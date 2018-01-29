package com.leetcode.ds.medium;

public class P114FlattenBinaryTreeToLinkedList {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public void flatten(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.left != null) {
        TreeNode subtree = curr.left;
        while (subtree.right != null) {
          subtree = subtree.right;
        }
        subtree.right = curr.right;
        curr.right = curr.left;
        curr.left = null;
      }
      curr = curr.right;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    new P114FlattenBinaryTreeToLinkedList().flatten(root);
  }
}
