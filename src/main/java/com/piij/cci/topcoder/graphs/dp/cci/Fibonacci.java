package com.piij.cci.topcoder.graphs.dp.cci;

public class Fibonacci {
    // draw the tree to see that every node has two subtrees. Time taken is roughly the number
    // of nodes in the tree which is 2 * 2 * 2 * .... == 2^n. So time complexity is O(2 ^ n)
    public static long fibonacciRec(int n) {
          if (n == 0 || n == 1)
            return 1;
        return fibonacciRec(n - 1) + fibonacciRec(n - 2);
    }

    public static long fibonacciDp(int n) {
        return fibonacciDpHelper(n, new long[n + 1]);
    }

    private static long fibonacciDpHelper(int n, long[] memo) {
          if (n == 0 || n == 1)
            memo[n] = 1;
          if (memo[n] == 0)
            memo[n] = fibonacciDpHelper(n - 2, memo) + fibonacciDpHelper(n - 1, memo);
        return memo[n];
    }

    private static long fibonacciDpMemOptimized(int n) {
        int[] memo = new int[3];
        memo[0] = memo[1] = 1;
          for (int i = 2; i <= n; i++) {
            memo[2] = memo[0] + memo[1];
            memo[0] = memo[1];
            memo[1] = memo[2];
        }
        return memo[2];
    }

    public static void main(String[] args) {
        System.out.println(fibonacciRec(33));
        System.out.println(fibonacciDp(33));
        System.out.println(fibonacciDpMemOptimized(33));
    }
}
