# 测试Python插件
import os

path = "D:/program/java/my/java-study/java-jdk/src/main/java/com/lin/basic/io/my/filerename/file/"

fileList = os.listdir(path)

n = 0
for i in fileList:
    oldName = path + fileList[n]
    newName = path + 'b' + str(n+1) + '.txt'
    os.rename(oldName, newName)
    print(oldName, '===>',newName)
    n += 1
