package datastructures.trees;

// this program has a bug
public class ListToTree {
  private static class LNode {
    int val;
    LNode next;

    public LNode(int i) {
      this.val = i;
    }

    public void print() {
      LNode head = this;
      while (head != null) {
        System.out.print(head.val + "->");
        head = head.next;
      }
      System.out.println();
    }
  }

  private static class TNode {
    int val;
    TNode left;
    TNode right;

    public TNode(int val, TNode left, TNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public void print() {
      System.out.print(this.val);
      if (this.left != null) {
        this.left.print();
      }
      if (this.right != null) {
        this.right.print();
      }
    }
  }

  public TNode createBst(LNode head) {
    if (head == null) {
      return null;
    }
    LNode mid = getMid(head);
    LNode tail = mid.next;
    mid.next = null;
    //    head.print();
    System.out.println("Mid " + mid.val);
    //    tail.print();

    TNode root = new TNode(mid.val, createBst(head), createBst(tail));
    return root;
  }

  private LNode getMid(LNode head) {
    LNode slow = head;
    LNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    LNode tmp = head;
    while (tmp != null && tmp.next != null && tmp.next.val != slow.val) {
      tmp = tmp.next;
    }
    tmp.next = null;
    return slow;
  }

  public static void main(String[] args) {
    LNode list = createList();
    TNode root = new ListToTree().createBst(list);

    root.print();
  }

  private static LNode createList() {
    LNode head = new LNode(1);
    LNode tmp = head;

    for (int i = 2; i < 8; i++) {
      tmp.next = new LNode(i);
      tmp = tmp.next;
    }
    return head;
  }
}
