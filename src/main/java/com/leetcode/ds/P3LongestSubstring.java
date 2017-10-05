package com.leetcode.ds;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class P3LongestSubstring {
  /**
   * Solution that uses O(2n) additional space. While it is simpler to understand, it's
   * not an optimal solution.
   *
   * @param s given string
   * @return int
   */
  public int lengthOfLongestSubstring1(String s) {
    int currLength = 0;
    int maxLength = 0;
    Set<Character> set = new LinkedHashSet<>();
    Queue<Character> queue = new LinkedList<>();
    for (char c : s.toCharArray()) {
      if (set.contains(c)) {
        char removed;
        do {
          removed = queue.remove();
          set.remove(removed);
          currLength--;
        } while (removed != c);
      }
      currLength++;
      maxLength = Math.max(maxLength, currLength);
      set.add(c);
      queue.add(c);
    }
    return maxLength;
  }

  /**
   * Ideally ASCII chars would need an index array of size 256. However last 128
   * of them are hard to be printed out and so can be ignored(clarify).
   * This solution uses constant space and runs in O(n) time complexity.
   *
   * @param s input String
   * @return int
   */
  public int lengthOfLongestSubstring(String s) {
    int[] index = new int[128];
    int maxLength = 0;
    int startIndex = 0;
    Arrays.fill(index, -1);
    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i);
      // index[c] >= startIndex means we found a duplicate character
      // and we need to reset startIndex to next character after duplicate.
      startIndex = Math.max(startIndex, index[c] + 1);
      index[c] = i;
      maxLength = Math.max(maxLength, i - startIndex + 1);
    }
    return maxLength;
  }

  public static void main(String[] args) {
    System.out.println(new P3LongestSubstring().lengthOfLongestSubstring("pwwpkew"));
  }
}
