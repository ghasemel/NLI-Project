import os
import time


def getTime():
    return time.strftime("%Y%m%d %H%M%S", time.gmtime())


def createDir(name: str):
    if not os.path.exists(name):
        os.mkdir(name)