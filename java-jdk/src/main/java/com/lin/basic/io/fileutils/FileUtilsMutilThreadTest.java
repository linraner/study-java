package com.lin.basic.io.fileutils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUtilsMutilThreadTest {

  private static String prefix = "/Users/linran/tmp/";

  public static void main(String[] args) throws IOException {
    ExecutorService executorService = Executors.newFixedThreadPool(2000);

    testWrite(executorService);

    executorService.shutdown();
    System.out.println("finish");

    // 多线程 文件 io 写 不保证顺序 可以 保证结果
    check();
  }

  static void check() throws IOException {
    List<Long> list = FileUtils.readLines(new File(prefix + "test1"), "UTF-8")
        .stream()
        .filter(x -> !StringUtils.isEmpty(x))
        .map(Long::valueOf)
        .distinct()
        .collect(Collectors.toList());

    System.out.println(list.size());
  }


  static void testWrite(ExecutorService executorService) throws IOException {
    for (int i = 1; i <= 10000; i++) {
      int finalI = i;
      executorService.submit(() -> {
        try {
          write((long) finalI);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  static void write(Long id) throws IOException {
    FileUtils.write(new File(prefix + "test1"), id + "\n", "UTF-8", true);
  }

}
