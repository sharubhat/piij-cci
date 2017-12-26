package com.topcoder;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=3970&rd=7993
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
      double temp = price;
      for (int i = 0; i < loanTerm; i++) {
        temp = temp + ((mid / 1200) * temp) - monthlyPayment;
      }
      if (temp == 0) {
        return mid;
      } else {
        if (temp < 0) {
          low = mid;
        } else  {
          high = mid;
        }
      }
    }
    return mid;
  }

  public static void main(String[] args) {
    AutoLoan al = new AutoLoan();
    System.out.println(al.interestRate(6800.0, 100.0, 68));
    System.out.println(al.interestRate(2000.0, 510.0, 4));
    System.out.println(al.interestRate(200.0, 15.0, 600));
  }
}
