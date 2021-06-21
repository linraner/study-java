package com.linran.leetcode.problem23;

import com.sun.imageio.plugins.jpeg.JPEGStreamMetadataFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]] 输出：[1,1,2,3,4,4,5,6] 解释：链表数组如下： [ 1->4->5, 1->3->4, 2->6 ] 将它们合并到一个有序链表中得到。 1->1->2->3->4->4->5->6 示例 2：
 * <p>
 * 输入：lists = [] 输出：[] 示例 3：
 * <p>
 * 输入：lists = [[]] 输出：[]  
 * <p>
 * 提示：
 * <p>
 * k == lists.length 0 <= k <= 10^4 0 <= lists[i].length <= 500 -10^4 <= lists[i][j] <= 10^4 lists[i] 按 升序 排列 lists[i].length 的总和不超过 10^4
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem23 {

  public static void main(String[] args) {



  }

}

class ListNode {

  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length <=0) {
      return new ListNode();
    }
    if (lists.length == 1) {
      return lists[0];
    }
    Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
    for (ListNode node : lists) {
      if (node != null) {
        pq.offer(node);
      }
    }
    ListNode target = new ListNode(-1);
    while (!pq.isEmpty()) {

    }

    return target.next;
  }
}