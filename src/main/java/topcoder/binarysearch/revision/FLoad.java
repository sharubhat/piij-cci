package topcoder.binarysearch.revision;

public class FLoad {
  public int getMostWork(int[] folders, int workers) {
    int low = max(folders);
    int high = sum(folders);
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (f(mid, folders, workers)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    if (f(low, folders, workers) == false) {
      return -1;
    }
    return low;
  }

  private int max(int[] a) {
    int max = a[0];
    for (int i : a) {
      if (max < i) {
        max = i;
      }
    }
    return max;
  }

  private int sum(int[] a) {
    int sum = 0;
    for (int i : a) {
      sum += i;
    }
    return sum;
  }

  private boolean f(int max, int[] folders, int workers) {
    int wCount = 1;
    int sum = folders[0];
    for (int i = 1; i < folders.length; i++) {
      if (sum + folders[i] < max) {
        sum += folders[i];
      } else {
        sum = folders[i];
        wCount++;
      }
    }
    return wCount <= workers;
  }

  public static void main(String[] args) {
    System.out.println(new FLoad().getMostWork(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90}, 3));
  }
}
