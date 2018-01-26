package com.leetcode.ds.easy;

public class P680ValidPalindromeII {
  public boolean validPalindrome(String s) {
    int st = -1;
    int e = s.length();
    while (++st < --e) {
      if (s.charAt(st) != s.charAt(e)) {
        return validPalindrome(s, st + 1, e) || validPalindrome(s, st, e - 1);
      }
    }
    return true;
  }

  private boolean validPalindrome(String s, int st, int e) {
    while (st < e) {
      if (s.charAt(st) != s.charAt(e)) {
        return false;
      }
      st++;
      e--;
    }
    return true;
  }

  private boolean validPalindrome(String s, int st, int e, boolean isDeleted) {
    while (st < e) {
      if (s.charAt(st) != s.charAt(e)) {
        if(isDeleted) {
          return false;
        }
        return validPalindrome(s, st + 1, e, true) || validPalindrome(s, st, e - 1, true);
      }
      st++;
      e--;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(new P680ValidPalindromeII().validPalindrome("cbbcc"));
  }
}
