package com.leetcode.medium;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class P19RemoveNthNodeFromEnd {
  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode voidHead = new ListNode(0);
    ListNode slow = voidHead;
    ListNode fast = voidHead;
    voidHead.next = head;
    for (int i = 0; i <= n; i++) {
      fast = fast.next;
    }
    if (slow == fast) {
      return slow.next;
    }
    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }
    slow.next = slow.next.next;
    return voidHead.next;
  }
}
