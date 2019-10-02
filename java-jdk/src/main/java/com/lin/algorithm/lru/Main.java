package com.lin.algorithm.lru;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-02 15:35
 * @Description:
 **/
public class Main {
    public static void main(String[] args) {
        LRU cache = new LRU(5);
        for (int i = 1; i <= 5; i++) {
            cache.set(i, i);
        }
        System.out.println("get 1:" + cache.get(1));
        cache.set(6, 6);
        System.out.println("get 2:" + cache.get(2));
    }
}
