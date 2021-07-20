package com.hello;

import java.util.Date;
import java.util.List;

public interface HelloService {

  String hello();

  String hello(Date now);

  List<User> queryUser(QueryUserRequest request);

}
