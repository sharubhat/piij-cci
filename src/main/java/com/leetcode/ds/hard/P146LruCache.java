package com.leetcode.ds.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/description/
 * Let's consider a constant stream of cache requests with a cache capacity of 3,
 * see below:
 * A, B, C, A, A, A, A, A, A, A, A, A, A, A, B, C, D
 * If we just consider a Least Recently Used (LRU) cache with
 * a HashMap + doubly linked list implementation with
 * O(1) eviction time and O(1) load time, we would have  the following elements
 * cached while processing the caching requests as mentioned above.
 * [A]
 * [A, B]
 * [A, B, C]
 * [B, C, A] <- a stream of As keeps A at the head of the list.
 * [C, A, B]
 * [A, B, C]
 * [B, C, D] <- here, we evict A
 */
public class P146LruCache {
  private static class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
      this.key = key;
      this.value = value;

      prev = next = this;
    }

    void detach() {
      prev.next = next;
      next.prev = prev;
    }

    // add to left of head is equivalent to adding to end of list
    // if the list were singly linked
    void addToLeft(Node left) {
      left.next = this;
      this.prev = left;
      prev.next = left;
      left.prev = prev;
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

  public static void main(String[] args) {
    P146LruCache p = new P146LruCache(2);
    p.put(1,1);
    p.put(2,2);
    p.get(1);
    p.put(3,3);
    p.get(2);
    p.put(4,4);
    p.get(1);
    p.get(3);
    p.get(4);
  }
}