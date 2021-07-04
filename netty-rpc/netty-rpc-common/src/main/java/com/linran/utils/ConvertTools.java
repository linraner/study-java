package com.linran.utils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ConvertTools {


  public static <T, R> List<R> toList(List<T> sources, Function<T, R> function) {
    if (CollectionsUtils.isEmpty(sources)) {
      return Collections.emptyList();
    }
    return sources.stream().map(function).collect(Collectors.toList());
  }

}
