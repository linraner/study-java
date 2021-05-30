package com.linran.chain.sample1;

import com.google.common.base.Preconditions;

public class AuthHandle extends Handle{


  @Override
  public void doHandle(Member member) {
    System.out.println("auth");
    Preconditions.checkNotNull(member);
    // chk admin
    Preconditions.checkArgument("admin".equals(member.getRoleName()), "not admin");
    System.out.println("admin handle");
  }
}
