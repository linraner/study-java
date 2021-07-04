package com.linran.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.springframework.util.CollectionUtils;

public final class Assert {

  public Assert() {
  }

  public static void state(boolean expression, String message) {
    if (!expression) {
      throw new IllegalStateException(message);
    }
  }


  public static void state(boolean expression, Supplier<String> messageSupplier) {
    if (!expression) {
      throw new IllegalStateException(nullSafeGet(messageSupplier));
    }
  }


  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }


  public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
    if (!expression) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }

  public static void isNull(@Nullable Object object, String message) {
    if (object != null) {
      throw new IllegalArgumentException(message);
    }
  }


  public static void isNull(@Nullable Object object, Supplier<String> messageSupplier) {
    if (object != null) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }


  @Deprecated
  public static void isNull(@Nullable Object object) {
    isNull(object, "[Assertion failed] - the object argument must be null");
  }


  public static void notNull(@Nullable Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }


  public static void notNull(@Nullable Object object, Supplier<String> messageSupplier) {
    if (object == null) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }

  public static void noNullElements(@Nullable Object[] array, String message) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new IllegalArgumentException(message);
        }
      }
    }
  }


  public static void noNullElements(@Nullable Object[] array, Supplier<String> messageSupplier) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
      }
    }
  }


  @Deprecated
  public static void noNullElements(@Nullable Object[] array) {
    noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
  }


  public static void notEmpty(@Nullable Collection<?> collection, String message) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new IllegalArgumentException(message);
    }
  }


  public static void notEmpty(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }

  public static void noNullElements(@Nullable Collection<?> collection, String message) {
    if (collection != null) {
      for (Object element : collection) {
        if (element == null) {
          throw new IllegalArgumentException(message);
        }
      }
    }
  }


  public static void noNullElements(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
    if (collection != null) {
      for (Object element : collection) {
        if (element == null) {
          throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
      }
    }
  }


  public static void notEmpty(@Nullable Map<?, ?> map, String message) {
    if (CollectionUtils.isEmpty(map)) {
      throw new IllegalArgumentException(message);
    }
  }


  public static void notEmpty(@Nullable Map<?, ?> map, Supplier<String> messageSupplier) {
    if (CollectionUtils.isEmpty(map)) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }


  @Deprecated
  public static void notEmpty(@Nullable Map<?, ?> map) {
    notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }

  private static boolean endsWithSeparator(String msg) {
    return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
  }

  private static String messageWithTypeName(String msg, @Nullable Object typeName) {
    return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
  }

  @Nullable
  private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
    return (messageSupplier != null ? messageSupplier.get() : null);
  }

}
