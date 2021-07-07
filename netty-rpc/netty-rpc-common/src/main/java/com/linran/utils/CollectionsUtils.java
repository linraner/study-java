package com.linran.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CollectionsUtils {


  public static <T> boolean isEmpty(Collection<T> list) {
    return list == null || list.size() == 0;
  }

}
