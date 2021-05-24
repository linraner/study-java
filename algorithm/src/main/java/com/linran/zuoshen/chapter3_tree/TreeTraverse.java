package com.linran.zuoshen.chapter3_tree;

public class TreeTraverse {

  /**
   * 根 - 左子树 - 右子树
   */
  public void preOrderRecur(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.println("head : " + head.getValue());
    preOrderRecur(head.getLeft());
    preOrderRecur(head.getRight());
  }


}
