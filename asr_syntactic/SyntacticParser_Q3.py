import os
import time
import spacy
import util

print(spacy.__version__)


def convertToResult(word: str, token):
    return "{{\"text\":\"{0}\"," \
           "\"idx\":{1}," \
           "\"lemma\":\"{2}\"," \
           "\"is_punct\":\"{3}\"," \
           "\"is_space\":\"{4}\"," \
           "\"shape\":\"{5}\"," \
           "\"pos\":\"{6}\"," \
           "\"tag\":\"{7}\"," \
           "\"dep\":\"{8}\"}}\n".format(
                    word,
                    token.idx,
                    token.lemma_,
                    token.is_punct,
                    token.is_space,
                    token.shape_,
                    token.pos_, # part of speech
                    token.tag_,
                    token.dep_ # dependency label
                )


nlp = spacy.load('en_core_web_sm')

INPUT_DIR = 'input'
BACKUP_DIR = 'backup'
SYNTACTIC_RESULT_DIR = 'syntactic_result'

util.createDir(INPUT_DIR)
util.createDir(BACKUP_DIR)
util.createDir(SYNTACTIC_RESULT_DIR)

while True:
    files = os.listdir(INPUT_DIR)
    if os.path.exists(INPUT_DIR) and len(files) == 0:
        print('the directory ' + INPUT_DIR + ' not exists or is empty')
        time.sleep(1)  # wait for one second
        continue

    for file in files:
        print('processing file: ' + file)
        f = open(INPUT_DIR + "/" + file, 'r')
        lines = f.readlines()

        if len(lines) == 0:
            print('file is empty: ' + file)
            continue # next file

        result = open(SYNTACTIC_RESULT_DIR + "/" + util.getTime() + '_' + file, 'w+')

        for line in lines:
            doc = nlp(line)
            for token in doc:
                word = token.text.strip()
                word = 'new_line' if word == '' else token.text

                result.write(convertToResult(word, token))
                print(convertToResult(word, token))

            # end token for
        # end line for

        result.close()
        f.close()
        os.rename(INPUT_DIR + "/" + file, BACKUP_DIR + "/" + util.getTime() + '_' + file)
    # end file for

    time.sleep(1) # wait for one second
# end while


