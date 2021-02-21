package leetcode.hard;

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
public class P146LruCache<K, V> {
  private static class Node<K, V> {
    K key;
    V value;
    Node prev;
    Node next;

    public Node() {
    }

    public Node(K key, V value) {
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

  private Map<K, Node<K, V>> cache;
  private LinkedList list;
  private int capacity;

  /**
   * Parametrized constructor.
   * @param capacity maximum number of elements the cache can hold.
   */
  public P146LruCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>(capacity + 1);
    this.list = new LinkedList();
  }

  /**
   * Returns the value for key or -1 if cache does not contain the key. O(1) time complexity.
   * @param key key
   * @return int
   */
  public V get(K key) {
    if (!cache.containsKey(key)) {
      return null;
    }
    Node<K, V> node = cache.get(key);
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
  public void put(K key, V value) {
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

  public static void main(String[] args) {
    P146LruCache cache = new P146LruCache(2);
    // ["LRUCache","put","put","get","put","get","put","get","get","get"]
    // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    cache.put(1,1);
    cache.put(2,2);
    cache.get(1);
    cache.put(3,3);
    cache.get(2);
    cache.put(4,4);
    cache.get(1);
    cache.get(3);
    cache.get(4);
  }
}