package dp;

import java.util.LinkedList;

/**
 * https://www.youtube.com/watch?v=8LusJS5-AGo&index=1&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 */
public class Knapsack {
  /**
   * Bottom up dynamic programming approach.
   * @param values values of items
   * @param weights weights of items
   * @param totalWeight total weight the knapsack can hold
   * @return int
   */
  public int maxValue(int values[], int weights[], int totalWeight) {
    int[][] lm = new int[values.length + 1][totalWeight + 1];    // lm is local maximum value matrix.
    for (int val = 1; val <= values.length; val++) {
      for (int weight = 1; weight <= totalWeight; weight++) {
        int prevVal = val - 1;
        if (weight < weights[prevVal]) {  // current item weighs more than current total weight,
                                               // so don't use this item.
          lm[val][weight] = lm[prevVal][weight];
        } else {   // current item weight is less than total weight in question and so there are two
                    // possibilities. The item is included or not included.
          lm[val][weight] = Math.max(
              lm[prevVal][weight],                                           // current item not included
              lm[prevVal][weight-weights[prevVal]] + values[prevVal]   // current item contributes
          );
        }
      }
    }
    // Determine which items to take :
    // Start from right bottom of the matrix. If that is same as the value above (i.e. same max value
    // without including current weight), then the item is not taken. If it's different, then the item is
    // taken. So include it in taken list. Reduce the total weight by the weight of currently taken item.
    // Continue until you reach beginning of the matrix or total weight becomes zero.
    LinkedList<Integer> taken = new LinkedList<>();
    for (int n = values.length, w = totalWeight; n > 0 || w > 0; n--) {
      if (lm[n][w] != lm[n - 1][w]) {
        taken.addFirst(weights[n - 1]);
        w = w - weights[n - 1];
      }
    }
    System.out.println(taken);
    return lm[values.length][totalWeight];
  }

  public static void main(String args[]){
    Knapsack k = new Knapsack();
    int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
    int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
    int r = k.maxValue(val, wt, 10);
    System.out.println(r);
  }
}
