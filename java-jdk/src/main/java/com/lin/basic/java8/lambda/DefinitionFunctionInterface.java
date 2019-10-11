package com.lin.basic.java8.lambda;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-11 12:37
 * @Description: 自定义函数式接口
 **/
@FunctionalInterface
public interface DefinitionFunctionInterface<T> {
    void accept(T t);
}
