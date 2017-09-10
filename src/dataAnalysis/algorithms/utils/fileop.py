#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os
import time

def saveplot(plt):
    fparentpath = os.path.dirname(os.getcwd())
    fresultpath = fparentpath + '/result/'

    if os.access(fresultpath, os.F_OK) == False:
        os.mkdir(fresultpath)

    fname = time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())) + '.png'
    fpath = fresultpath + fname
    
    if os.access(fpath, os.F_OK) == True:
        os.open(fpath, os.O_RDWR|os.O_TRUNC)
    else:
        os.open(fpath, os.O_RDWR|os.O_CREAT)
    plt.savefig(fpath)
