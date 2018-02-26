package com.leetcode.easy;

/**
 * https://leetcode.com/problems/non-decreasing-array/description/
 * When you find nums[i-1] > nums[i] for some i, you will prefer to change nums[i-1]'s value,
 * since a larger nums[i] will give you more risks that you get inversion errors after position i.
 * But, if you also find nums[i-2] > nums[i], then you have to change nums[i]'s value instead,
 * or else you need to change both of nums[i-2]'s and nums[i-1]'s values.
 * [n[i - 2], n[i - 1], n[i]]
 * 1        ,         4 > 2       ==> 1        ,         1 < 2
 * n[i - 2], n[i - 1] > n[i]    ==> n[i - 2], n[i - 1] < n[i]  // if n[i - 1] > n[i] && n[i - 2] <= n[i] then n[i - 1] = n[i]
 * 3        ,         4 > 2       ==> 1        ,         1 < 2
 * n[i - 2], n[i - 1] > n[i]    ==> n[i - 2], n[i - 1] < n[i]
 * n[i - 2]             > n[i]    ==> 3        ,          4,   4  // if n[i - 1] > n[i] && n[i - 2] > n[i] then n[i] = n[i - 1]
 */
public class P665NonDecreasingArray {
  public boolean checkPossibility(int[] nums) {
    if (nums.length < 2) {
      return true;
    }
    int changeCount = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i - 1] > nums[i]) {
        if (i < 2 || nums[i - 2] <= nums[i]) {      // e.g. : 4, 2 => 2, 2 OR 2/1, 4, 2 => 2/1, 2, 2
          nums[i - 1] = nums[i];
        } else {
          nums[i] = nums[i - 1];                      // e.g. : 3, 5, 2 => 3, 5, 5
        }
        changeCount++;
      }
    }
    return changeCount <= 1;
  }
}
