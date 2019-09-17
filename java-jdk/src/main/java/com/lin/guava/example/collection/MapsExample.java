package com.lin.guava.example.collection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lin.guava.example.entity.Person;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 13:34
 * @Description: Maps使用
 **/
public class MapsExample {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Person person1 = new Person("001", "libia");
        Person person2 = new Person("002", "dufu");

        List<Person> personList = Lists.newArrayList(person1, person2);

        Map<String, Person> personMap = Maps.uniqueIndex(personList.iterator(), new Function<Person, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Person input) {
                return input.getId();
            }
        });
        System.out.println("将主键当做Map的key: " + personMap);

        // Maps.uniqueIndex相反
        Set<Person> personSet = Sets.newHashSet(person1, person2);
        Map<Person, String> personAsMap = Maps.asMap(personSet, new Function() {
            @Nullable
            @Override
            public Object apply(@Nullable Object o) {
                return ((Person)o).getId();
            }
        });
        System.out.println("personAsMap = " + personAsMap);


        // 转换Map中的value值
        Map<String, String> transformValuesMap = Maps.transformValues(personMap, new Function<Person, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Person input) {
                return input.getName();
            }
        });
        System.out.println("转换Map中的value值: " + transformValuesMap);

    }

}


