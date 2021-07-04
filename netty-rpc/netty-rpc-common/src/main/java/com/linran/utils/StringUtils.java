package com.linran.utils;

public final class StringUtils {

  public static boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }

  public static boolean isNotEmpty(String s) {
    return !isEmpty(s);
  }

}
