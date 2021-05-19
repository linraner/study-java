package com.linran.visitor.sample0;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {

  private List<IElement> elements = new ArrayList<IElement>(2);

  {
    elements.add(new ConcreteElementA());
    elements.add(new ConcreteElementB());
  }

  public void accept(IVisitor visitor) {
    for (IElement element : elements) {
      element.accept(visitor);
    }
  }

}
