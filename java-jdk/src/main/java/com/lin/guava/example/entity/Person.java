package com.lin.guava.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 13:07
 * @Description: Person实体类
 **/
@ToString
@Getter
@Setter
public class Person {

  private String id;
  private String name;
  private int age;

  public Person() {
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Person(String id, String name) {
    this.id = id;
    this.name = name;
  }

}
