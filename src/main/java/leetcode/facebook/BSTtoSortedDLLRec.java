package leetcode.facebook;

/**
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 * Hint #1
 * The recursion is key. Trust that the recursive call on each sub-tree works and concentrate on
 * assembling the outputs of the recursive calls to build the result. It's too complex to delve
 * into how each recursive call is going to work -- trust that it did work
 * and assemble the answer from there.
 * Hint #2
 * The recursion will go down the tree, recursively changing the small and large sub-trees into lists,
 * and then append those lists together with the parent node to make larger lists.
 * Separate out a utility function append(Node a, Node b) that takes two circular doubly linked lists
 * and appends them together to make one list which is returned. Writing a separate
 * utility function helps move some of the complexity out of the recursive function.
 */
public class BSTtoSortedDLLRec {
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

    // Recursively do the subtrees (leap of faith!)
    Node leftList = treeToDoublyList(root.left);
    Node rightList = treeToDoublyList(root.right);

    // Make the single root node into a list length-1
    // in preparation for the appending
    root.left = root;
    root.right = root;

    // At this point we have three lists, and it's
    // just a matter of appending them together
    // in the right order (aList, root, bList)
    leftList = append(leftList, root);
    leftList = append(leftList, rightList);

    return leftList;
  }

  /*
     helper function -- given two list nodes, join them
     together so the second immediately follow the first.
     Sets the .next of the first and the .previous of the second.
    */
  private void join(Node a, Node b) {
    a.right = b;
    b.left = a;
  }

  /*
     helper function -- given two circular doubly linked
     lists, append them and return the new list.
    */
  private Node append(Node left, Node right) {
    if (left == null) {
      return right;
    } else if (right == null) {
      return left;
    } else {
      Node leftLast = left.left;
      Node rightLast = right.left;

      join(leftLast, right);
      join(rightLast, left);

      return left;
    }
  }
}
