package leetcode.hard.trees;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class P124BinaryTreeMaximumPathSum {
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      this.val = x;
    }
  }

  int max = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    maxPathSumHelper(root);
    return max;
  }

  private int maxPathSumHelper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = Math.max(0, maxPathSumHelper(root.left));
    int right = Math.max(0, maxPathSumHelper(root.right));
    max = Math.max(max, left + right + root.val);
    return Math.max(left, right) + root.val;
  }
}
