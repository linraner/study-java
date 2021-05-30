package com.linran.chain.sample1;


import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * 非空校验
 */
public class ValidateHandle extends Handle{


  @Override
  public void doHandle(Member member) {
    System.out.println("valid");
    Preconditions.checkNotNull(member);
    Preconditions.checkArgument(!StringUtils.isEmpty(member.getLoginName()));
    Preconditions.checkArgument(!StringUtils.isEmpty(member.getLoginPass()));
    handleNext(member);
  }

}
