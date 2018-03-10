package leetcode.easy;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 */
public class P237DeleteNodeLinkedList {
  private class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public void deleteNode(ListNode node) {
    if (node != null && node.next != null) {
      node.val = node.next.val;
      node.next = node.next.next;
    }
  }
}
