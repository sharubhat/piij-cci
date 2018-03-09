package topcoder.binarysearch;

/** https://community.topcoder.com/stat?c=problem_statement&pm=2427&rd=4765 */
public class Mortgage {
  public int monthlyPayment(int loan, int interest, int term) {
    long min = 0;
    long max = loan;
    long mid;
    while (min < max) {
      mid = min + (max - min) / 2;
      if (payoff(loan, interest, term, mid)) {
        max = mid;
      } else {
        min = mid + 1;
      }
    }
    return (int) min;
  }

  private boolean payoff(int loan, double interest, int term, long monthly) {
    for (int i = 0; i < term * 12; i++) {
      loan = (int) Math.ceil((loan - monthly) * (1 + interest / 10 / 12 / 100));
    }
    return loan <= 0;
  }

  public static void main(String[] args) {
    System.out.println(new Mortgage().monthlyPayment(1000, 50, 1));
  }
}
