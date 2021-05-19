package com.lin.guava;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import javax.annotation.Nullable;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 11:32
 * @Description
 **/
public class Main {

  public static void main(String[] args) {
    Optional<Integer> age = Optional.of(5);
    age.isPresent();
    age.get();

    // 自定义排序
    Ordering<String> byLengthOrdering = new Ordering<String>() {
      @Override
      public int compare(@Nullable String s, @Nullable String t1) {
        return Ints.compare(s.length(), t1.length());
      }
    };


  }

}

class Foo {

  private String lastName;
  private String firstName;
  private int zipCode;

  // 比较器
  public int compareTo(Foo that) {
    return ComparisonChain.start()
        .compare(this.lastName, that.lastName)
        .compare(this.firstName, that.firstName)
        .compare(this.zipCode, that.zipCode, Ordering.natural().nullsLast())
        .result();
  }


  // 另一种排序
  Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
    public String apply(Foo foo) {
      return foo.lastName;
    }
  });

}