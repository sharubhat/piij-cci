package topcoder.binarysearch;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=4823&rd=8074&rm=&cr=15533368
 * Starting from first bound, keep totalling the numbers in combined sorted array until mid
 * for all bounds but within search space. This means, accumulate if and only if mid is larger than
 * lower bound. If mid is between the bounds, add the numbers up to and including mid. If mid is
 * larger than upper bound, then add all elements within the bound to total. Note that the total
 * being accumulated is actually indices and not the actual numbers in resultant sorted array. After
 * accounting all the bounds, adjust the search space by updating min or max accordingly.
 */
public class UnionOfIntervals {
  public int nthElement(int[] lowerBound, int[] upperBound, int n) {
    int len = lowerBound.length;
    long min = 2_000_000_000;
    long max = -2_000_000_000;
    long mid;
    for (int i = 0; i < len; i++) {
      min = min > lowerBound[i] ? lowerBound[i] : min;
      max = max < upperBound[i] ? upperBound[i] : max;
    }
    while (min <= max) {
      mid = min + (max - min) / 2;
      long total = 0;
      for (int i = 0; i < len; i++) {
        if (mid >= lowerBound[i] && mid <= upperBound[i]) {
          total = total + (mid - lowerBound[i] + 1);
        } else if (mid >= lowerBound[i] && mid > upperBound[i]) {
          total = total + (upperBound[i] - lowerBound[i] + 1);
        }
      }
      if (total > n) {
        max = mid - 1;
      } else {
        min = mid + 1;
      }
    }
    return (int)min;
  }

  public static void main(String[] args) {
    System.out.println(new UnionOfIntervals().nthElement(new int[] {1, 3}, new int[] {4, 5}, 3));
    System.out.println(new UnionOfIntervals().nthElement(new int[] {1, 1, 1, 1, 1, 1}, new int[] {2, 2, 2, 2, 2, 100}, 5));
    // Watch out for overflow errors.
    System.out.println(new UnionOfIntervals().nthElement(new int[] {-1500000000}, new int[] {1500000000}, 1500000091));
  }
}
