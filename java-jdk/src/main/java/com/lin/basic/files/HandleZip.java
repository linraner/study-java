package com.lin.basic.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;

public class HandleZip {


  public static void main(String[] args) throws IOException {
    String path = "/Users/linran/tmp/sprite.zip";
    String prefix = "";
    File file = new File(path);
    InputStream inputStream = new FileInputStream(file);
    // 从 input 进来
    ZipInputStream zipInputStream = new ZipInputStream(inputStream);

    ZipEntry zipEntry = null;
    ZipFile zipFile = new ZipFile(file);
    Integer count = 0;
    System.out.println(zipFile.size());

    while ((zipEntry = zipInputStream.getNextEntry()) != null) {
      if (!skip(zipEntry)) {
        count++;
        InputStream inputStream1 = zipFile.getInputStream(zipEntry);
        BufferedImage bufferedImage = ImageIO.read(inputStream1);
        System.out.println(zipEntry.getName());
        if (bufferedImage != null) {
          System.out.println(bufferedImage.getWidth());
          System.out.println(bufferedImage.getHeight());
        }
        zipInputStream.closeEntry();
      }
    }

    System.out.println(count);

  }

  private static boolean skip(ZipEntry ze) {
    return ze.isDirectory() || ze.getName() == null || ze.getName().startsWith("__MACOSX");
  }


}
