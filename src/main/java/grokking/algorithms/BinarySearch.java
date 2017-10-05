package grokking.algorithms;

import java.util.Arrays;

public class BinarySearch {
  public int search(int item, int[] array) {
    int low = 0;
    int high = array.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int guess = array[mid];
      if (guess == item)
        return mid;
      if (guess > item)
        high = mid - 1;
      else
        low = mid + 1;
    }
    return -1;
  }

  public static void main(String[] args) {
    BinarySearch bs = new BinarySearch();
    int[] array = new int[]{34, 43, 10, 45};
    Arrays.sort(array);
    System.out.println(bs.search(44, array));
  }
}
