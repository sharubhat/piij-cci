package com.leetcode.easy;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 */
public class P669TrimBinarySearchTree {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  /**
   * Returns trimmed TreeNode.
   * @param root given TreeNode
   * @param lower lower limit
   * @param upper upper limit
   * @return TreeNode
   */
  public TreeNode trimBst(TreeNode root, int lower, int upper) {
    if (root == null) {
      return root;
    }
    if (root.val < lower) {
      return trimBst(root.right, lower, upper);
    }
    if (root.val > upper) {
      return trimBst(root.left, lower, upper);
    }
    root.left = trimBst(root.left, lower, upper);
    root.right = trimBst(root.right, lower, upper);
    return root;
  }
}
