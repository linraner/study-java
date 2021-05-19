package com.lin.basic.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-11 11:56
 * @Description:
 **/
public class Main {

  Runnable r1 = () -> System.out.println(this);
  Runnable r2 = () -> System.out.println(toString());


  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        // do
      }
    }).start();

    new Thread(() -> {
      // do
    }).start();

    List<String> list = Arrays.asList("Bye", "hello", "ok", "QWQ", "asda");
    Collections.sort(list, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        if (o1 == null) {
          return -1;
        }
        if (o2 == null) {
          return 1;
        }
        return o1.length() - o2.length();
      }
    });

    Collections.sort(list, (o1, o2) -> {
      if (o1 == null) {
        return -1;
      }
      if (o2 == null) {
        return 1;
      }
      return o1.length() - o2.length();
    });

    new Main().r1.run();
    new Main().r2.run();

    //
    BinaryOperator<Long> add = (Long a, Long b) -> a + b;

    // 自定义函数式接口
    DefinitionFunctionInterface<String> definitionFunctionInterface = str -> System.out.println(str);
    // else use class
    MyStream<String> stream = new MyStream<>();
    stream.myForEach(str -> System.out.println(str));


  }

  static void test() {
    System.out.println("this is a test demo");

  }

  @Override
  public String toString() {
    return "Main to String";
  }
}

class MyStream<T> {

  private List<T> list;

  public void myForEach(DefinitionFunctionInterface<T> definitionFunctionInterface) {
    for (T t : list) {
      definitionFunctionInterface.accept(t);
    }

  }
}