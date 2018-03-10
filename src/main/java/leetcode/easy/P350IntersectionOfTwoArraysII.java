package leetcode.easy;

import java.util.*;

public class P350IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length < nums2.length) {
      int[] tmp = nums1;
      nums1 = nums2;
      nums2 = tmp;
    }

    Map<Integer, Integer> occurrence = new HashMap<>();
    for (int num : nums2) {
      occurrence.put(num, occurrence.get(num) == null ? 1 : occurrence.get(num) + 1);
    }

    List<Integer> result = new ArrayList<>();
    for (int num : nums1) {
      if (occurrence.get(num) != null && occurrence.get(num) != 0) {
        result.add(num);
        occurrence.put(num, occurrence.get(num) - 1);
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }
}
