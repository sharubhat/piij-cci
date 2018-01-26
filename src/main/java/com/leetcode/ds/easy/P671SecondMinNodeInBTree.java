package com.leetcode.ds.easy;

import java.util.Stack;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 * Note: root is the smallest element. We need to find the second smallest.
 */
public class P671SecondMinNodeInBTree {
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int findSecondMinimumValue(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    int rootVal = root.val;
    int secMin = Integer.MAX_VALUE;
    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if (root.val != rootVal && root.val < secMin) {
        secMin = root.val;
      }
      root = root.right;
    }
    return secMin != Integer.MAX_VALUE ? secMin : -1;
  }
}
