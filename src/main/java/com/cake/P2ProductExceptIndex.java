package com.cake;

import java.util.Arrays;

public class P2ProductExceptIndex {
  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(getProductsOfAllIntsExceptAtIndex(new int[]{1,2,6,5,9})));
  }

  public static int[] getProductsOfAllIntsExceptAtIndex(int[] input) {
    int[] prefixProduct = new int[input.length];
    int productSoFar = 1;

    for (int i = 0; i < input.length; i++) {
      prefixProduct[i] = productSoFar;
      productSoFar = productSoFar * input[i];
    }
    System.out.println(Arrays.toString(prefixProduct));

    int[] suffixProduct = new int[input.length];
    int productAfter = 1;

    for (int i = input.length - 1; i >= 0; i--) {
      suffixProduct[i] = productAfter;
      productAfter = productAfter * input[i];
    }
    System.out.println(Arrays.toString(suffixProduct));

    for (int i = 0; i < input.length; i++) {
      prefixProduct[i] = prefixProduct[i] * suffixProduct[i];
    }
    return prefixProduct;
  }
}
