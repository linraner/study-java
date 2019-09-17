package com.lin.guava.example.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:20
 * @Description:
 **/
public class ArrayListMultiMapExample {
    public static void main(String[] args) {
        // ArrayListMultimap List允许重复
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("foo", "1");
        multimap.put("foo", "2");
        multimap.put("foo", "3");
        multimap.put("foo", "3");
        System.out.println("multimap = " + multimap);
        System.out.println("size: " + multimap.size());

        // HashMultimap 不允许重复
        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("foo", "1");
        hashMultimap.put("foo", "2");
        hashMultimap.put("foo", "2");
        hashMultimap.put("foo", "3");
        hashMultimap.put("foo", "3");
        System.out.println("hashMultimap = " + hashMultimap);
        System.out.println("size: " + hashMultimap.size());

    }
}
