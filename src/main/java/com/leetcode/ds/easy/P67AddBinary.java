package com.leetcode.ds.easy;

/**
 * https://leetcode.com/problems/add-binary/description/
 */
public class P67AddBinary {
  public String addBinary(String a, String b) {
    if (a == null) {
      a = "0";
    }
    if (b == null) {
      b = "0";
    }
    int aI = a.length();
    int bI = b.length();
    boolean carry = false;
    StringBuffer sb = new StringBuffer();
    while (aI > 0 && bI > 0) {
      carry = add(a.charAt(--aI), b.charAt(--bI), carry, sb);
    }
    while (aI > 0) {
      carry = add(a.charAt(--aI), '0', carry, sb);
    }
    while (bI > 0) {
      carry = add('0', b.charAt(--bI), carry, sb);
    }
    if (carry) {
      sb.append("1");
    }
    return sb.reverse().toString();
  }

  private boolean add(char a, char b, boolean carry, StringBuffer sb) {
    if (a == b) {
      if (carry) {
        sb.append('1');
      } else {
        sb.append('0');
      }
      return a == '1';
    } else {
      if (carry) {
        sb.append('0');
      } else {
        sb.append('1');
      }
      return carry;
    }
  }
}
