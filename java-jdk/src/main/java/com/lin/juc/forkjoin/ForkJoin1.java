package com.lin.juc.forkjoin;

import java.util.concurrent.*;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-21 10:54
 * @Description:求数组中的最大值
 **/
public class ForkJoin1 extends RecursiveTask<Integer> {
    private final int threshold = 10;
    private int[] array;
    private int start;
    private int end;


    public ForkJoin1(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int max = Integer.MIN_VALUE;
        if ((end - start) <= threshold) {
            for (int i = start; i <= end; i++) {
                max = Math.max(max, array[i]);
            }
        } else {
            // fork join
            int mid = start + (end - start) / 2;
            ForkJoin1 left = new ForkJoin1(array, start, mid);
            ForkJoin1 right = new ForkJoin1(array, mid + 1, end);
            left.fork();
            right.fork();

            int lm = left.join();
            int rm = left.join();
            max = Math.max(lm, rm);
        }
        return max;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = {100, 400, 200, 90, 80, 300, 600, 10, 20, -10, 30, 2000, 1000};
        ForkJoin1 task = new ForkJoin1(array, 0, array.length - 1);
        ForkJoinTask<Integer> futureTask = pool.submit(task);
        Integer result = add(array);
        System.out.println("Result = " + futureTask.get(1, TimeUnit.SECONDS));

    }

    static Integer add(int[] array) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            result = Math.max(result, array[i]);
        }
        return result;
    }

    public int getThreshold() {
        return threshold;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}


