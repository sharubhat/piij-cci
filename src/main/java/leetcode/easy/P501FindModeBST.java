package com.leetcode.easy;

import com.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 */
public class P501FindModeBST {
  private int currVal;
  private int currCount;
  private int maxCount;
  private int modeCount;

  private int[] modes;

  public int[] findMode(TreeNode root) {
    inorder(root);
    modes = new int[modeCount];
    modeCount = 0;
    currCount = 0;
    inorder(root);
    return modes;
  }

  private void inorder(TreeNode root) {
    if (root != null) {
      inorder(root.left);
      handleValue(root.val);
      inorder(root.right);
    }
  }

  private void handleValue(int val) {
    if (currVal != val) {
      currVal = val;
      currCount = 0;
    }
    currCount++;
    if (currCount > maxCount) {
      maxCount = currCount;
      modeCount = 1;  // we found a new max, so there can be only one mode
    } else if (currCount == maxCount) {
      if (modes != null) {
        modes[modeCount] = currVal;
      }
      modeCount++;
    }
  }
}
