package com.leetcode.ds.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/contest/weekly-contest-61/problems/daily-temperatures/. O(k.n) time
 * complexity where k is difference between min and max temperature; O(n) space complexity
 */
public class P739DailyTemperatures {
  public int[] dailyTemperatures(int[] temperatures) {
    int[] daysToWait = new int[temperatures.length];
    Map<Integer, Integer> mapTempToIndex = new HashMap<>();
    for (int idx = temperatures.length - 1; idx >= 0; idx--) {
      int days = Integer.MAX_VALUE;
      for (int temp = temperatures[idx] + 1; temp <= 100; temp++) {
        if (mapTempToIndex.containsKey(temp)) {
          days = Math.min(days, mapTempToIndex.get(temp) - idx);
        }
      }
      mapTempToIndex.put(temperatures[idx], idx);
      if (days != Integer.MAX_VALUE) {
        daysToWait[idx] = days;
      }
    }
    return daysToWait;
  }

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(
            new P739DailyTemperatures()
                .dailyTemperatures(new int[] {89,62,70,58,47,47,46,76,100,70})));
  }
}
