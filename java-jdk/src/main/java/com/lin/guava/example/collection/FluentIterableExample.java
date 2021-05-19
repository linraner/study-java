package com.lin.guava.example.collection;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.lin.guava.example.entity.Person;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 12:40
 * @Description: 迭代器
 **/
public class FluentIterableExample {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Person person1 = new Person("libai", 50);
    Person person2 = new Person("dufu", 40);
    @SuppressWarnings("rawtypes")
    ArrayList persionList = Lists.newArrayList(person1, person2);

    // 返回条件过滤结果集
    Iterable<Person> personFilterByAge = FluentIterable.from(persionList)
        .filter(new Predicate<Person>() {
          @Override
          public boolean apply(@Nullable Person input) {
            return input.getAge() > 40;
          }
        });
    Iterator<Person> i = personFilterByAge.iterator();
    while (i.hasNext()) {
      System.out.println("年龄大于40的是:  " + i.next().getName());
    }
    System.out.println("==============================");
    // 返回处理的结果集
    List<String> transformedPersonList = FluentIterable.from(persionList)
        .transform(new Function<Person, String>() {

          @Nullable
          @Override
          public String apply(@Nullable Person input) {
            return Joiner.on(":").join(input.getName(), input.getAge());
          }
        }).toList();
    Iterator transformedPersonListIterator = transformedPersonList.iterator();
    while (transformedPersonListIterator.hasNext()) {
      System.out.println("拼接结果是: " + transformedPersonListIterator.next());
    }
  }
}

