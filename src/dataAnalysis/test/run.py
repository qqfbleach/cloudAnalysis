#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys
sys.path.append("..")
#from algorithms.svm_nonlinear import plot_svm_nonlinear
from algorithms.svm import svm

if __name__ == "__main__":
    len = len(sys.argv)
    if len >= 2:
        posi = sys.argv[1]
    if len >= 3:
        neg = sys.argv[2]
    if len >= 4:
        predict = sys.argv[3]
    if len >= 5:
        percent = sys.argv[4]
    #plot_svm_nonlinear.plot()
    #svm.dosvm(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])
    svm.dosvm()
