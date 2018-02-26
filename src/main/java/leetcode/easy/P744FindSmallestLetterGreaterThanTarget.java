package com.leetcode.easy;

/**
 * https://leetcode.com/contest/weekly-contest-by-app-academy/problems/find-smallest-letter-greater-than-target/
 * Note: Letters are sorted.
 */
public class P744FindSmallestLetterGreaterThanTarget {
  public char nextGreatestLetter(char[] letters, char target) {
    for (int i = 0; i < letters.length; i++) {
      if (target < letters[i]) {
        return letters[i];
      }
    }
    return letters[0];
  }
}
