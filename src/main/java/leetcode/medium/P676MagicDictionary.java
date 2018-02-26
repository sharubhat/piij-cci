package leetcode.medium;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/implement-magic-dictionary/discuss/
 * A different approach : https://leetcode.com/articles/implement-magic-dictionary/
 */
public class P676MagicDictionary {

  Map<String, List<int[]>> trie;

  /** Initialize your data structure here. */
  public P676MagicDictionary() {
    trie = new HashMap<>();
  }

  /** Build a dictionary through a list of words */
  public void buildDict(String[] dict) {
    for (String s : dict) {
      for (int i = 0; i < s.length(); i++) {
        String key = s.substring(0, i) + s.substring(i + 1);
        List<int[]> value = trie.getOrDefault(key, new ArrayList<>());
        value.add(new int[]{i, s.charAt(i)});
        trie.put(key, value);
      }
    }
  }

  /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
  public boolean search(String word) {
    for (int i = 0; i < word.length(); i++) {
      String key = word.substring(0, i) + word.substring(i + 1);
      if (trie.containsKey(key)) {
        List<int[]> values = trie.get(key);
        for (int[] value : values) {
          if (value[0] == i && value[1] != word.charAt(i)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
