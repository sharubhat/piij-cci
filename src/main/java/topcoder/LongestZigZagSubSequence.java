package topcoder;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
 */
public class LongestZigZagSubSequence {
  public int longestZipZag(int[] arr) {
    if (arr.length == 1) {
      return 1;
    }
    int[] diffs = new int[arr.length];
    for (int i = 1; i < arr.length; i++) {
      diffs[i - 1] = arr[i] -arr[i -1];
    }
    int direction = 0;
    int len = 1;
    for (int i = 0; i < arr.length; i++) {
      if(diffs[i] != 0) {
        if (direction == 0) {      // case : first non-zero difference
          direction = diffs[i];
          len++;
          continue;
        }
        if (diffs[i] * direction < 0) {
          direction *= -1;
          len++;
        }
      }
    }
    return len;
  }

  public static void main(String[] args) {
    System.out.println(new LongestZigZagSubSequence().longestZipZag(new int[]{12, 333, 700, 436, 1, 919, 959, 456, 456, 456, 1000, 193, 192, 13, 75, 818, 197, 197, 2, 777, 99, 81, 222, 109, 1000, 19, 27, 270, 62, 189, 987, 234, 55, 2, 718, 238, 901, 901, 900, 432, 55, 606, 89, 7, 7, 23, 789, 790, 800, 1000}));
    System.out.println(new LongestZigZagSubSequence().longestZipZag(new int[]{3, 3, 3, 3, 3}));
    System.out.println(new LongestZigZagSubSequence().longestZipZag(new int[]{4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}));
  }
}
