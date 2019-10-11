# Lambda实现函数接口

## 替代匿名内部类

```
// jdk7
new Thread(new Runnable() {
    @Override
    public void run() {
        // do
    }
}).start();

// jdk8
new Thread(() ->{
    // do
}).start();
```

## 带参函数简写
```
List<String> list = Arrays.asList("Bye", "hello", "ok", "QWQ", "asda");
// jdk7
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
        if (o1 == null) return -1;
        if (o2 == null) return 1;
        return o1.length() - o2.length();
    }
});

// jdk8
Collections.sort(list, (o1, o2)->{
    if (o1 ==null) return -1;
    if (o2 ==null) return 1;
    return o1.length() - o2.length();
});
```
