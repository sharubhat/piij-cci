package dp.cci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotInGrid {
    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    /**
     * Draw recursion tree. Each path has r+c steps and there are two choices at each step. O(2 ^ r+c)
     * @param r
     * @param c
     * @param maze
     * @param path
     * @return
     */
    private static boolean getPathR(int r, int c, boolean[][] maze, List<Point> path) {
          if (r < 0 || c < 0 || !maze[r][c]) {
            return false;
        }
          if ((r == 0 && c == 0) || getPathR(r-1, c, maze, path) || getPathR(r, c-1, maze, path)) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }
        return false;
    }

    /**
     * There are rc points that the robot can visit, but with getPath, it ends up visiting 2^ r+c points.
     * By drawing recursion tree one can see that some of the points are visited more than once. So the
     * solution can be improved by caching the points where robot can't move to or are already found not to
     * be in the path from (0,0) to (r,c). Resulting time complexity is O(rc)
     * @param r
     * @param c
     * @param maze
     * @param path
     * @return
     */
    private static boolean getPathDP(int r, int c, boolean[][] maze, List<Point> path, Set<Point> memo) {
          if (r < 0 || c < 0 || !maze[r][c]) {
            return false;
        }
          if (!memo.contains(new Point(r, c)) ||
                (r == 0 && c == 0) ||
                getPathDP(r-1, c, maze, path, memo) ||
                getPathDP(r, c-1, maze, path, memo)) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }
        memo.add(new Point(r, c));
        return false;
    }

    public static List<Point> getPath(boolean[][] maze) {
        List<Point> res = new ArrayList<>();
        getPathR(maze.length, maze[0].length, maze, res);
        getPathDP(maze.length, maze[0].length, maze, res, new HashSet<>());
        return res;
    }

}
