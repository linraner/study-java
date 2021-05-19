package com.lin.utils;

import java.util.Objects;

public class FileUtils {


  public static void main(String[] args) {
    System.out.println(Objects.requireNonNull(FileUtils.class.getClassLoader().getResource("`pwd`")).getPath());
    ;
  }

}
