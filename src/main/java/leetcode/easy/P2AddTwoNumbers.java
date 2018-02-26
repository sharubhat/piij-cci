package com.leetcode.easy;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 */
public class P2AddTwoNumbers {
  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      this.val = x;
    }
  }

  private int getVal(ListNode l) {
    return l == null ? 0 : l.val;
  }

  private ListNode getNext(ListNode l) {
    return l == null ? null : l.next;
  }

  /**
   * Adds two numbers represented as linked list and returns the result as new linked list.
   * @param l1 first number
   * @param l2 second number
   * @return ListNode
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int sum = 0;
    int carry = 0;
    ListNode res = new ListNode(0);
    ListNode curr = res;
    while (l1 != null || l2 != null) {
      sum = getVal(l1) + getVal(l2) + carry;
      carry = sum / 10;
      sum = sum % 10;
      curr.next = new ListNode(sum);
      curr = getNext(curr);
      l1 = getNext(l1);
      l2 = getNext(l2);
    }
    if (carry != 0) {
      curr.next = new ListNode(carry);
    }
    return res.next;
  }
}
