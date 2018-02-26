package com.cci.library;

public class TreeNode {
  public int data;
  public TreeNode left;
  public TreeNode right;
  public TreeNode parent;
  private int size;

  public TreeNode(int d) {
    this.data = d;
    this.size = 1;
  }

  public int size() {
    return size;
  }

  public int height() {
    int leftHeight = left != null ? left.height() : 0;
    int rightHeight = right != null ? right.height() : 0;
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public boolean isBST() {
    if (left != null) {
      if (data < left.data || !left.isBST()) {
        return false;
      }
    }
    if (right != null) {
      if (data > right.data || !right.isBST()) {
        return false;
      }
    }
    return true;
  }

  public TreeNode find(int d) {
    if (d == data) {
      return this;
    } else if (d <= data) {
      return left != null ? left.find(d) : null;
    } else if (d >= data) {
      return right != null ? right.find(d) : null;
    }
    return null;
  }

  private void setLeftChild(TreeNode left) {
    this.left = left;
    if (left != null) {
      left.parent = this;
    }
  }

  private void setRightChild(TreeNode right) {
    this.right = right;
    if(right != null) {
      right.parent = this;
    }
  }

  public void insertInOrder(int d) {
    if (d <= data) {
      if (left != null) {
        left.insertInOrder(d);
      } else {
        setLeftChild(new TreeNode(d));
      }
    } else {
      if (right != null) {
        right.insertInOrder(d);
      } else {
        setRightChild(new TreeNode(d));
      }
    }
  }

  private static TreeNode createMinimalBST(int[] array, int start, int end) {
    if (end < start) {
      return null;
    }
    int mid = start + (end - start) / 2;
    TreeNode n = new TreeNode(array[mid]);
    n.setLeftChild(createMinimalBST(array, start, mid - 1));
    n.setRightChild(createMinimalBST(array, mid + 1, end));
    return n;
  }

  public static TreeNode createMinimalBST(int[] array) {
    return createMinimalBST(array, 0, array.length - 1);
  }
}
