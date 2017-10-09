package com.leetcode.ds.hard;

import java.util.HashMap;
import java.util.Map;

public class P146LruCache {
  /**
   * A version of doubly linked list tailored to LRU Cache.
   */
  private static class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node() {
    }

    public Node(int key, int value) {
      this.key = key;
      this.value = value;

      prev = next = this;
    }

    void detach() {
      prev.next = next;
      next.prev = prev;
    }

    void addToLeft(Node left) {
      left.next = this;
      left.prev = prev;
      prev.next = left;
      prev = left;
    }
  }

  private Map<Integer, Node> cache;
  private Node head;
  private int capacity;

  /**
   * Parametrized constructor.
   * @param capacity maximum number of elements the cache can hold.
   */
  public P146LruCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>(capacity);
    this.head = new Node(-1, -1);
  }

  /**
   * Returns the value for key or -1 is cache does not contain the key. O(1) time complexity.
   * @param key key
   * @return int
   */
  public int get(int key) {
    Node node = cache.get(key);
    int res = -1;
    if (node != null) {
      res = node.value;
      node.detach();
      head.addToLeft(node);
    }
    return res;
  }

  /**
   * Adds the key value pair to cache. Evicts the least recently used entry in case the
   * cache is full.
   * @param key key
   * @param value value
   */
  public void put(int key, int value) {
    Node node = cache.get(key);
    if (node != null) {
      node.value = value;
      node.detach();
    } else {
      if (cache.size() == capacity) {
        node = head.next;
        node.detach();
        cache.remove(node.key);
      }
      node = new Node(key, value);
      cache.put(key, node);
    }
    head.addToLeft(node);
  }
}
