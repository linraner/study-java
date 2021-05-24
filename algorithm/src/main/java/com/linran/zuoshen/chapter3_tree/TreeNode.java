package com.linran.zuoshen.chapter3_tree;

import lombok.Data;

@Data
public class TreeNode {

  private Integer value;
  private TreeNode left;
  private TreeNode right;

  public TreeNode(Integer value) {
    this.value = value;
  }

  /**
   *
   */
  public static TreeNode newDefault() {
    TreeNode node = new TreeNode(5);
    node.setLeft(
        new TreeNode(4)
            .setLeft(new TreeNode(1))
            .setRight(new TreeNode(2))
    );
    node.setRight(
        new TreeNode(3)
            .setLeft(
                new TreeNode(9)
                    .setLeft(new TreeNode(3)
                        .setRight(new TreeNode(7)))
            )
            .setRight(new TreeNode(10))
    );
    return node;
  }

  public TreeNode setLeft(TreeNode node) {
    this.left = node;
    return this;
  }

  public TreeNode setRight(TreeNode node) {
    this.right = node;
    return this;
  }

}
