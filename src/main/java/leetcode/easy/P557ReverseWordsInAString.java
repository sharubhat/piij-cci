package com.leetcode.easy;

public class P557ReverseWordsInAString {
  /**
   * A quick readable solution. Can be solved without using StringBuffers at the cost
   * of increased code complexity and reduced readability.
   * @param s input
   * @return String
   */
  public String reverseWords(String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    String[] words = s.split(" ");
    StringBuffer sb = new StringBuffer();
    for (String word : words) {
      sb.append(new StringBuffer(word).reverse()).append(" ");
    }
    String res = sb.toString();
    return res.trim();
  }
}
