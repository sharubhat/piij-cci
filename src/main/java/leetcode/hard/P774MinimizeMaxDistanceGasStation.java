package com.leetcode.hard;

/**
 * We are using binary search to find the smallest possible value of D.
 * Initialize low = 0 and high = max distance between two adjacent stations. Since we are using
 * binary search, we can be sloppy and say high = distance between first and last station.
 * Check if K stations can be placed such that max distance between any two stations is equal to mid.
 * if remainingStations >= 0, it means mid works but we can continue to find a smaller mid.
 * if remainingStations < 0, it means mid is too small and can't be achieved with K stations.
 * When high - low < 1e-6, it means the answer within 10^-6 of the true value and it will be accepted.
 */
public class P774MinimizeMaxDistanceGasStation {
  public double minmaxGasDist(int[] stations, int k) {
    double low = 0;
//    double high = maxDistance(stations);
    double high = stations[stations.length - 1] - stations[0];
    double mid = 0;
    while (high - low > 1e-6) {
      mid = low + (high - low) / 2;
      // will this maxDist work?
      double remainingStations = check(stations, k, mid);
        if (remainingStations < 0) { // k stations are not enough to meet max distance of mid
          low = mid;
        } else {  // mid works, but continue to minimize max distance.
          high = mid;
        }
    }
    return mid;
  }

  // if any dist is > current max, reduce k, return remaining k
  private double check(int[] s, int k, double newMax) {
    for (int i = 1; i < s.length; i++) {
      // Place a station if distance between adjacent stations is more than newMax
      // If distance is already less than newMax(mid) no new stations are placed. No need for explicit
      // if condition as division results in 0 in such a case.
      k = k - (int)((s[i] - s[i - 1]) / newMax);
    }
    return k;
  }

  private double maxDistance(int[] s) {
    double max = s[1] - s[0];
    for (int i = 2; i < s.length; i++) {
      if (max < s[i] - s[i - 1]) {
        max = s[i] - s[i - 1];
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(new P774MinimizeMaxDistanceGasStation().minmaxGasDist(new int[]{1,2,3,4,5,6,7,8,9,10}, 9));
    System.out.println(new P774MinimizeMaxDistanceGasStation().minmaxGasDist(new int[]{23,24,36,39,46,56,57,65,84,98}, 1));
  }
}
