package com.lin.basic.serializable;

import com.lin.basic.serializable.common.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 10:11
 * @Description:
 **/
public class SerializableDemo {

  public static void main(String[] args) {
    // init
    User user = new User();
    user.setName("libai");
    user.setAge(44);
    user.setBirthday(new Date());
    user.setGender("man");
    System.out.println("User: " + user);

    // write Ojb to file
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream("userFile"));
      oos.writeObject(user);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        oos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // read file to Obj
    File file = new File("userFile");
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(file));
      User newUser = (User) ois.readObject();
      System.out.println("newUser: " + newUser);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        ois.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      file.delete();
    }

  }
}
