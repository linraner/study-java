package com.linran.state.sample0;

public class Client {

  public static void main(String[] args) {
    AppContext context = new AppContext();
    context.favorite();
    context.comment("评论 ");
  }
}
