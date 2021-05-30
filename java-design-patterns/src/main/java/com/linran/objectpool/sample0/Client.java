package com.linran.objectpool.sample0;

public class Client {

  public static void main(String[] args) {
    ObjectPool pool = new ObjectPool(10, 50);
    IPooledObject object = pool.borrowObject();
    object.operation();
    pool.returnObject(object);

  }


}
