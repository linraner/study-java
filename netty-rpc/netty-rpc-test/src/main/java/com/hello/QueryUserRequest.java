package com.hello;

import java.io.Serializable;
import lombok.Data;

@Data
public class QueryUserRequest implements Serializable {

  private static final long serialVersionUID = -7050693845954005102L;

  private Integer start;
  private Integer limit;

}
