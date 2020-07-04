import os

import spacy
from pip._vendor.distlib.compat import raw_input

nlp = spacy.load('en_core_web_sm')

while True:
    sentence = raw_input("Input your sentence (q to exit): ")
    if sentence == "q":
        break

    doc = nlp(sentence)
    for token in doc:
        print("{0}\t{1}\t{2}\t{3}\t{4}\t{5}\t, pos: {6}\t, tag: {7}\t, dep: {8}".format(
            token.text,
            token.idx,
            token.lemma_,
            token.is_punct,
            token.is_space,
            token.shape_,
            token.pos_, # part of speech
            token.tag_,
            token.dep_ # dependency label
        ))
# end while