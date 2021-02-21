package dp.patterns;

/**
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/2-dimensional/tutorial/
 * Given a cost matrix Cost[][] where Cost[i][j] denotes the Cost of visiting cell with coordinates (i,j),
 * find a min-cost path to reach a cell (x,y) from cell (0,0) under the condition that you can only travel
 * one step right or one step down. (We assume that all costs are positive integers)
 *
 * Another variant of this problem includes another direction of motion, i.e. one is also allowed to move diagonally
 * lower from cell (i,j) to cell (i+1,j+1). This question can also be solved easily using a slight modification
 * in the recurrence relation. To reach (i,j), we must first reach either (i-1,j), (i,j-1) or (i-1,j-1).
 */
public class MinCostPathInGrid {
    private static int minCostInGrid(int[][] costGrid) {
        int m = costGrid.length;
        int n = costGrid[0].length;

        int[][] costMemo = new int[m][n];

        // initialize first row of memo
        for (int i = 1; i < n; i++) {
            costMemo[0][i] = costMemo[0][i - 1] + costGrid[0][i];
        }

        // initialize first column of memo
        for (int i = 1; i < m; i++) {
            costMemo[i][0] = costMemo[i - 1][0] + costGrid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                costMemo[i][j] = Math.min(costMemo[i - 1][j], costMemo[i][j - 1]) + costGrid[i][j];
            }
        }
        return costMemo[m - 1][n - 1];
    }
}
