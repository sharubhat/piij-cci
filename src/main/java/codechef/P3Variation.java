package codechef;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.codechef.com/ZCOPRAC/problems/ZCO15002
 */
public class P3Variation {
  private static int sum;
  public static void variation(int[] input, int start, int k) {
    if (start == input.length - 1) {
      return;
    }
    // replace this linear search with binary search
    for (int i = start + 1; i < input.length; i++) {
      if (Math.abs(input[start] - input[i]) >= k) {
        sum += input.length - i;
        break;
      }
    }
    variation(input, start + 1, k);
  }

  public static void main(String[] args) {
    Scanner scanner  = new Scanner(System.in);
    String[] lenAndK = scanner.nextLine().split(" ");
    int len = Integer.parseInt(lenAndK[0]);
    int k = Integer.parseInt(lenAndK[1]);
    int[] inputs = new int[len];

    String[] inputArray = scanner.nextLine().split(" ");
    for (int i = 0; i < len; i++) {
      inputs[i] = Integer.parseInt(inputArray[i]);
    }

    Arrays.sort(inputs);
    variation(inputs, 0, k);
    System.out.println(sum);
  }
}
