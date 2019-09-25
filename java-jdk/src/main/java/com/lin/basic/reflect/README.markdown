## 三种方法获取Class对象
```java
Class.forName();

// 直接获取某一对象的class
Class<?> clazz = int.class();
Class<?> classInt = Integer.TYPE;

// 调用某个对象的getClass方法
StrngBuilder sb = new StringBuilder("123");
Class<?> clazz = sb.getClass();
```

## 两种方法创建实例

```java
Class<?> c = String.class;
Object object = c.newInstacne();

// 使用构造器创建
Class<?> c = String.class;
Constuctor constuctor = c.getConstructor(String.class);
Object obj = constructor.newInstance("123");
```

