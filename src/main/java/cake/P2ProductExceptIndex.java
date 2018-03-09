package cake;

import java.util.Arrays;

public class P2ProductExceptIndex {
  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(getProductsOfAllIntsExceptAtIndex(new int[]{1,2,6,5,9})));
  }

  public static int[] getProductsOfAllIntsExceptAtIndex(int[] input) {
    if (input == null || input.length < 2) {
      throw new RuntimeException("Need an input array with at least two elements");
    }
    int[] productExceptAtIndex = new int[input.length];
    int productSoFar = 1;

    for (int i = 0; i < input.length; i++) {
      productExceptAtIndex[i] = productSoFar;
      productSoFar = productSoFar * input[i];
    }
    System.out.println(Arrays.toString(productExceptAtIndex));

    productSoFar = 1;
    for (int i = input.length - 1; i >= 0; i--) {
      productExceptAtIndex[i] = productExceptAtIndex[i] * productSoFar;
      productSoFar = productSoFar * input[i];
    }

    return productExceptAtIndex;
  }
}
