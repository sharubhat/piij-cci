package interview.adobe;

public class MirrorTree<T> {
  /**
   * Takes a binary tree and creates in-place mirror image.
   * @param root Root node of input binary tree
   * @return mirrored tree
   */
  public BinaryTreeNode<T> mirror(BinaryTreeNode<T> root) {
    if (root != null) {
      BinaryTreeNode<T> leftChild = mirror(root.getRightChild());
      BinaryTreeNode<T> rightChild = mirror(root.getLeftChild());
      root.setLeftChild(leftChild);
      root.setRightChild(rightChild);
    }
    return root;
  }

  static class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> leftChild;
    BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T data) {
      this.data = data;
    }

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public BinaryTreeNode<T> getLeftChild() {
      return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
      this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
      return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
      this.rightChild = rightChild;
    }
  }
}
