package com.leetcode.ds.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/keyboard-row/description/
 */
public class P500KeyboardRow {
  /**
   * Returns list of words whose characters are in a single row of US keyboard.
   * @param words input list of words
   * @return String[]
   */
  public String[] findWords(String[] words) {
    List<String> result = new ArrayList<>();
    Map<Character, Integer> map = prepareMap();
    for (int i = 0; i < words.length; i++) {
      String word = words[i].toUpperCase();
      int currentRow = map.get(word.charAt(0));
      boolean count = true;
      for (char c: word.toCharArray()) {
        if (map.get(c) != currentRow) {
          count = false;
          break;
        }
      }
      if (count) {
        result.add(words[i]);
      }
    }
    return result.toArray(new String[result.size()]);
  }

  private Map<Character, Integer> prepareMap() {
    Map<Character, Integer> map = new HashMap<>();
    map.put('Q', 1);
    map.put('W', 1);
    map.put('E', 1);
    map.put('R', 1);
    map.put('T', 1);
    map.put('Y', 1);
    map.put('U', 1);
    map.put('I', 1);
    map.put('O', 1);
    map.put('P', 1);
    map.put('A', 2);
    map.put('S', 2);
    map.put('D', 2);
    map.put('F', 2);
    map.put('G', 2);
    map.put('H', 2);
    map.put('J', 2);
    map.put('K', 2);
    map.put('L', 2);
    map.put('Z', 3);
    map.put('X', 3);
    map.put('C', 3);
    map.put('V', 3);
    map.put('B', 3);
    map.put('N', 3);
    map.put('M', 3);
    return map;
  }
}
