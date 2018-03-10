package leetcode.easy;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/description/
 * Hint : Imagine a number to the base 26.
 * Consider BA. If B is at right most position, then it's value is B * 26 ^ 0 =  2 * 1 = 2.
 * Now if B should be moved to one position to the left, it's value would become B * 26 ^ 1 which
 * is equivalent to previous result * 26. So BA would be 26 * result of B + result of A.
 * The solution can be imagined as adding characters to the end of a list and as list grows,
 * the characters are moved to their left.
 */
public class P171ExcelSheetColumnNumber {
  public int titleToNumber(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res = res * 26 + (s.charAt(i) - 'A' + 1);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(new P171ExcelSheetColumnNumber().titleToNumber("BA"));
  }
}
