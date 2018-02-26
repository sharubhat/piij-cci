package com.dp.cci;

public class Coins {
    public static int makeChange(int amt, int[] denoms, int index) {
          if (index >= denoms.length  - 1)
            return 1;
        int denomAmt = denoms[index];
        int ways = 0;
          for (int i = 0; i * denomAmt <= amt; i++) {
            int amountRemaining = amt - i * denomAmt;
            ways += makeChange(amountRemaining, denoms, index + i);
        }
        return ways;
    }
}
