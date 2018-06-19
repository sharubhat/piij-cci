package leetcode.easy;

/**
 * https://leetcode.com/problems/plus-one/description/
 * Tricky problem. Since we are just adding 1, carry comes in play only when the digit is 9.
 * If all digits are 9, then it's a special case.
 */
public class P66PlusOne {
  public int[] plusOne(int[] digits) {
    int carry = 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      digits[i] = 0;
    }
    digits = new int[digits.length + 1];
    digits[0] = carry;
    return digits;
  }
}
