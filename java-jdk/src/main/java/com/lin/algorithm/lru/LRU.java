package com.lin.algorithm.lru;

import java.util.HashMap;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-02 15:18
 * @Description: LRU cache 最近用到的数据被重用的概率比最早用到的数据打的多,高效的cache 双向链表 cache命中随机 与load顺序无关 双向链表插入删除速度快 + HashMap 访问速度快
 **/
public class LRU {

  int capacity;
  HashMap<Integer, Node> map = new HashMap<>();
  Node head = null;
  Node end = null;

  public LRU(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      remove(n);
      setHead(n);
      printNodes("get");
      return n.value;
    }
    printNodes("get");
    return -1;
  }

  public void set(int key, int value) {
    if (map.containsKey(key)) {
      Node old = map.get(key);
      old.value = value;
      remove(old);
      setHead(old);
    } else {
      Node created = new Node(key, value);
      if (map.size() > capacity) {
        map.remove(end.key);
        remove(end);
        setHead(created);
      } else {
        setHead(created);
      }
      map.put(key, created);
    }
    printNodes("set");
  }

  public void setHead(Node node) {
    node.next = head;
    node.pre = null;
    if (head != null) {
      head.pre = node;
    }
    head = node;
    if (end == null) {
      end = head;
    }

  }

  public void remove(Node node) {
    if (node.pre != null) {
      node.pre.next = node.next;
    } else {
      head = node.next;
    }
    if (node.next != null) {
      node.next.pre = node.pre;
    } else {
      end = node.pre;
    }
  }

  public void printNodes(String explain) {
    System.out.println(explain + " : " + head.toString());
    Node node = head.next;
    while (node != null) {
      System.out.println(node.toString());
      node = node.next;
    }
    System.out.println();
  }
}

