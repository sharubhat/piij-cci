package leetcode.arraysandstrings;

import java.util.Arrays;

public class DiagonalTraverse {
  public int[] findDiagonalOrder(int[][] matrix) {
    boolean forwarding = true;
    int row = 0;
    int col = 0;
    int m = matrix.length;
    int n = matrix[0].length;
    int[] res = new int[m * n];
    int resIndex = 0;
    boolean validRow;
    boolean validCol;

    while (row < matrix.length + 1 && col < matrix[0].length + 1) {
      if (valid(row, col, matrix)) {
        res[resIndex++] = matrix[row][col];
      }
      if (forwarding == true) {
        row = row - 1;
        col = col + 1;
        validRow = validRow(row, matrix);
        validCol = validCol(col, matrix);
        if (!validRow && !validCol) {
          row = row + 2;
          col = col - 1;
          forwarding = false;
        } else if (!validRow) { // but valid column
          row = row + 1;
          forwarding = false;
        }
      } else {
        row = row + 1;
        col = col - 1;
        validRow = validRow(row, matrix);
        validCol = validCol(col, matrix);
        if (!validRow && !validCol) {
          row = row - 1;
          col = col + 2;
          forwarding = true;
        } else if (!validCol) {
          col = col + 1;
          forwarding = true;
        }
      }
    }
    return res;
  }

  private boolean valid(int r, int c, int[][] matrix) {
    return (r >= 0 && r < matrix.length) && (c >= 0 && c < matrix[0].length);
  }

  private boolean validRow(int n, int[][] matrix) {
    return (n < matrix.length && n >= 0);
  }

  private boolean validCol(int n, int[][] matrix) {
    return (n < matrix[0].length && n >= 0);
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new DiagonalTraverse().findDiagonalOrder(new int[][]{{1,2,3,4,5,6,7,8,9,10}})));
  }
}