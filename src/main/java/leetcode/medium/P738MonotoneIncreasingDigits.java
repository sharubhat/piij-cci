package leetcode.medium;

/**
 * https://leetcode.com/contest/weekly-contest-61/problems/monotone-increasing-digits/
 * The idea is to go from the LSB to MSB and find the last digit, where an inversion happens.
 * There are 2 cases to consider:
 * case 1:
 * In 14267 , we see that inversion happens at 4. In this case, then answer is obtained by reducing
 * 4 to 3, and changing all the following digits to 9.
 * => 13999
 * case 2:
 * 1444267, here even though the last inversion happens at the last 4 in 1444, if we reduce it to 3,
 * then that itself breaks the rule. So once we find the last digit where inversion happens,
 * if that digit is repeated, then we have to find the last position of that digit.
 * After that it is same as case1, where we reduce it by 1 and set the remaining digits to 9.
 * => 1399999
 */
public class P738MonotoneIncreasingDigits {
  public int monotoneIncreasingDigits(int N) {
    String s = String.valueOf(N);
    int idx = -1;
    for (int i = s.length() - 2;i >= 0; i--) {
      if (s.charAt(i) > s.charAt(i + 1) ||
          ((idx != -1) && (s.charAt(idx) == s.charAt(i)))) {
        idx = i;
      }
    }
    return idx == -1 ? N : N - Integer.parseInt(s.substring(idx + 1)) - 1;
  }

  public static void main(String[] args) {
    System.out.println(new P738MonotoneIncreasingDigits().monotoneIncreasingDigits(14267));
    System.out.println(new P738MonotoneIncreasingDigits().monotoneIncreasingDigits(1444267));
  }
}
