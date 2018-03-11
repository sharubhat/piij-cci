package leetcode.easy;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 */
public class P108ConvertSortedArrayToBST {
  public TreeNode sortedArrayToBST(int[] nums) {
    return toBst(nums, 0, nums.length - 1);
  }

  private TreeNode toBst(int[] nums, int s, int e) {
    if (s <= e) {
      int mid = s + (e - s) / 2;
      TreeNode root = new TreeNode(nums[mid]);
      root.left = toBst(nums, s, mid - 1);
      root.right = toBst(nums, mid + 1, e);
      return root;
    }
    return null;
  }
}
