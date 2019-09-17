package com.lin.guava.example.collection;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 13:28
 * @Description: sets
 **/
public class SetsExample {
    public static void main(String[] args) {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        System.out.println("s1 = " + s1);
        Set<String> s2 = Sets.newHashSet("2", "3", "4");
        System.out.println("s2 = " + s2);

        System.out.println("差集: " + Sets.difference(s1, s2));
        System.out.println("互斥集合: " + Sets.symmetricDifference(s1, s2));
        System.out.println("交集: " + Sets.intersection(s1, s2));
        System.out.println("并集: " + Sets.union(s1, s2));

    }
}
