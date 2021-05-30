package com.linran.objectpool.sample0;

import java.util.Vector;

public class ObjectPool {

  private final int step = 10;
  private final int minCount;
  private final int maxCount;

  private final Vector<IPooledObject> returneds;
  private final Vector<IPooledObject> borroweds;

  public ObjectPool(int minCount, int maxCount) {
    borroweds = new Vector<>();
    returneds = new Vector<>();
    this.minCount = minCount;
    this.maxCount = maxCount;
    refresh(minCount);
  }

  public IPooledObject borrowObject() {
    IPooledObject next = null;
    if (returneds.size() > 0) {
      for (IPooledObject returned : returneds) {
        next = returned;
        returneds.remove(next);
        borroweds.add(next);
        return next;
      }
    } else {
      int count = maxCount - minCount;
      refresh(Math.min(count, step));
    }
    return null;
  }

  public void returnObject(IPooledObject object) {
    returneds.add(object);
    borroweds.remove(object);
  }

  private void refresh(int count) {
    for (int i = 0; i < count; i++) {
      returneds.add(new ConcretePoolObject());
    }
  }

}
