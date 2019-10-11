package com.lin;

import com.google.common.base.Stopwatch;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 16:31
 **/
public class Test {
     public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i >= 0 ; i++) {
            if (i >= Integer.MAX_VALUE-1) {
                System.out.println(i);
            }
        }
        System.out.println("ans");
    }
}
