package leetcode.arraysandstrings;

/**
 * https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1148/
 */
public class PlusOne {
  public int[] plusOne(int[] digits) {
    int index = digits.length - 1;

    while (index >= 0) {
      int digit = digits[index];
      if (digit != 9) {
        digits[index] = digits[index] + 1;
        return digits;
      } else {
        digits[index] = 0;
      }
      index--;
    }
    int[] result = new int[digits.length + 1];
    result[0] = 1;
    return result;
  }
}
