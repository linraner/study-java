package com.linran.chain.sample1;

import lombok.Data;

@Data
public class Member {

  private String loginName;
  private String loginPass;
  private String roleName;

  public static Member ofLogin(
      String loginName,
      String loginPass
  ) {
    Member member = new Member();
    member.setLoginName(loginName);
    member.setLoginPass(loginPass);
    return member;
  }

}
