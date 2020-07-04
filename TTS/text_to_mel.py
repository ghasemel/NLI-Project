import time
import torch
import numpy as np
import IPython.display as ipd
from scipy.io.wavfile import write
import warnings
warnings.filterwarnings("ignore")
print("All basic modules loaded!")

#LOAD MODELS
torch.manual_seed(1234)

# tacotron2 text2mel:
from hyper_parameters import tacotron_params
from training import load_model

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

# melgan mel2wav:

from melgan.model.generator import Generator
from melgan.utils.hparams import load_hparam

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
torch.manual_seed(1234)
#INPUT:
# where the clip file will be written:
save_path = 'audio_test.wav'
# where the pre-trained model is located:
# Inputs for the synthesis:
test_text = "the recommended book for natural language interaction is neural network methods from goldberg"

#GST scores
gst_head_scores = np.array([0.4, 0.2, 0.4])
gst_scores = torch.from_numpy(gst_head_scores).cuda().float()
print('Input sequence and GST weights loaded...')


# TEXT2MEL:
torch.manual_seed(1234)
from text import text_to_sequence
#preprocessing:
sequence = np.array(text_to_sequence(test_text, ['english_cleaners']))[None, :]
sequence = torch.from_numpy(sequence).to(device='cuda', dtype=torch.int64)
print("Input text sequence pre-processed successfully...")
#text to mel inference:
t1 = time.time()
with torch.no_grad():
  mel_outputs, mel_outputs_postnet, _, alignments = model.inference(sequence,
                                                                    gst_scores)

# MEL2WAV :
from audio_processing import griffin_lim
from nn_layers import TacotronSTFT
torch.manual_seed(1234)

# Griffin Lim vocoder synthesis:
# griffin_iters = 60
# taco_stft = TacotronSTFT(hparams['filter_length'], hparams['hop_length'], hparams['win_length'], sampling_rate=hparams['sampling_rate'])
#
# t2 = time.time()
# mel_decompress = taco_stft.spectral_de_normalize(mel_outputs_postnet)
# mel_decompress = mel_decompress.transpose(1, 2).data.cpu()
#
# spec_from_mel_scaling = 60
# spec_from_mel = torch.mm(mel_decompress[0], taco_stft.mel_basis)
# spec_from_mel = spec_from_mel.transpose(0, 1).unsqueeze(0)
# spec_from_mel = spec_from_mel * spec_from_mel_scaling
#
# audio = griffin_lim(torch.autograd.Variable(spec_from_mel[:, :, :-1]), taco_stft.stft_fn, griffin_iters)
#
# audio = audio.squeeze()
# audio_numpy = audio.cpu().numpy()
#
# elapsed_griffinLim = time.time() - t2
# print('Time elapsed in transforming mel to wav has been {} seconds.'.format(elapsed_griffinLim))

# Melgan mel2wav inference:
t1 = time.time()
with torch.no_grad():
  audio = vocoder_model.inference(mel_outputs_postnet)

audio_numpy = audio.data.cpu().detach().numpy()
elapsed_melgan = time.time() - t1

print('Time elapsed in transforming mel to wav has been {} seconds.'.format(elapsed_melgan))


write(save_path, 22050, audio_numpy)
ipd.Audio(audio_numpy, rate=22050)