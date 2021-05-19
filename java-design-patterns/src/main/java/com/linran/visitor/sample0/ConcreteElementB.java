package com.linran.visitor.sample0;

import java.util.Random;

public class ConcreteElementB implements IElement {

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  public int operateB() {
    return new Random().nextInt();
  }
}
