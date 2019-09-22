package com.lin.basic;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 13:47
 * @Description:一段有趣的代码
 * 程序开始，或运算的第一个条件不满足，就去执行第二个条件，
 * 第二个条件在构造代码块中调用 Ls 静态方法，
 * 这次 main 函数参数是 null，if 判断第一个条件就满足，就不去判断第二个条件，
 * 直接输出 a 然后创建的新对象转化成字符串不等于 123，
 * 所以第二个条件也不满足，就输出 b.
 **/
public class Ls {
    public static void main(String[] args) {
        // @linran 只不过是调用了2次main方法而已
        if (args == null || new Ls(){{Ls.main(null);}}.equals("123")) {
            System.out.print("a");
        } else {
            System.out.print("b");
        }
    }
}
