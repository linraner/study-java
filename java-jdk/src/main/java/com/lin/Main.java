package com.lin;

import com.google.common.collect.ImmutableSet;
import com.lin.domain.Luck;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.http.util.Asserts;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 16:12
 **/
public class Main {

  public static final ImmutableSet<String> COLOR_NAME = ImmutableSet.of(
      "red",
      "white",
      "black",
      "grey"
  );

  public static void main(String[] args) throws InterruptedException {
//        String str = "1-2-3-4-5-6";
//        List<String> list = Splitter.on("-").splitToList(str);
//        list.stream().forEach(System.out::println);
/*
        String s = "asdaszhdhh";
        System.out.println(s);
        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            map[i] = 0;
        }
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                sb.append((char) ('a' + i));
            }
        }
        System.out.println(sb);

        */

//        LocalDate time = LocalDate.now();
//        System.out.println(time);

//    List<String> list = Stream.of("a", "b", "c", "d", "e", "f").collect(Collectors.toList());

//    System.out.println(randomToInt(list, 4));
    String ret = parseString("#1#白泽");
    String s = "#12#白泽";
//    String[] ss = s.split("[1-9][0-9]?");
    String[] ss = s.split("^#([0-9]{1})#");
    Pattern p = Pattern.compile("[^0-9]");
    Matcher m = p.matcher(s);
//    System.out.println(m.replaceAll("").trim());

    Luck l1 = new Luck("少女粉", "小熊", "小纠结");
    Luck l2 = new Luck("少女粉", "小熊", "小纠结");
    Luck l3 = new Luck("快乐黄", "小熊", "小纠结");

    System.out.println(l1.hashCode());
    System.out.println(l3.hashCode());
    System.out.println(l1.hashCode() == l2.hashCode());
    l1.setColor("快乐黄");
    System.out.println(l1.hashCode() == l3.hashCode());

  }

  private static String parseString(String s) {
    if (s.startsWith("#")) {

    }
    return null;
  }

  private static List<Integer> randomToInt(List<String> list, int count) {
    int size = list.size();
    if (count > size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int cnt = 0;
    Set<Integer> ret = new HashSet<>();
    Random random = new Random();
    while (count > ret.size()) {
      ret.add(random.nextInt(size));
    }

    return ret.stream().collect(Collectors.toList());
  }


}
