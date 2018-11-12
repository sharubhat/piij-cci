package leetcode.hard.arrays;

/** https://leetcode.com/problems/median-of-two-sorted-arrays/ */
public class P4MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] num1, int[] num2) {
    if (num1 == null || num2 == null) {
      throw new IllegalArgumentException();
    } else if (num1 == null) {
      return median(num2);
    } else if (num2 == null) {
      return median(num1);
    }
    if (num1.length > num2.length) {
      return findMedianSortedArrays(num2, num1);
    }
    int m = num1.length;
    int n = num2.length;

    int min = 0;
    int max = m;
    int halfLen = (m + n + 1) / 2;

    while (min <= max) {
      int i = min + (max - min) / 2; //
      int j = halfLen - i;

      if (i < max && num2[j - 1] > num1[i]) {
        // move to right
        min = i + 1;
      } else if (i > min && num1[i - 1] > num2[j]) {
        // move to left
        max = i - 1;
      } else {
        // i is where we want to divide
        int maxLeft = 0;
        if (i == 0) {
          // the entire num1 is on right half.
          // so the max element on left is the element in num2 at index j - 1
          maxLeft = num2[j - 1];
        } else if (j == 0) {
          // the entire num2 is on right half
          // so the max element on left is the element in num1 at index i - 1
          maxLeft = num1[i - 1];
        } else {
          // since both arrays are divided
          maxLeft = Math.max(num1[i - 1], num2[j - 1]);
        }

        if ((m + n) % 2 == 1) {
          return maxLeft;
        }

        int minRight = 0;
        if (i == m) {
          // the entire num1 is on left half.
          // so minimum element on right half is the element in num2 at index j
          minRight = num2[j];
        } else if (j == n) {
          // the entire num2 is on left half.
          // so minimum element on right half is the element in num1 at index i
          minRight = num1[i];
        } else {
          // since both arrays are divided
          minRight = Math.min(num1[i], num2[j]);
        }

        return (maxLeft + minRight) / 2.0;
      }
    }
    throw new IllegalArgumentException();
  }

  private double median(int[] num) {
    int len = num.length;
    if (len % 2 == 0) {
      return (num[len / 2] + num[len / 2 + 1]) / 2;
    } else {
      return num[len / 2 + 1];
    }
  }
}
