package com.linran.visitor.sample0;

public class ConcreteVisitorA implements IVisitor {

  @Override
  public void visit(ConcreteElementA element) {
    int operate = element.operateA();


  }

  @Override
  public void visit(ConcreteElementB element) {

  }

}
