#!/usr/bin/python
# -*- coding: UTF-8 -*-

import httplib,urllib
import sys
import os
from algorithms.utils import fileop
import json
import threading

https = False
serverinfo = "/home/server_info"
host = "180.117.170.45:8000"
url = "/cloud/v1/instance/linode/"

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
    global host
    global url
    process = curstatus.process
    #msg = curstatus.message
    #host = gethost()
    if ishttps == True:
        httpsConn = httplib.HTTPSConnection(host)
        sock = socket.create_connection((httpsConn.host, httpsConn.port))
        httpsConn.sock = ssl.wrap_socket(sock, ca_certs=CERT_FILE,cert_reqs=ssl.CERT_REQUIRED, ssl_version=ssl.PROTOCOL_SSLv3)
    else:
        httpsConn = httplib.HTTPConnection(host)
    body = {
            "task_id":os.getpid(),
            "task_result":"",
            "process":str(process),
            "status":3
           }
    headers={'content-type':'application/json'}
    #url = geturl()
    lurl = url + str(getnodeid())
    httpsConn.request("POST", lurl, json.JSONEncoder().encode(body), headers)
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
