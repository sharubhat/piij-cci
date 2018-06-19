package topcoder.binarysearch;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=3970&rd=7993&rm=&cr=15614628
 * Binary Search
 *
 * For any given annual rate of interest, we can calculate the balance using below formula.
 * balance = sigma (o to loan term) of [balance + ( yearly interest / 12 * balance) - monthly payment]
 *
 * If YI rate is chosen from 0 to hundred, we will end up with an array where balance will,
 * either be all positive or range from negative to positive and also might include 0.
 * Balance of 0 is best case but if there is no zero, we need to find YI that yields highest negative
 * balance.
 */
public class AutoLoan {
  public double interestRate(double price, double monthlyPayment, int loanTerm) {
    double low = 0;
    double high = 1000;
    double mid;
    // The return value must be within 1e-9 absolute or relative error of the actual result.
    while (high - low > 1e-12) {
      mid = low + (high - low) / 2;
      double balance = getBalance(price, monthlyPayment, loanTerm, mid);
      if (balance == 0) {
        return mid;
      } else {
        if (balance < 0) {
          low = mid;
        } else  {
          high = mid;
        }
      }
    }
    return high;
  }

  /**
   * Predicate function for binary search.
   * @param price
   * @param monthlyPayment
   * @param loanTerm
   * @param mid
   * @return
   */
  private double getBalance(double price, double monthlyPayment, int loanTerm, double mid) {
    double balance = price;
    for (int i = 0; i < loanTerm; i++) {
      balance = (balance + (mid / 1200 * balance)) - monthlyPayment;
    }
    return balance;
  }

  public static void main(String[] args) {
    AutoLoan al = new AutoLoan();
    System.out.println(al.interestRate(6800.0, 100.0, 68));
    System.out.println(al.interestRate(2000.0, 510.0, 4));
    System.out.println(al.interestRate(200.0, 15.0, 600));
    System.out.println(al.interestRate(5628.0, 2297.0, 3));
  }
}
