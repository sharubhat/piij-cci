package leetcode.easy;

import java.util.BitSet;

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

  public int missingNumber2(int[] nums) {
    BitSet bitSet = new BitSet(nums.length);

    for (int num : nums) {
      bitSet.set(num - 1);
    }

    return bitSet.nextClearBit(0) + 1;
  }

  public static void main(String[] args) {
    System.out.println(new P268MissingNumber().missingNumber2(new int[]{1, 2, 3, 4, 6}));
  }
}
