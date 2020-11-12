# -*- coding: utf-8 -*-
"""
Created on Thu Nov 12 09:57:31 2020

@author: linghui
"""
import os


def buildAllapps(cgbench):
    for d in os.listdir(cgbench):      
        pomFile = os.path.join(cgbench, d, "pom.xml")
        appDir = os.path.join(cgbench, d)
        if os.path.exists(pomFile):
           os.chdir(appDir)
           print("building "+d)
           os.system("mvn install")
          
cgbench = os.path.dirname(os.path.abspath(__file__))           
buildAllapps(cgbench)
