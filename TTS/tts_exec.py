import time
import os.path
import torch
import numpy as np
import IPython.display as ipd
from scipy.io.wavfile import write
from playsound import playsound
from text import text_to_sequence
from hyper_parameters import tacotron_params
from training import load_model
from melgan.model.generator import Generator
from melgan.utils.hparams import load_hparam
import warnings
warnings.filterwarnings("ignore")
print("All basic modules loaded!")

torch.manual_seed(1234)
hparams = tacotron_params
MAX_WAV_VALUE = 32768.0
print("All needed functions loaded!")

# load trained tacotron2 + GST model:
model = load_model(hparams)
checkpoint_path = "checkpoint_78000"
model.load_state_dict(torch.load(checkpoint_path)['state_dict'])
model.to('cuda')
_ = model.eval()
print("Tacotron2 loaded successfully...")

# load pre trained MelGAN model for mel2audio:
vocoder_checkpoint_path = "nvidia_tacotron2_LJ11_epoch6400.pt"
checkpoint = torch.load(vocoder_checkpoint_path)
hp_melgan = load_hparam("melgan/config/default.yaml")
vocoder_model = Generator(80)  # Number of mel channels
vocoder_model.load_state_dict(checkpoint['model_g'])
vocoder_model = vocoder_model.to('cuda')
vocoder_model.eval(inference=False)
print("MelGAN vocoder loaded successfully.")

#GENERATE TEXT to SPEECH
#GST scores
gst_head_scores = np.array([0.3, 0.35, 0.35])
gst_scores = torch.from_numpy(gst_head_scores).cuda().float()
print('Input sequence and GST weights loaded...')


st = True
file_path = 'inputText/'
counter = 0
while st:
    save_path = 'audio_output/audio_test_' + str(counter) + '.wav'
    files = os.listdir(file_path)

    while len(files) == 0:
        #print(len(files))
        time.sleep(1)
        files = os.listdir(file_path)

    fp = (file_path + "/" + files[0])
    if os.path.isfile(fp):
        f = open(fp, "r")
        input_text = f.read()

        sequence = np.array(text_to_sequence(input_text, ['english_cleaners']))[None, :]
        sequence = torch.from_numpy(sequence).to(device='cuda', dtype=torch.int64)
        print("Input text sequence pre-processed successfully...")
        # text to mel inference:
        t1 = time.time()
        with torch.no_grad():
            mel_outputs, mel_outputs_postnet, _, alignments = model.inference(sequence,
                                                                              gst_scores)
        # Melgan mel2wav inference:
        t1 = time.time()
        with torch.no_grad():
            audio = vocoder_model.inference(mel_outputs_postnet)


        audio_numpy = audio.data.cpu().detach().numpy()
        elapsed_melgan = time.time() - t1
        f.close()
        os.remove(fp)
        print('Time elapsed in transforming mel to wav has been {} seconds.'.format(elapsed_melgan))

        write(save_path, 22050, audio_numpy)
        playsound(save_path)

        counter = counter + 1