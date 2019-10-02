package com.lin.algorithm.lru;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-02 15:21
 * @Description:
 **/
public class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
