#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os
import time

def pathexist(path):
    if os.access(path, os.F_OK) == True:
        return True
    else:
        return False

def saveplot(plt):
    fparentpath = os.path.dirname(os.getcwd())
    fresultpath = fparentpath + '/result/'

    if pathexist(fresultpath) == False:
        os.mkdir(fresultpath)

    fname = time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())) + '.png'
    fpath = fresultpath + fname
    
    if pathexist(fpath) == True:
        fd = os.open(fpath, os.O_RDWR|os.O_TRUNC)
    else:
        fd = os.open(fpath, os.O_RDWR|os.O_CREAT)
    plt.savefig(fpath)
    os.close(fd)

def getresultfilename():
    fparentpath = os.path.dirname(os.getcwd())
    fresultpath = fparentpath + '/result/'

    if pathexist(fresultpath) == False:
        os.mkdir(fresultpath)

    fname = time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())) + '.txt'
    fpath = fresultpath + fname
    return fpath
