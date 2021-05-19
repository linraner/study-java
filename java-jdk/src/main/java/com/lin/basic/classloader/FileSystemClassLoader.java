package com.lin.basic.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 13:22
 * @Description:自定义文件系统类加载器
 **/
public class FileSystemClassLoader extends ClassLoader {

  private String rootDir;

  public FileSystemClassLoader(String rootDir) {
    this.rootDir = rootDir;
  }

  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] classData = getClassData(name);
    if (classData == null) {
      throw new ClassNotFoundException();
    } else {
      return defineClass(name, classData, 0, classData.length);
    }
  }

  private byte[] getClassData(String className) {
    // get this class path
    String path = classNameToPath(className);
    try {
      InputStream ins = new FileInputStream(path);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      int bufferSize = 4096;
      byte[] buffer = new byte[bufferSize];
      int byteNumRead = 0;
      while (true) {
        try {
          if (!((byteNumRead = ins.read(buffer)) != -1)) {
            break;
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        baos.write(buffer, 0, byteNumRead);
      }
      return baos.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String classNameToPath(String className) {
    return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
  }

}
