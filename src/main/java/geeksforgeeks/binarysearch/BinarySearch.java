package com.geeksforgeeks.binarysearch;

public class BinarySearch {
  private int bs(int[] arr, int left, int right, int key) {
    while(left <= right) {
      int mid = left + (right - left)/2;
      if(arr[mid] == key) {
        return mid;
      } else if(arr[mid] < key) { // key could be on right half
        left = mid + 1;
      } else { // key could be on left half
        right = mid - 1;
      }
    }
    return -1;
  }

  private int bsLessComparisons(int[] arr, int left, int right, int key) {
    int mid;
    while (right - left > 1) {
      mid = left + (right - left)/2;
      if (arr[mid] <= key) { // key could be on right half
        left = mid;
      } else { // key could be on left half
        right = mid;
      }
    }
    if (arr[left] == key) {
      return left;
    }
    return -1;
  }

  public int bsLessComparisons(int[] arr, int key) {
    return bsLessComparisons(arr, 0, arr.length - 1, key);
  }

  public int bs(int[] arr, int key) {
    return bs(arr, 0, arr.length - 1, key);
  }

  public static void main(String[] args) {
    int[] a = {2, 5, 8, 10, 11, 12};
    System.out.println(new BinarySearch().bsLessComparisons(a, 11));
  }
}
