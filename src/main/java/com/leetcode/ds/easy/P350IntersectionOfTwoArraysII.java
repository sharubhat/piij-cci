package com.leetcode.ds.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P350IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] cache = null;
    int[] nums = null;
    if (nums1.length <= nums2.length) {
      cache = nums1;
      nums = nums2;
    } else {
      cache = nums2;
      nums = nums1;
    }
    for (int num : cache) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }
    List<Integer> result = new ArrayList<>();
    for (int num : nums) {
      if (map.containsKey(num) && map.get(num) != 0) {
        map.put(num, (map.get(num)) - 1);
        result.add(num);
      }
    }
    int[] res = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      res[i] = result.get(i);
    }

    return res;
  }
}
