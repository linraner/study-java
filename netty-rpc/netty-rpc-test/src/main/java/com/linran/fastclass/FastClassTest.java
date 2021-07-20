package com.linran.fastclass;

import com.hello.HelloService;
import com.linran.testserver0.HelloService0;
import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.reflect.FastClass;
import org.junit.Test;

public class FastClassTest {

  @Test
  public void testHello() throws InvocationTargetException {
    HelloService0 instance = new HelloService0();
    FastClass fastClass = FastClass.create(HelloService.class);
    String methodName = "hello";
    Class<?>[] paramTypes = new Class[0];
    Object[] params = new Object[0];
    int index = fastClass.getIndex(methodName, paramTypes);
    System.out.println(index);
    Object result = fastClass.invoke(
        index,
        instance,
        params
    );
    System.out.println(result);
  }


}
