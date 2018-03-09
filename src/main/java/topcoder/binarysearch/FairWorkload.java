package topcoder.binarysearch;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=1901&rd=4650
 * Summary :
 *  partition the array such that partition with max sum is the smallest sum partition possible.
 *
 *  Read https://www.topcoder.com/community/data-science/data-science-tutorials/binary-search/
 *  for explanation.
 */
public class FairWorkload {
  public int getMostWork(int[] folders, int workers) {
    int n = folders.length;
    int low = getMaxElement(folders);
    int high = accumulate(folders);

    while (low < high) {
      int max = low + (high - low) / 2;

      int required = 1;
      int current_load = 0;

      for (int i = 0; i < n; i++) {
        if (current_load + folders[i] <= max) {
          // current worker can handle it
          current_load += folders[i];
        } else {
          // assign next worker
          required++;
          current_load = folders[i];
        }
      }

      if (required <= workers) {
        high = max;
      } else {
        low = max + 1;
      }
    }
    return low;
  }

  private int accumulate(int[] folders) {
    int sum = 0;
    for (int folder : folders) {
      sum += folder;
    }
    return sum;
  }

  private int getMaxElement(int[] folders) {
    int max = Integer.MIN_VALUE;
    for (int folder : folders) {
      if (folder > max) {
        max = folder;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(new FairWorkload().getMostWork(new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90}, 3));
  }
}
