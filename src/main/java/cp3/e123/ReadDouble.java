package cp3.e123;

import java.util.Scanner;

/**
 * Exercise 1.2.3-1 from book competitive programming 3
 *
 * Using Java, read in a double
 * (e.g. 1.4732, 15.324547327, etc.)
 * echo it, but with a minimum field width of 7 and 3 digits after the decimal point
 * (e.g. ss1.473 (where ‘s’ denotes a space), s15.325, etc.)
 */
public class ReadDouble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextDouble();
        scanner.close();

        // min width of 7, 3 decimal places. The width includes the dot('.').
        // so input = 1.2345 will be "ss1.234", s being placeholder for space
        System.out.format("Input is :%7.3f%n", input);
        // adding a 0 after % left pads number with zeros. e.g: input 12 will be printed as "012.000"
        System.out.format("Input is :%07.3f%n", input);
    }
}
