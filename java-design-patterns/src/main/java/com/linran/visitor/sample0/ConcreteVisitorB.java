package com.linran.visitor.sample0;

public class ConcreteVisitorB implements IVisitor{

  @Override
  public void visit(ConcreteElementA element) {
    int operate = element.operateA();
    System.out.println(
        "class: " +
            element.getClass().getSimpleName() +
            " result: " +
            operate
    );

  }

  @Override
  public void visit(ConcreteElementB element) {
    int operate = element.operateB();
    System.out.println(
        "class: " +
            element.getClass().getSimpleName() +
            " result: " +
            operate
    );

  }
}
