package library;

public class LinkedListNode {
  public LinkedListNode next;
  public LinkedListNode prev;
  public LinkedListNode last;
  public int data;

  public LinkedListNode(int data) {
    this.data = data;
  }

  public LinkedListNode(int data, LinkedListNode n, LinkedListNode p) {
    this.data = data;
    this.next = n;
    this.prev = p;
  }

  public void setNext(LinkedListNode n) {
    this.next = n;
    if (this == last) {
      last = n;
    }
    if(n != null && n.prev != this) {
      n.setPrev(this);
    }
  }

  public void setPrev(LinkedListNode p) {
    this.prev = p;
    if (p != null && p.next != this) {
      p.setNext(this);
    }
  }

  public String printForward() {
    if(next != null) {
      return this.data + "->" + next.printForward();
    } else {
      return Integer.toString(this.data);
    }
  }

  public LinkedListNode clone() {
    LinkedListNode newNode = null;
    if (next != null) {
      newNode = next.clone();
    }
    LinkedListNode newHead = new LinkedListNode(data, newNode, null);
    return newHead;
  }
}
