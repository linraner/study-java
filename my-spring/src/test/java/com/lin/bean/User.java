package com.lin.bean;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-10 15:33
 * @Description:
 **/
public class User {
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void init(){
        System.out.println("init...");
    }

    public void destory(){
        System.out.println("destory...");
    }

    public void sayHello(){
        System.out.println("hello...");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
