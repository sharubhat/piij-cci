package leetcode.facebook;

public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int num = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (!visited[i][j] && grid[i][j] == '1') {
          bfs(grid, visited, i, j);
          num++;
        }
      }
    }
    return num;
  }

  private void bfs(char[][] grid, boolean[][] visited, int i, int j) {
    if (!visited[i][j]) {
      visited[i][j] = true;
      if (grid[i][j] == '1') {
        if (i - 1 >= 0) bfs(grid, visited, i - 1, j);
        if (j - 1 >= 0) bfs(grid, visited, i, j - 1);
        if (i + 1 < grid.length) bfs(grid, visited, i + 1, j);
        if (j + 1 < grid[0].length) bfs(grid, visited, i, j + 1);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(new NumberOfIslands().numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
  }
}
