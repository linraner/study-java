package com.lin.guava.example;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 12:57
 * @Description: 列表
 **/
public class ListsExample {
    public static void main(String[] args) {
        Person person1 = new Person("libai", 50);
        Person person2 = new Person("dufu", 40);
        Person person3 = new Person("baijuyi", 20);
        Person person4 = new Person("taoyuanming", 30);
        Person person5 = new Person("dumu", 20);
        List<Person> personList = Lists.newArrayList(person1, person2, person3, person4, person5);
        // 3为拆分长度
        List<List<Person>> subList = Lists.partition(personList, 3);
        Iterator<List<Person>> i = subList.iterator();
        while (i.hasNext()) {
            List<Person> listTemp = (List<Person>) i.next();
            Iterator<Person> iTemp = listTemp.iterator();
            while (iTemp.hasNext()) {
                System.out.println("iTemp = " + iTemp.next().getName());
            }
        }
    }
}



