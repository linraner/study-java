package com.lin.basic.set.order;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SetOrderTest {

  public static void main(String[] args) {
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new LinkedHashSet<>();
    IntStream.range(0, 10).boxed().forEach(x -> {
      set1.add(x);
      set2.add(x);
    });
    // hash Set 不保证顺序
    set1.forEach(System.out::print);
    System.out.println("\n");
    set2.forEach(System.out::print);
  }


}
