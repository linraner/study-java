package com.lin;

import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSet;

import java.util.List;

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
        String str = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str);
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


    }


}
