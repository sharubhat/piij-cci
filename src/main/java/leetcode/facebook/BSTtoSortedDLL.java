package leetcode.facebook;

import java.util.Stack;

public class BSTtoSortedDLL {
  static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
      this.val = val;
    }
  }

  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }
    Node head = new Node(0);
    Node prev = head;
    Stack<Node> stack = new Stack<>();

    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      join(prev, root);
      prev = prev.right;
      root = root.right;
    }
    head = head.right;
    join(prev, head);
    return head;
  }

  private void join(Node a, Node b) {
    a.right = b;
    b.left = a;
  }

  public static void main(String[] args) {
    Node root = new Node(4);
    root.left = new Node(2);
    root.left.left = new Node(1);
    root.left.right = new Node(3);
    root.right = new Node(5);
    new BSTtoSortedDLL().treeToDoublyList(root);
  }
}
