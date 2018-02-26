package leetcode.easy;

import java.util.*;

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

  /**
   * The basic idea is, keep a hashmap which stores the characters in string as keys and
   * their positions as values, and keep two pointers which define the max substring.
   * Move the right pointer to scan through the string , and meanwhile update the hashmap.
   * If the character is already in the hashmap, then move the left pointer to the right of
   * the same character last found. Note that the two pointers can only move forward.
   *
   * @param s input String
   * @return
   */
  public int lengthOfLongestSubstring3(String s) {
    if (s.length() == 0) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;
    for (int i = 0, j = 0; i < s.length(); ++i) {
      if (map.containsKey(s.charAt(i))) {
        j = Math.max(j, map.get(s.charAt(i)) + 1);
      }
      map.put(s.charAt(i), i);
      max = Math.max(max, i - j + 1);
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(new P3LongestSubstring().lengthOfLongestSubstring("pwwpkew"));
  }
}
