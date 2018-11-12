package leetcode.mockinterview;

public class SNL {
  public int snakesAndLadders(int[][] board) {
    return snl(board, board.length - 1, 0, 0, Integer.MAX_VALUE);
  }

  private int snl(int[][] board, int m, int n, int moves, int minMoves) {
    if (m <= 0) {
      minMoves = minMoves > moves ? moves : minMoves;
      return minMoves;
    }
    if (m == 1 && n + 6 > board[0].length) {
      minMoves = minMoves == Integer.MAX_VALUE ? 1 : minMoves;
      return minMoves;
    }
    for (int i = 0 ; i < 6; i ++) {
      n = (n + i) % board[0].length;
      if (board[m][n] != -1) {
        // snake or ladder
        int square = board[m][n];
        int row = board.length - (square / (board[0].length - 1));
        int col = (square % board[0].length) - 1;
        return snl(board, row, col++, moves + 1, minMoves);
      }
    }
    return snl(board, m - 1, n % board[0].length, moves + 1, minMoves);
  }

  public static void main(String[] args) {
    System.out.println(new SNL().snakesAndLadders(new int[][]{{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}}));
  }
}
