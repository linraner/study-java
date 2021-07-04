package com.linran.utils;

import java.util.List;

public final class CollectionsUtils {


  public static <T> boolean isEmpty(List<T> list) {
    return list == null || list.size() == 0;
  }

}
