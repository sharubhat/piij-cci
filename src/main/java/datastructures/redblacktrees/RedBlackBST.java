package datastructures.redblacktrees;

public class RedBlackBST<K extends Comparable<K>, V> {
  private class Node {
    private K key;
    private V val;
    private Node left;
    private Node right;
    private Color color;
    private int subtreeSize;

    public Node(K key, V val, Color color, int subtreeSize) {
      this.key = key;
      this.val = val;
      this.color = color;
      this.subtreeSize = subtreeSize;
    }
  }

  private Node root;

  public int size() {
    return size(root);
  }

  private int size(Node n) {
    return n == null ? 0 : n.subtreeSize;
  }

  private boolean isEmpty() {
    return root == null;
  }

  private boolean isRed(Node x) {
    return x != null && x.color.equals(Color.RED);
  }

  // BST search
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key is null");
    }
    return get(root, key);
  }

  private V get(Node root, K key) {
    while (root != null) {
      int cmp = key.compareTo(root.key);
      if (cmp < 0) {
        root = root.left;
      } else if (cmp > 0) {
        root = root.right;
      } else {
        return root.val;
      }
    }
    return null;
  }

  // insertion
  public void put(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException("Key can't be null");
    }
    if (value == null) {
      //      delete(key);
      return;
    }
    root = put(root, key, value);
    root.color = Color.BLACK;
  }

  private Node put(Node root, K key, V value) {
    if (root == null) {
      return new Node(key, value, Color.RED, 1);
    }

    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      root.left = put(root.left, key, value);
    } else if (cmp > 0) {
      root.right = put(root.right, key, value);
    } else {
      root.val = value;
    }

    // fix-up any right-leaning links
    if (isRed(root.right) && !isRed(root.left)) {
      //      root = rotateLeft(root);
    }
    if (isRed(root.left) && isRed(root.left.left)) {
      root = rotateRight(root);
    }
    if (isRed(root.left) && isRed(root.right)) {
      //      flipColors(root);
    }

    root.subtreeSize = size(root.left) + size(root.right) + 1;

    return root;
  }

  // delete

  // helper functions
  private Node rotateRight(Node root) {
    Node x = root.left;
    root.left = x.right;
    x.right = root;
    x.color = x.right.color;
    x.right.color = Color.RED;
    x.subtreeSize = root.subtreeSize;
    root.subtreeSize = size(root.left) + size(root.right) + 1;
    return x;
  }
}
