package com.topcoder.binarysearch;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=3970&rd=7993&rm=&cr=15614628
 * Binary Search
 */
public class AutoLoan {
  public double interestRate(double price, double monthlyPayment, int loanTerm) {
    double low = 0;
    double high = 100;
    double mid = 0;
    // The return value must be within 1e-9 absolute or relative error of the actual result.
    while (high - low > 1e-9) {
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
    return mid;
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
  }
}
