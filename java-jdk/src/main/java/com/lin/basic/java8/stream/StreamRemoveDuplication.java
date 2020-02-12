package com.lin.basic.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamRemoveDuplication {

  public static void main(String[] args) {
    List<int[]> lists = new ArrayList<int[]>();
    int[] list = {-1, 0, 1};

    lists.add(list);
    lists.add(list);

    lists.stream()
        .distinct()
        .collect(Collectors.toList());
    System.out.println(lists);
  }

}
