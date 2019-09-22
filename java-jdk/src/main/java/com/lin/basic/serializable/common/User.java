package com.lin.basic.serializable.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 10:06
 * @Description:
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 4765262125316155792L;
    private String name;
    private Integer age;
    private Date birthday;
    private transient String gender; // 变量将不会成为序列化的一部分

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}
