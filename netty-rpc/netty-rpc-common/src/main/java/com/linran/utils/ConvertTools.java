package com.linran.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ConvertTools {


  public static <T, R> List<R> toList(List<T> sources, Function<T, R> function) {
    if (CollectionUtils.isEmpty(sources)) {
      return Collections.emptyList();
    }
    return sources.stream().map(function).collect(Collectors.toList());
  }

  public static <T> Set<T> toSet(Collection<T> coll) {
    if (CollectionUtils.isEmpty(coll)) {
      return Collections.emptySet();
    }
    return new HashSet<>(coll);
  }

}
