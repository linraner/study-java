package com.linran.observer.sample0;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class EventContext {

  protected Map<String, Event> eventMap = new HashMap<>();

  public void addListener(String eventType, EventListener target, Method callback) {
    eventMap.put(eventType, Event.of(target, callback));
  }

  public void addListener(String eventType, EventListener target) {
    try {
      this.addListener(eventType, target, target.getClass().getMethod("on" +toUpperFirstCase(eventType), Event.class));
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  private String toUpperFirstCase(String eventType) {
    Preconditions.checkNotNull(eventType);
    char[] chars = eventType.toCharArray();
    chars[0] -= 32;
    return String.valueOf(chars);
  }

  private void trigger(Event event) {
    event.setSource(this);
    event.setTime(System.currentTimeMillis());
    try {
      if (event.getCallback() != null) {
        event.getCallback().invoke(event.getTarget(), event);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void trigger(String trigger) {
    if (!this.eventMap.containsKey(trigger)) {
      return;
    }
    trigger(this.eventMap.get(trigger).setTrigger(trigger));
  }
}
