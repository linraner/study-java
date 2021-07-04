package com.hello;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class User {

  private Long id;
  private String name;
  private LocalDateTime createAt;

  public static User mock() {
    User user = new User();
    user.setId(new Random().nextLong());
    user.setName("name_" + RandomStringUtils.random(5));
    user.setCreateAt(LocalDateTime.now());
    return user;
  }

}
