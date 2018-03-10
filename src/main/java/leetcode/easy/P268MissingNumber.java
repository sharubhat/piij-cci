package leetcode.easy;

/**
 * https://leetcode.com/problems/missing-number/description/
 */
public class P268MissingNumber {
  public int missingNumber(int[] nums) {
    int xor = 0;
    for (int i = 0; i < nums.length; i++) {
      xor = xor ^ nums[i] ^ i;
    }
    // finally xor nums.length, because a number is missing, nums.length will also be an entry
    // in array. e.g. [0,2,3]
    return xor ^ nums.length;
  }
}
