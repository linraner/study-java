package com.lin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 {

  public static void main(String[] args) {
//    Scanner cin = new Scanner(System.in);
//
//    String s = cin.next();
//
//    System.out.println(salve(s));

//    ClassLoader classLoader = Main2.class.getClassLoader();
//    System.out.println(classLoader.getResource("com/lin/Main2.class"));
    String s = "";

    List<String> dest = new ArrayList<>();

    List<String> src = new ArrayList<>();

    Collections.copy(dest, src);


  }

  private static String salve(String s) {
    if (s == null || s.equals("")) {
      return "";
    }
    char[] ch = s.toCharArray();
    char[] ch2 = s.toLowerCase().toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < ch2.length; i++) {
      map.put(ch2[i], i);
    }
    int cnt = 0;
    StringBuffer sb = new StringBuffer();
    for (char c : map.keySet()) {
      char tmp = ch[cnt++];
      if (charEquals(c, tmp)) {
        sb.append(tmp);
      }
    }
    return sb.toString();
  }

  private static boolean charEquals(char c1, char c2) {
    return c1 == c2 || (c1 - 32) == c2 || c1 == (c2 - 32);
  }

}
