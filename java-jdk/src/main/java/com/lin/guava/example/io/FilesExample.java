package com.lin.guava.example.io;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:43
 * @Description:文件的复制,重命名,读取等 TODO:
 **/
public class FilesExample {
//    public static void main(String[] args) {
//        try {
//            FilesExample FilesExample = new FilesExample();
//            // 一个文件内容复制到另一个文件中
//            /*File original = new File("E:\\test\\original");
//            File copy = new File("E:\\test\\copy");
//            if (original.isFile() && copy.isFile()) {
//                Files.copy(original, copy);
//            }*/
//            // 文件重命名
//           /* File originalFile = new File("E:\\test\\1.txt");
//            File newFile = new File("E:\\test\\2.txt");
//            if (originalFile.isFile() && newFile.isFile()) {
//                Files.move(originalFile, newFile);
//            }*/
//            // 将文件内容一行一行读取出来
//            File file = new File("E:\\test\\1.txt");
//            List<String> expectedLines = Lists.newArrayList("The quick brown", " fox jumps over", " the lazy dog");
//            List<String> readLines = Files.readLines(file, Charsets.UTF_8);
//            System.out.printf(readLines.toString());
//            // 为文件生成一个hashcode
//            HashCode hashCode = Files.hash(file, Hashing.md5());
//            System.out.println(hashCode);
//            // 文件 写/新增内容     完全不用去关心打开打开流/关闭流
//            String hamletQuoteStart = "To be, or not to be";
//            Files.write(hamletQuoteStart, file, Charsets.UTF_8);
//            String hamletQuoteEnd = "that is a question";
//            Files.append(hamletQuoteEnd, file, Charsets.UTF_8);
//            // Sources读  Sinks写
//            // Sources 和 Sinks 不是 streams', readers', 或者 writers' 对象
//            // 但是提供相同功能
//            File dest = new File("E:\\test\\2.txt");
//            //dest.deleteOnExit();
//            File source = new File("E:\\test\\1.txt");
//            ByteSource byteSource = Files.asByteSource(source);
//            ByteSink byteSink = Files.asByteSink(dest);
//            byteSource.copyTo(byteSink);
//            System.out.println(byteSink + ", " + byteSource);
//            // 把几个文件内容写到同一个文件下
//            File f1 = new File("E:\\test\\1.txt");
//            File f2 = new File("E:\\test\\2.txt");
//            File f3 = new File("E:\\test\\3.txt");
//            File joinedOutput = new File("E:\\test\\4.txt");
//            //joinedOutput.deleteOnExit();
//            // 为每个文件生成InputSupplier(使用Closer去管理关闭底层I/O资源)
//            List<InputSupplier<InputStreamReader>> inputSuppliers = FilesExample.getInputSuppliers(f1, f2, f3);
//            // 逻辑上合并为一个InputSupplier
//            InputSupplier<Reader> joinedSupplier = CharStreams.join(inputSuppliers);
//            // 建立输出
//            OutputSupplier<OutputStreamWriter> outputSupplier = Files.newWriterSupplier(joinedOutput, Charsets.UTF_8);
//            // 将底层InputSuppliers写到OutputSupplier
//            CharStreams.copy(joinedSupplier, outputSupplier);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String joinFiles(File... files) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        for (File file : files) {
//            builder.append(Files.toString(file, Charsets.UTF_8));
//        }
//        return builder.toString();
//    }
//
//    private List<InputSupplier<InputStreamReader>> getInputSuppliers(File... files) {
//        List<InputSupplier<InputStreamReader>> list = Lists.newArrayList();
//        for (File file : files) {
//            list.add(Files.newReaderSupplier(file, Charsets.UTF_8));
//        }
//        return list;
//    }
}