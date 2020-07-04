#from pocketsphinx import LiveSpeech
#for phrase in LiveSpeech(): print(phrase)



#from pocketsphinx import LiveSpeech

#speech = LiveSpeech(lm=False, keyphrase='hello', kws_threshold=1e-20)
#for phrase in speech:
    #print(phrase.segments(detailed=True))


#
#
# import os
# from pocketsphinx import LiveSpeech, get_model_path
#
# model_path = get_model_path()
#
# speech = LiveSpeech(
#     verbose=False,
#    sampling_rate=16000,
#    buffer_size=2048,
#    no_search=False,
#    full_utt=False,
#    hmm=os.path.join(model_path, 'en-us'),
#    lm=os.path.join(model_path, 'en-us.lm.bin'),
#    dic=os.path.join(model_path, 'cmudict-en-us.dict')
# )
#
# for phrase in speech:
#    print(phrase)

#
# import os
# from pocketsphinx import AudioFile, get_model_path, get_data_path
#
# model_path = get_model_path()
# data_path = get_data_path()
#
# config = {
#     'verbose': False,
#     'audio_file': os.path.join(data_path, 'cloth.wav'),
#     'buffer_size': 2048,
#     'no_search': False,
#     'full_utt': False,
#     'hmm': os.path.join(model_path, 'en-us'),
#     'lm': os.path.join(model_path, 'en-us.lm.bin'),
#     'dict': os.path.join(model_path, 'cmudict-en-us.dict')
# }
#
# audio = AudioFile(**config)
# for phrase in audio:
#     print(phrase)

from google.cloud import speech_v1
from google.cloud.speech_v1 import enums

client = speech_v1.SpeechClient()

encoding = enums.RecognitionConfig.AudioEncoding.FLAC
sample_rate_hertz = 44100
language_code = 'en-US'
config = {'encoding': encoding, 'sample_rate_hertz': sample_rate_hertz, 'language_code': language_code}
uri = 'gs://bucket_name/file_name.flac'
audio = {'uri': uri}

response = client.recognize(config, audio)

