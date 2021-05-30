package com.linran.observer.sample0;

public class MouseListener implements EventListener{

  public void onClick(Event event) {
    System.out.println("click "+ "\n" +  event);
  }

  public void onUp(Event event) {
    System.out.println("up "+ "\n" +  event);
  }

}
