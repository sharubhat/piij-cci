package dp.cci;

public class KidStairCase {
    // Drawing the recursion tree will show that each node has 3 subtrees resulting in O(3 ^ n) time complexity.
    public static int numWaysToClimbNStairs(int n) {
          if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        else
            return numWaysToClimbNStairs(n - 3) + numWaysToClimbNStairs(n - 2) + numWaysToClimbNStairs(n - 1);
    }

    public static int numWaysToClimbNStairsDp(int n) {
        return numWaysToClimbNStairsDpHelper(n, new int[n + 1]);
    }

    private static int numWaysToClimbNStairsDpHelper(int n, int[] memo) {
          if (n < 0)
            return 0;
          if (n == 0)
            return 1;
        memo[n] = numWaysToClimbNStairsDpHelper(n -3, memo) +
                numWaysToClimbNStairsDpHelper(n - 2, memo) +
                numWaysToClimbNStairsDpHelper(n - 1, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(numWaysToClimbNStairs(10));
        System.out.println(numWaysToClimbNStairsDp(10));
    }
}
