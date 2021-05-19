package com.lin.guava.example.collection;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 09:51
 * @Description: 字符串常用操作
 **/
public class StringExample {

  public static void main(String[] args) {
    // utf-8格式
    // Java jdk byte[] bytes = "foo".getBytes("UTF-8");
    byte[] bytes = "foo".getBytes(Charsets.UTF_8);

    // 字符串连接
    String s1 = Strings.padEnd("foo", 6, 'a');
    System.out.println("s1 = " + s1);

    // ""转null NPE
    String sToNull1 = Strings.emptyToNull("123");
    String sToNull2 = Strings.emptyToNull("");
    System.out.println(sToNull1 + "--" + sToNull2);

    // 多个tab多个空格转成一个空格
    String tabsAndSpaces = "asd  asdda as as     asd  tabs.";
    String expected = "asd asdda as as asd tabs.";
    String s2 = CharMatcher.whitespace().collapseFrom(tabsAndSpaces, ' ');
    System.out.println(expected.equals(s2));

    // Object utilities 对象工具
    // 1. toString()实现
//        System.out.println(Objects.(WorkingWithStrings.class).omitNullValues()
//                .add("expected", expected).add("tabsAndSpaces", tabsAndSpaces));

    // 2. 检查如果为null值 , 填充默认值
//        System.out.println(Objects.firstNonNull(null, "default value"));

    // 3. 生成hashcode
    System.out.println(Objects.hashCode(tabsAndSpaces, expected));

    // 4. CompareTo实现    如果都是相同的返回0，有一个不同返回-1
    System.out.println(ComparisonChain.start().compare(tabsAndSpaces, expected).compare(expected, s2).result());


  }
}
