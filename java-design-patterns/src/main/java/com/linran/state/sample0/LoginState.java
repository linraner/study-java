package com.linran.state.sample0;

public class LoginState extends UserState {

  @Override
  public void favorite() {
    System.out.println("fav success!");
  }

  @Override
  public void comment(String comment) {
    System.out.println(comment);
  }
}
