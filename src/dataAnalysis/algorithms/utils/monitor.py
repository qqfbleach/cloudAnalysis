#!/usr/bin/python
# -*- coding: UTF-8 -*-

import httplib,urllib
import sys
from algorithms.utils import fileop
import json

https = False
url = "/cloud/v1/instance/status/:"
serverinfo = "/home/server_info"
       
def gethost():
    if fileop.pathexist(serverinfo) == True:
        fd = open(serverinfo)
        setting = json.load(fd)
        host = setting['host']
        fd.close()
    else:
        print "server_info file:" + serverinfo + " is not exist!"
        host = -1
    return host

def getnodeid():
    if fileop.pathexist(serverinfo) == True:
        fd = open(serverinfo)
        setting = json.load(fd)
        nodeid = setting['nodeId']
        fd.close()
    else:
        print "server_info file:" + serverinfo + " is not exist!"
        nodeid = -1
    return nodeid

def sendstatus(process,msg,ishttps):
    global https, host, url
    if ishttps == True:
        httpsConn = httplib.HTTPSConnection(gethost())
        sock = socket.create_connection((httpsConn.host, httpsConn.port))
        httpsConn.sock = ssl.wrap_socket(sock, ca_certs=CERT_FILE,cert_reqs=ssl.CERT_REQUIRED, ssl_version=ssl.PROTOCOL_SSLv3)
    else:
        httpsConn = httplib.HTTPConnection(gethost())
    body = {
            "task_id":3344,
            "task_result":"",
            "process":process,
            "status":msg
            }
    if getnodeid() == -1:
        print "Id error!"
    else:
        url = url + str(getnodeid())
    httpsConn.request("POST", url, body)
    res = httpsConn.getresponse()
    print res.status, res.reason
    if httpsConn:
        httpsConn.close

def sendcurstatus(process,msg):
    #msg = msg + " in function " + sys._getframe().f_back.f_code.co_name
    #print msg
    sendstatus(process, msg, https)
