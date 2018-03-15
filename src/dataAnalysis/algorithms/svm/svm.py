#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""
==============
SVM
==============

"""
print(__doc__)

import numpy as np
from sklearn import svm
from algorithms.utils import fileop
from algorithms.utils import monitor

def dosvm(posisample="../dataset/posi_samples.txt", negsample="../dataset/neg_samples.txt", predict="../dataset/predict.txt", percent=1):
    # read positive samples
    monitor.sendcurstatus(0, "Loading positive samples...")
    if fileop.pathexist(posisample) == False:
        monitor.sendcurstatus(0, "Positive samples file is not exist")
        return
    posi = np.loadtxt(posisample)
    posi_lines = int(posi.shape[0] * percent)
    posi_portion = posi[0:posi_lines,:]
    posi_y = posi_portion[:,0]
    posi_x = np.delete(posi_portion, 0, 1)

    # read negtive samples
    monitor.sendcurstatus(0.2, "Loading negtive samples...")
    if fileop.pathexist(negsample) == False:
        monitor.sendcurstatus(0.25, "Negtive samples file is not exist")
        return
    neg = np.loadtxt(negsample)
    neg_lines = int(neg.shape[0] * percent)
    neg_portion = neg[0:neg_lines,:]
    neg_y = neg_portion[:,0]
    neg_x = np.delete(neg_portion, 0, 1)

    # concatenate data
    train_x = np.concatenate((posi_x,neg_x), axis=0)
    train_y = np.concatenate((posi_y,neg_y), axis=0)

    # fit the model
    monitor.sendcurstatus(0.4, "Training...")
    clf = svm.SVC()
    clf.fit(train_x, train_y)

    # read predict samples
    monitor.sendcurstatus(0.6, "Loading predict samples...")
    if fileop.pathexist(predict) == False:
        monitor.sendcurstatus(0, "Predict samples file is not exist")
        return
    pred = np.loadtxt(predict)

    # predict
    monitor.sendcurstatus(0.8, "Predicting...")
    result = clf.predict(pred)

    # save result
    np.savetxt(fileop.getresultfilename(), result)
    monitor.sendcurstatus(1, "Work done!")
