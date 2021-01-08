package com.lin.my.downloader;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 14:57
 * @Description: Java实现多线程下载
 **/

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * url: https://mirrors.tuna.tsinghua.edu.cn/anaconda/archive/Anaconda-1.4.0-Windows-x86_64.exe
 */
public class HttpDownload {
    private boolean resumable;
    private URL url;
    private File localFile;
    private int[] endPoint;
    private Object waiting = new Object();//作为同步对象使用
    private AtomicInteger downloadedBytes = new AtomicInteger(0);
    private AtomicInteger aliveThreads = new AtomicInteger(0);
    private boolean multiThreaded = true;
    private int fileSize = 0;
    private int threadNum = 5;
    private int timeOut = 5000;
    private final int MIN_SIZE = 2 << 20;

    public static void main(String[] args) throws IOException {
        String url = "https://mirrors.tuna.tsinghua.edu.cn/archlinux/community/os/x86_64/adobe-source-han-sans-hk-fonts-2.001-1-any.pkg.tar.xz";
        String localPath = "~/tmp/";
//        String url = "http://mirrors.163.com/debian/ls-lR.gz";
        new HttpDownload(url, "~/tmp/test.pkg.tar.xz", 1, 5000).get();
    }

    public HttpDownload(String url, String localPath) throws MalformedURLException {
        this.url = new URL(url);
        this.localFile = new File(localPath);
    }

    public HttpDownload(String url, String localPath, int threadNum, int timeOut) throws MalformedURLException {
        this(url, localPath);
        this.threadNum = threadNum;
        this.timeOut = timeOut;
    }

    public void get() throws IOException {
        long startTime = System.currentTimeMillis();
        resumable = supportResumeDownload();
        if (!resumable || threadNum == 1 || fileSize < MIN_SIZE) multiThreaded = false;
        if (!multiThreaded) {
            new DownloadThread(0, 0, fileSize - 1).start();
        } else {
            endPoint = new int[threadNum + 1];
            int block = fileSize / threadNum;
            for (int i = 0; i < threadNum; i++) {
                endPoint[i] = block + 1;
            }
            endPoint[threadNum] = fileSize;
            for (int i = 0; i < threadNum; i++) {
                new DownloadThread(i, endPoint[i], endPoint[i + 1] - 1).start();
            }
        }

        startDownloadMonitor();
        synchronized (waiting) {
            try {
                waiting.wait();
            } catch (InterruptedException e) {
                System.err.println("Download interrupted!");
            }
        }

        cleanTempFile();
        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println("All file successfully downloaded.");
        System.out.println(String.format("All time used: %.3f s, Average speed: %d KB/s",
                timeElapsed / 1000.0, downloadedBytes.get() / timeElapsed));
    }

    // 检测文件是否支持多线程下载,断点续传
    public boolean supportResumeDownload() throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Range", "bytes=0-");
        int code;
        while (true) {
            con.connect();
            fileSize = con.getContentLength();
            code = con.getResponseCode();
            con.disconnect();
            break;
        }
        if (code == 206) {
            System.out.println("Support resume download!");
            return true;
        } else {
            System.out.println("Doesn't support resume download!");
            return false;
        }
    }

    // 检测单个线程下载速度和状态, 通知主线程
    public void startDownloadMonitor() {
        Thread downloadMonitor = new Thread(() -> {
            int pre = 0;
            int cur = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cur = downloadedBytes.get();
                System.out.println(String.format("Speed: %d KB/s, Downloaded: %d KB (%.2f%%), Threads: %d",
                        (cur - pre) >> 10, cur >> 10, cur / (float) fileSize * 100, aliveThreads.get()));
                pre = cur;
                if (aliveThreads.get() == 0) {
                    synchronized (waiting) {
                        waiting.notifyAll();
                    }
                }
            }
        });
        downloadMonitor.setDaemon(true);
        downloadMonitor.start();
    }

    // 下载线程
    class DownloadThread extends Thread {
        private int id;
        private int start;
        private int end;
        private OutputStream out;

        public DownloadThread(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
            aliveThreads.incrementAndGet();
        }

        @Override
        public void run() {
            boolean success = false;
            while (true) {
                success = download();
                if (success) {
                    System.out.println("* Downloaded part " + (id + 1));
                    break;
                } else {
                    System.out.println("Retry to download part " + (id + 1));
                }
            }
        }

        // 下载range指定范围部分
        public boolean download() {
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Range", String.format("bytes=%d-%d", start, end));
                con.setConnectTimeout(timeOut);
                con.setReadTimeout(timeOut);
                con.connect();
                int partSize = con.getHeaderFieldInt("Content-Length", -1);
                if (partSize != end - start + 1) {
                    return false;
                }
                if (out == null) {
                    out = new FileOutputStream(localFile.getAbsolutePath() + "." + id + ".tmp");
                }
                try {
                    InputStream in = con.getInputStream();
                    byte[] buffer = new byte[1024];
                    int size;
                    while (start <= end && (size = in.read(buffer)) > 0) {
                        start += size;
                        downloadedBytes.addAndGet(size);
                        out.write(buffer, 0, size);
                        out.flush();
                    }
                    con.disconnect();
                    if (start <= end) return false;
                    else out.close();

                } catch (SocketTimeoutException e) {
                    System.out.println("Part " + (id + 1) + " Reading timeout.");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("Part " + (id + 1) + " encountered error.");
                return false;
            }
            return true;
        }
    }

    // 对临时文件合并
    public void cleanTempFile() throws IOException {
        if (multiThreaded) {
            merge();
            System.out.println("All temp file merged!");
        } else {
            Files.move(Paths.get(localFile.getAbsolutePath() + ".0.tmp"),
                    Paths.get(localFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // 合并文件
    public void merge() {
        try (OutputStream out = new FileOutputStream(localFile)) {
            byte[] buffer = new byte[1024];
            int size;
            for (int i = 0; i < threadNum; i++) {
                String tempFile = localFile.getAbsolutePath() + "." + i + ".tmp";
                InputStream in = new FileInputStream(tempFile);
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }
                in.close();
                Files.delete(Paths.get(tempFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
