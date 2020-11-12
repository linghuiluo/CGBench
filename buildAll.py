# -*- coding: utf-8 -*-
"""
Created on Thu Nov 12 09:57:31 2020

@author: linghui
"""
import os
from shutil import copyfile

def buildAllapps(cgbench):
    allBenchmarks = os.path.join(cgbench, "allBenchmarks") 
    if not os.path.exists(allBenchmarks):
        os.mkdir(allBenchmarks)
    for d in os.listdir(cgbench):      
        pomFile = os.path.join(cgbench, d, "pom.xml")
        appDir = os.path.join(cgbench, d)
        targetDir = os.path.join(appDir, "target")
        if os.path.exists(pomFile):
           os.chdir(appDir)
           ssFile = d+"_SourcesAndSinks.txt"
           if os.path.exists(ssFile):
                fromFile = os.path.join(appDir, ssFile)
                toFile = os.path.join(allBenchmarks, ssFile)
                copyfile(fromFile,toFile) 
           print("building "+d)
           os.system("mvn install")
           if os.path.exists(targetDir):
               for f in os.listdir(targetDir):
                   if f.endswith(".jar"):
                       print(f)
                       fromFile = os.path.join(targetDir, f)
                       toFile = os.path.join(allBenchmarks, d+".jar")
                       copyfile(fromFile,toFile)
    os.system("ls "+allBenchmarks)
          
cgbench = os.path.dirname(os.path.abspath(__file__))           
buildAllapps(cgbench)
