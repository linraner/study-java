package com.lin.guava.example.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:23
 * @Description: BiMaps key和value都是唯一的
 **/
public class BiMapsExample {

  public static void main(String[] args) {
    BiMap<String, String> biMap = HashBiMap.create();
    biMap.put("1", "red");
    biMap.put("2", "white");
    // 相同value报错
//        biMap.put("2", "red");
    // 相同value覆盖前一个
//        biMap.forcePut("2", "red");
    System.out.println("biMap = " + biMap);

    // k-v翻转
    System.out.println(biMap.inverse());


  }
}
