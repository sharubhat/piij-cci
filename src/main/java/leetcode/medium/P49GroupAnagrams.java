package leetcode.medium;

import java.util.*;

public class P49GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);
      String sorted = new String(chars);
      if (map.containsKey(sorted)) {
        map.get(sorted).add(s);
      } else {
        List<String> set = new ArrayList<>();
        set.add(s);
        map.put(sorted, set);
      }
    }
    return new ArrayList<>(map.values());
  }

  public List<List<String>> groupAnagrams2(String[] strs) {
    Map<String, List<String>> result = new HashMap<>();
    for (String s : strs) {
      int[] lookup = new int[26];
      for (char c: s.toCharArray()) {
        lookup[c - 'a'] += 1;
      }
      String hash = Arrays.toString(lookup);
      if (result.containsKey(hash)) {
        result.get(hash).add(s);
      } else {
        List<String> anagrams = new ArrayList<>();
        anagrams.add(s);
        result.put(hash, anagrams);
      }
    }
    return new ArrayList<>(result.values());
  }
}
