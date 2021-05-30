package com.linran.objectpool.sample0;

public class ConcretePoolObject implements IPooledObject{

  @Override
  public void operation() {
    System.out.println("doing");
  }
}
