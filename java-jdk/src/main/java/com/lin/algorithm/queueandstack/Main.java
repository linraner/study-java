package com.lin.algorithm.queueandstack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-29 10:37
 * @Description:常用用法
 **/
public class Main {

  public static void main(String[] args) {
    // 现在Java API将栈和队列封装在这个双向链表里了
    Queue<Integer> q = new LinkedList<>();
    System.out.println("=====================Queue========================");
    System.out.println("The first element: " + q.peek());

    q.offer(5);
    q.offer(7);
    q.offer(12);
    q.offer(2);
    System.out.println("Queue size: " + q.size());
    q.poll();
    System.out.println("The first element: " + q.peek());

    System.out.println("Queue size: " + q.size());
    System.out.println("=====================Stack========================");
    Deque<Integer> s = new LinkedList<>();
    System.out.println("The first element: " + s.peek());

    s.push(5);
    s.push(7);
    s.push(13);
    s.push(19);
    s.push(23);
    System.out.println("Stack size: " + s.size());
    s.poll();
    System.out.println("The first element: " + s.peek());
    System.out.println("Stack size: " + s.size());

  }
}
