package com.linran.chain.sample1;

public class MemberService {

  public void login(String loginName, String pwd) {
    Handle.Builder builder = new Handle.Builder<>();
    builder.addHandle(new ValidateHandle())
        .addHandle(new LoginHandle())
        .addHandle(new AuthHandle())
        .build()
        .doHandle(Member.ofLogin(loginName, pwd));
  }

}
