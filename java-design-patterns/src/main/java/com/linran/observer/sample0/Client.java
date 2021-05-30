package com.linran.observer.sample0;

public class Client {

  public static void main(String[] args) {
    EventListener listener = new MouseListener();

    Mouse mouse = new Mouse();
    mouse.addListener(MouseEventType.ON_CLICK, listener);
    mouse.addListener(MouseEventType.ON_UP, listener);

    mouse.click();
    mouse.up();
  }

}
