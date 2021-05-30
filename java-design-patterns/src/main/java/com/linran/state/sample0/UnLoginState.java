package com.linran.state.sample0;

public class UnLoginState extends UserState {

  @Override
  public void favorite() {
    login();
    this.appContext.getCurrentState().favorite();
  }

  @Override
  public void comment(String comment) {
    login();
    this.appContext.getCurrentState().comment(comment);
  }

  private void login() {
    System.out.println("login");
    this.appContext.setState(AppContext.LOGIN_STATE);
  }
}
