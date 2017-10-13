package com.leetcode.ds.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/description/
 */
public class P257BinaryTreePaths {
  private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  private List<String> binaryTreePaths(TreeNode root, String path, List<String> paths) {
    if(root != null) {
      if (root.left == null && root.right == null) {
        paths.add(path + root.val);
        return paths;
      }
      if (root.left != null) {
        binaryTreePaths(root.left, path + root.val + "->", paths);
      }
      if (root.right != null) {
        binaryTreePaths(root.right, path + root.val + "->", paths);
      }
    }
    return paths;
  }

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();
    return binaryTreePaths(root, "", paths);
  }
}
