package com.linran.visitor.sample0;

import java.util.Random;

public class ConcreteElementA implements IElement {

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  public int operateA() {
    return new Random().nextInt();
  }
}
