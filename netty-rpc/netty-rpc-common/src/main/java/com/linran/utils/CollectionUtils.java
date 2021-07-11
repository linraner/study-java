package com.linran.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CollectionUtils {


  public static <T> boolean isEmpty(Collection<T> list) {
    return list == null || list.size() == 0;
  }

  public static <T> boolean isNotEmpty(Collection<T> list) {
    return !isEmpty(list);
  }

}
