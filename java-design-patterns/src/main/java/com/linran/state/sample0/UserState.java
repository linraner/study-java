package com.linran.state.sample0;

public abstract class UserState {

  protected AppContext appContext;

  public void setAppContext(AppContext appContext) {
    this.appContext = appContext;
  }

  public abstract void favorite();

  public abstract void comment(String comment);


}
