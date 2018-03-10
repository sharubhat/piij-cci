package leetcode.easy;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 * Note: You may assume the string contain only lowercase letters.
 * So don't jump into initializing array of size 265.
 */
public class P387FirstUniqueCharacterInString {
  public int firstUniqChar(String s) {
    int[] frequency = new int[26];
    for (char c : s.toCharArray()) {
      frequency[c - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (frequency[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }
}
