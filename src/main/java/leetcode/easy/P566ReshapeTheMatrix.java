package leetcode.easy;

public class P566ReshapeTheMatrix {
  /**
   * Returns reshaped matrix or original matrix if reshaping is not possible.
   * @param nums Original matrix
   * @param r number of rows in output matrix
   * @param c number of columns in output matrix
   * @return int[][]
   */
  public int[][] matrixReshape(int[][] nums, int r, int c) {
    if (nums.length * nums[0].length != r * c) {
      return nums;
    }
    int[][] res = new int[r][c];
    int y = 0;
    int x = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[0].length; j++) {
        if (x == c) {
          x = 0;
          y++;
        }
        res[y][x++] = nums[i][j];
      }
    }
    return res;
  }
}
