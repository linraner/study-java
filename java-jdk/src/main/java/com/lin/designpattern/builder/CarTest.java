package com.lin.designpattern.builder;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-06 15:47
 * @Description:
 **/
public class CarTest {
    public static void main(String[] args) {
        Car car = new Car.Builder().carBody("奔驰")
                .safetyBelt(null).build();
    }
}
