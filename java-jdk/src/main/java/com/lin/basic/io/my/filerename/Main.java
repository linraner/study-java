package com.lin.basic.io.my.filerename;

import java.io.File;
import java.nio.file.Files;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-04 11:09
 * @Description: 文件夹文件批量重命名
 **/
public class Main {
    public static void main(String[] args) {
        String path = "D:/program/java/my/java-study/java-jdk/src/main/java/com/lin/basic/io/my/filerename/file";
        String suffix = ".txt";
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.out.println("不是文件夹");
            return;
        }
        String[] files = file.list();
        File f = null;
        String newFileName = "";
        String oldFileName = "";
        for (int i = 0; i < files.length; i++) {
            oldFileName = files[i];
            newFileName = String.valueOf(i) + suffix;
            f = new File(path + "/" + oldFileName);
            f.renameTo(new File(path + "/" + newFileName));

        }
    }
}
