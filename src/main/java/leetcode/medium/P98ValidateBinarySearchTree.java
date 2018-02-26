package leetcode.medium;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 */
public class P98ValidateBinarySearchTree {
  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isValidBST(TreeNode root) {
    if (root != null) {
      if (root.left != null) {
        if (root.left.val >= root.val || !isValidBST(root.left)) {
          return false;
        }
      }
      if (root.right != null) {
        if (root.right.val <= root.val || !isValidBST(root.right)) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(15);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(20);
    System.out.println(new P98ValidateBinarySearchTree().isValidBST(root));
  }
}
