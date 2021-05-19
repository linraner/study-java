package com.linran.visitor.sample0;

public class VisitorClient0 {

  public static void main(String[] args) {
    ObjectStructure objectStructure = new ObjectStructure();
    IVisitor visitorA = new ConcreteVisitorA();
    System.out.println("visitorA: ");
    objectStructure.accept(visitorA);

    System.out.println("============");
    System.out.println("visitorB: ");
    IVisitor visitorB = new ConcreteVisitorB();
    objectStructure.accept(visitorB);
  }

}
