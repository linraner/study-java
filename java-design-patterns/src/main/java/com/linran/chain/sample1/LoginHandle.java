package com.linran.chain.sample1;

public class LoginHandle extends Handle{

  @Override
  public void doHandle(Member member) {
    System.out.println("login");
    member.setRoleName("admin");
    handleNext(member);
  }
}
