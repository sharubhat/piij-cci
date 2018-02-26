package com.cci.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HashMapList<T, E> {
  private HashMap<T, ArrayList<E>> map = new HashMap<>();

  /**
   * Insert an item into list at key.
   * @param key key
   * @param item value
   */
  public void put(T key, E item) {
    if (!map.containsKey(key)) {
      map.put(key, new ArrayList<>());
    }
    map.get(key).add(item);
  }

  /**
   * Insert a list into list at key.
   * @param key key
   * @param items list of values
   */
  public void put(T key, ArrayList<E> items) {
    map.put(key, items);
  }

  public ArrayList<E> get(T key) {
    return map.get(key);
  }

  public boolean containsKey(T key) {
    return map.containsKey(key);
  }

  /**
   * Check if list at key contains value.
   * @param key key
   * @param item value
   * @return boolean
   */
  public boolean containsKeyValue(T key, E item) {
    if (!map.containsKey(key)) {
      return map.get(key).contains(item);
    }
    return false;
  }

  public Set<T> keySet() {
    return map.keySet();
  }
}
