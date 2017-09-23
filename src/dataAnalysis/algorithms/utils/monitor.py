#!/usr/bin/python
# -*- coding: UTF-8 -*-

import httplib,urllib
import sys
from algorithms.utils import fileop
import json
import threading

https = False
serverinfo = "/home/server_info"

statuslist = []

class status(object):
    def __init__(self,process,message):
        self.process = process
        self.message = message

def gethost():
    if fileop.pathexist(serverinfo) == True:
        fd = open(serverinfo)
        setting = json.load(fd)
        hostraw = setting['host']
        index = hostraw.find('/')
        host = hostraw[0:index]
        fd.close()
    else:
        print "server_info file:" + serverinfo + " is not exist!"
        host = -1
    return host

def geturl():
    if fileop.pathexist(serverinfo) == True:
        fd = open(serverinfo)
        setting = json.load(fd)
        hostraw = setting['host']
        index = hostraw.find('/')
        url = hostraw[index:len(hostraw)]
        fd.close()
    else:
        print "server_info file:" + serverinfo + " is not exist!"
        url = -1
    return url

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

def sendstatus(curstatus,ishttps):
    process = curstatus.process
    #msg = curstatus.message
    host = gethost()
    if ishttps == True:
        httpsConn = httplib.HTTPSConnection(host)
        sock = socket.create_connection((httpsConn.host, httpsConn.port))
        httpsConn.sock = ssl.wrap_socket(sock, ca_certs=CERT_FILE,cert_reqs=ssl.CERT_REQUIRED, ssl_version=ssl.PROTOCOL_SSLv3)
    else:
        httpsConn = httplib.HTTPConnection(host)
    body = {
            "task_id":3344,
            "task_result":"",
            "process":str(process),
            "status":3
           }
    url = geturl()
    httpsConn.request("POST", url, json.JSONEncoder().encode(body))
    res = httpsConn.getresponse()
    print res.status, res.reason
    if httpsConn:
        httpsConn.close

def sendcurstatus(process,msg):
    #msg = msg + " in function " + sys._getframe().f_back.f_code.co_name
    #print msg
    global statuslist
    curstatus = status(process,msg)
    statuslist.append(curstatus)

def listen():
    global statuslist
    process = 0
    print "Start listen..."
    while process != 1:
        if len(statuslist) > 0:
            curstatus = statuslist[0]
            process = curstatus.process
            sendstatus(curstatus, False)
            print "sendstatus success"
            statuslist.pop(0)

def startlisten():
    t = threading.Thread(target=listen)    
    t.start()
