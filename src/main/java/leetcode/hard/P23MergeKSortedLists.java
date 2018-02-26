package leetcode.hard;

import java.util.PriorityQueue;

public class P23MergeKSortedLists {
  private static class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      StringBuffer sb = new StringBuffer();
      ListNode head = this;
      while(head != null) {
        sb.append(head.val + " ");
        head = head.next;
      }
      return sb.toString();
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
    for (ListNode l : lists) {
      while(l != null) {
        ListNode tmp = l;
        l = l.next;
        tmp.next = null;
        pq.add(tmp);
      }
    }
    ListNode head = new ListNode(0);
    ListNode tmp = head;
    while(!pq.isEmpty()) {
      tmp.next = pq.poll();
      tmp = tmp.next;
    }
    return head.next;
  }

  public static void main(String[] args) {
    ListNode l = new ListNode(-2);
    l.next = new ListNode(-1);
    l.next.next = new ListNode(-1);
    l.next.next.next = new ListNode(-1);
    ListNode[] list = new ListNode[]{l, null};
    System.out.println(new P23MergeKSortedLists().mergeKLists(list));
  }
}
