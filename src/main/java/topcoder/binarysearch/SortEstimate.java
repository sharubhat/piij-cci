package topcoder.binarysearch;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=3561&rd=6519
 * binary search on real numbers
 */
public class SortEstimate {
  private double f(int c, double n) {
    return c * n * Math.log(n) / Math.log(2);
  }
  public double howMany(int c, int time) {
    double low = 1;
    // determine high as it's unknown or not clear
    while (f(c, low) < time) {
      low = low * 2;
    }
    // if we get lucky, we might find exact value for n
    if (f(c, low) == time) {
      return low;
    }
    // now low is slightly higher than highest possible n for which running time <= time
    // set this to be high as we don't need to look any further in real numbers line and reset low.
    double high = low;
    low = 1;
    int count = 0;
    while (high - low >= 1e-9) {
      double mid = low + (high - low) / 2;
      if (f(c, mid) < time) {
        low = mid;
      } else {
        high = mid;
      }
      count++;
    }
    System.out.println("Ran for " + count + " loops");
    return low;
  }

  public static void main(String[] args) {
    SortEstimate se = new SortEstimate();
    System.out.println(se.howMany(1, 8));
    System.out.println(se.howMany(37, 12392342));
  }
}
