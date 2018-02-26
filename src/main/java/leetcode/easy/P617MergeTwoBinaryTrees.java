package com.leetcode.easy;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 */
public class P617MergeTwoBinaryTrees {
  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      this.val = x;
    }
  }

  /**
   * Recursive solution to merge two trees.
   * @param t1 first tree
   * @param t2 second tree
   * @return TreeNode
   */
  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 != null) {
      if (t2 != null) {
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
      }
    } else {
      t1 = t2;
    }
    return t1;
  }
}
