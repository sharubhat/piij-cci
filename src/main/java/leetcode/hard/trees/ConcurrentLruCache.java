package leetcode.hard.trees;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentLruCache {
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
    }
  }

  private static class LinkedList {
    // initialize with two place holder nodes that mark the ends of the list
    private Node first;
    private Node last;

    public LinkedList() {
      first = new Node();
      last = new Node();
      first.next = last;
      last.prev = first;
    }

    void unlink(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    void addLast(Node node) {
      Node lastValidNode = last.prev;
      node.next = last;
      node.prev = lastValidNode;
      lastValidNode.next = last.prev = node;
    }

    Node removeFirst() {
      Node firstValidNode = first.next;
      unlink(firstValidNode);
      return firstValidNode;
    }
  }

  private Map<Integer, Node> cache;
  private LinkedList list;
  private int capacity;

  /**
   * Parametrized constructor.
   * @param capacity maximum number of elements the cache can hold.
   */
  public ConcurrentLruCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>(capacity + 1);
    this.list = new LinkedList();
  }

  /**
   * Returns the value for key or -1 if cache does not contain the key. O(1) time complexity.
   * @param key key
   * @return int
   */
  public int get(int key) {
    if (!cache.containsKey(key)) {
      return -1;
    }
    Node node = cache.get(key);
    list.unlink(node);
    list.addLast(node);
    return node.value;
  }

  /**
   * Adds the key value pair to cache. Evicts the least recently used entry in case the cache is
   * full.
   *
   * @param key key
   * @param value value
   */
  public void put(int key, int value) {
    if (cache.containsKey(key)) {
      list.unlink(cache.get(key));
    }
    Node node = new Node(key, value);
    list.addLast(node);
    cache.put(key, node);
    if (cache.size() > capacity) {
      cache.remove(list.removeFirst().key);
    }
  }
}
