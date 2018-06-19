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
    // if child path sum is negative, ignore it by setting it to 0
    int leftMax = Math.max(0, maxPathSumHelper(root.left));
    int rightMax = Math.max(0, maxPathSumHelper(root.right));
    // calculate max sum at current node to calculate global max
    max = Math.max(max, leftMax + rightMax + root.val);
    // return only max of left or right and curr val to find max sum at parent
    return Math.max(leftMax, rightMax) + root.val;
  }
}
