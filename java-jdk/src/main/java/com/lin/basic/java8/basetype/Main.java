package com.lin.basic.java8.basetype;

import javax.print.attribute.IntegerSyntax;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-06 15:02
 * @Description: Java8一些基础类型加强 IntStream LongStream
 **/
public class Main {
    final static String div = "==========================================";

    public static void main(String[] args) {
        // 创建空的 IntStream
        IntStream intStreamEnmpty = IntStream.empty();
        IntStream intStream = IntStream.of(1, 2, 3);
        // 包含1-9的 IntStream
        IntStream intStreamRange = IntStream.range(1, 10);
        // 包含1-10
        IntStream intStreamRange2 = IntStream.rangeClosed(1, 10);

        // 包含 3
        IntStream generated = IntStream.generate(() -> 3);
        // 无限循环 IntStream 1, 3, 5, 7 ...
        IntStream infinite = IntStream.iterate(1, operand -> operand + 3);

        // int[] 构建
        int[] arr = new int[]{1, 2, 3, 3, 4};
        // 包含整个
        IntStream arrStream = Arrays.stream(arr);
        // index [2, 4)
        IntStream rangeArray = Arrays.stream(arr, 2, 4);

        // filter过滤数据 int -> boolean接口函数
        IntStream.range(1, 10).filter(elem -> elem % 2 == 0).forEach(System.out::println);
        System.out.println(div);
        // map int -> int接口函数
        IntStream.range(1, 8).map(elem -> elem * 10).forEach(System.out::println);
        System.out.println(div);
        // mapToLong int -> long函数接口
        IntStream.range(1, 5).mapToLong(elem -> elem * 100L).forEach(System.out::println);
        System.out.println(div);
        // mapToDouble
        IntStream.range(1, 5).mapToDouble(elem -> elem + 0.1).forEach(System.out::println);

        System.out.println(div);
        // min OptionaInt 类型
        System.out.println(IntStream.of(1, 2, 3, -1).min().getAsInt());

        System.out.println(div);
        // summaryStatistics 获取 Stream元素统计信息 返回 IntSummaryStatistics
        IntSummaryStatistics summary = IntStream.range(1, 11).summaryStatistics();

        // Min Max Sum Avg Count
        System.out.println(summary.getMin());
        System.out.println(summary.getCount());







    }
}
