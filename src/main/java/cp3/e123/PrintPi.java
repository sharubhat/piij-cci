package cp3.e123;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Exercise 1.2.3-2 from book competitive programming 3
 *
 * Given an integer n (n ≤ 15), print π to n digits after the decimal point (rounded).
 * (e.g. for n = 2, print 3.14; for n = 4, print 3.1416; for n = 5, prints 3.14159.)
 */
public class PrintPi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n <= 0 || n >= 16) {
            throw new IllegalArgumentException("Input should be <= 15");
        }

        System.out.printf("%." + n + "f%n", Math.PI);


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(n);
        System.out.println(df.format(Math.PI));
    }
}
