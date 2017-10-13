package com.leetcode.ds.easy;

/**
 * https://leetcode.com/problems/first-bad-version/description/
 */
public class P278FirstBadVersion {
  public int firstBadVersion(int n) {
    int start = 1;
    int end = n;
    int mid;
    while (end > start) {
      mid = start + (end - start)/2;
      if(isBadVersion(mid)) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return end;
  }

  /**
   * This implementation is provided and is hidden
   * @param version version
   * @return boolean
   */
  private boolean isBadVersion(int version) {
    return false;
  }
}
