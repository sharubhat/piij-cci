package com.leetcode.easy;

public class P125ValidPalindrome {
  public boolean isPalindrome(String s) {
    int st= 0;
    int e = s.length() - 1;
    while (st < e) {
      while (!isValid(s.charAt(st)) && st < e) {
        st++;
      }
      while (!isValid(s.charAt(e)) && st < e) {
        e--;
      }
      if (!compare(s.charAt(st++), s.charAt(e--))) {
        return false;
      }
    }
    return true;
  }

  private boolean isValid(char c) {
    return (c >= 'a' && c <= 'z') ||
        (c >= 'A' && c <= 'Z') ||
        (c >= '0' && c <= '9');
  }

  private boolean compare(char a, char b) {
    return Character.toString(a).equalsIgnoreCase(Character.toString(b));
  }

  public static void main(String[] args) {
    System.out.println(new P125ValidPalindrome().isPalindrome("0P"));
  }
}
