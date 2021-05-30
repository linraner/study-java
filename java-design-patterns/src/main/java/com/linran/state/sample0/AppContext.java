package com.linran.state.sample0;

public class AppContext {

  public static final UserState LOGIN_STATE = new LoginState();
  public static final UserState UN_LOGIN_STATE = new UnLoginState();

  private UserState currentState = UN_LOGIN_STATE;

  {
    LOGIN_STATE.setAppContext(this);
    UN_LOGIN_STATE.setAppContext(this);
  }

  public void setState(UserState currentState) {
    this.currentState = currentState;
    this.currentState.setAppContext(this);
  }

  public UserState getCurrentState() {
    return currentState;
  }

  public void favorite() {
    this.currentState.favorite();
  }

  public void comment(String comment) {
    this.currentState.comment(comment);
  }

}
