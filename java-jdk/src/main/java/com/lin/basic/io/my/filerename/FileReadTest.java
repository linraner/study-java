package com.lin.basic.io.my.filerename;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReadTest {

  private static void readFile() throws IOException {
    InputStream input = new FileInputStream("/home/linran/testUser");
    for (; ; ) {
      int n = input.read(); // 反复调用read()方法，直到返回-1
      if (n == -1) {
        break;
      }
      System.out.println(n); // 打印byte的值
    }
    input.close(); // 关闭流
  }

  private static void readLine() {
    try {
      InputStream is = new FileInputStream("/home/linran/testUser");
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String str = null;
      while (true) {
        str = reader.readLine();
        if(str!=null)
          System.out.println(str);
        else
          break;
      }
      is.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) throws IOException {
//    readFile();
    readLine();

  }

}
