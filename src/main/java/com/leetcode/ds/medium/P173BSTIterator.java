package com.leetcode.ds.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 * Requirement says next() and hasNext() should run in average O(1) time and
 * use O(h) memory, where h is the height of the tree. Below implementation satisfies first two
 * conditions but uses O(n) memory. We can use stack instead of list but next() and hasNext() will
 * not be O(1).
 */
public class P173BSTIterator {

  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  private List<TreeNode> itList = new ArrayList<>();
  private int index;

  public P173BSTIterator(TreeNode root) {
    inOrderTraverse(root);
  }

  private void inOrderTraverse(TreeNode root) {
    if (root != null) {
      inOrderTraverse(root.left);
      itList.add(root);
      index++;
      inOrderTraverse(root.right);
    }
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return index > 0 ;
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode next = itList.get(itList.size() - index);
    index--;
    return next.val;
  }
}
