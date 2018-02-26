package com.dp.cci;

import java.util.Optional;

public class MagicIndex {
    // linear time complexity
    private static Optional<Integer> magicSlow(int[] numbers) {
          for (int i = 0; i < numbers.length; i++) {
              if (i == numbers[i])
                return Optional.of(i);
        }
        return Optional.empty();
    }

    // magic slow can be improved by using divide and conquer approach yielding logarithmic time complexity
    private static Optional<Integer> magicFaster(int[] nums) {
        return magicFasterHelper(nums, 0, nums.length - 1);
    }

    private static Optional<Integer> magicFasterHelper(int[] nums, int s, int e) {
          if (e >= s) {
            int mid = s + (e - s) / 2;
              if (nums[mid] == mid)
                return Optional.of(mid);
            else   if (nums[mid] < mid)
                return magicFasterHelper(nums, mid + 1, e);
            else
                return magicFasterHelper(nums, s, mid - 1);
        }
        return Optional.empty();
    }

    public static Optional<Integer> magic(int[] nums) {
        return magicSlow(nums);
    }

    public static void main(String[] args) {
        System.out.println(magicFaster(new int[]{-3, 0, 1, 2, 4, 8, 9}));
    }
}
