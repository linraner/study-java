package com.linran.testserver0;

import com.google.common.collect.Lists;
import com.hello.HelloService;
import com.hello.QueryUserRequest;
import com.hello.User;
import com.linran.annotation.RpcService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@RpcService(value = HelloService0.class, version = "1.0")
@Slf4j
public class HelloService0 implements HelloService {


  @Override
  public String hello(Date now) {
    log.info("call date : {}", now);
    return LocalDateTime.now().toString();
  }

  @Override
  public List<User> queryUser(QueryUserRequest request) {
    log.info("request:{}", request);
    return Lists.newArrayList(
        User.mock(),
        User.mock(),
        User.mock()
    );
  }
}
