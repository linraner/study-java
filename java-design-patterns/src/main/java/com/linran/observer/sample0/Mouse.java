package com.linran.observer.sample0;

public class Mouse extends EventContext{

  public void click() {
    System.out.println("click");
    this.trigger(MouseEventType.ON_CLICK);
  }

  public void up() {
    System.out.println("up");
    this.trigger(MouseEventType.ON_UP);
  }

}
