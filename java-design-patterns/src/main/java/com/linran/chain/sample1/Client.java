package com.linran.chain.sample1;

public class Client {

  public static void main(String[] args) {
    MemberService memberService = new MemberService();
    memberService.login("test", "test");
  }

}
