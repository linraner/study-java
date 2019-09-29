package com.lin.algorithm.queueandstack;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-29 09:56
 * @Description:设计一个循环队列
 **/

/**
 * Example:
 * MyCircularQueue circularQueue = new MycircularQueue(3); // 设置长度为 3
 *
 * circularQueue.enQueue(1);  // 返回 true
 *
 * circularQueue.enQueue(2);  // 返回 true
 *
 * circularQueue.enQueue(3);  // 返回 true
 *
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 *
 * circularQueue.Rear();  // 返回 3
 *
 * circularQueue.isFull();  // 返回 true
 *
 * circularQueue.deQueue();  // 返回 true
 *
 * circularQueue.enQueue(4);  // 返回 true
 *
 * circularQueue.Rear();  // 返回 4
 *
 *
 /**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
class MyCircularQueue {
    private final int initSize = 11;
    private int size;
    private int[] data;
    private int head;
    private int tail;

    public MyCircularQueue() {
        this.data = new int[initSize];
        this.head = -1;
        this.tail = -1;
        this.size = initSize;
    }
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.data = new int[k];
        this.head = -1;
        this.tail = -1;
        this.size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        if(isEmpty()) return false;
        // tail比size小取模为自身值 大于size在对称的环对面
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        return data[tail];

    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head ==  -1 || head == tail;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}

