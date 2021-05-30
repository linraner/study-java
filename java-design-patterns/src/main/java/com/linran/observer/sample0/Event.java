package com.linran.observer.sample0;

import java.lang.reflect.Method;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Event {

  private Object source;
  private EventListener target;
  private Method callback;
  private String trigger;
  private Long time;

  public static Event of(EventListener target, Method callback) {
      Event event = new Event();
      event.setTarget(target);
      event.setCallback(callback);
      return event;
  }

  public Event setTrigger(String trigger) {
    this.trigger = trigger;
    return this;
  }


}
