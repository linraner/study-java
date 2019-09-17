package com.lin;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

import java.util.List;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 16:12
 **/
public class Main {
    public static final ImmutableSet<String> COLOR_NAME = ImmutableSet.of(
            "red",
            "white",
            "black",
            "grey"
    );

    public static void main(String[] args) {
        String str = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str);
        list.stream().forEach(System.out::println);


    }


}
