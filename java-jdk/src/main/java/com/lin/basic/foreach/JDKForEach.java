package com.lin.basic.foreach;

import com.lin.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class JDKForEach {



  public static void main(String[] args) {
    List<User> users = new ArrayList<>(5);

    for (int i = 0; i < 5; i++) {
      User user = new User();
      user.setAge(new Random(i).nextInt());
      user.setName("user" + String.valueOf(i));
      users.add(user);
    }
    users.forEach(it -> {
      it.setId(UUID.randomUUID().toString());
    });
    System.out.println(users);

  }

}
