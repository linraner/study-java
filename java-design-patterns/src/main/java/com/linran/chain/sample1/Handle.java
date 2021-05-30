package com.linran.chain.sample1;


public abstract class Handle<T> {

  protected Handle chain;

  public void next(Handle chain) {
    this.chain = chain;
  }

  public abstract void doHandle(Member member);

  public void handleNext(Member member) {
    if (chain != null) {
      chain.doHandle(member);
    }
  }

  public static class Builder<T> {

    private Handle<T> head;
    private Handle<T> tail;

    public Builder<T> addHandle(Handle<T> handle) {
      if (this.head == null) {
        this.head = this.tail = handle;
        return this;
      }
      this.tail.next(handle);
      this.tail = handle;
      return this;
    }

    public Handle<T> build() {
      return this.head;
    }
  }

}
