package com.topcoder.graphs.dfs.tc;

import java.util.*;

/**
 * Ref: https://community.topcoder.com/stat?c=problem_statement&pm=2998&rd=5857
 *
 * Solution here uses depth first search. The problem can also be solved using breadth first search.
 *
 * Created by sharath on 12/23/16.
 */
public class GrafixMask {
    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] sortedAreas(String[] rectangles) {
        boolean[][] fill = new boolean[400][600];

          for (int i = 0; i < rectangles.length; i++) {
            StringTokenizer st = new StringTokenizer(rectangles[i]);
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
              for (int j = x1; j <= x2; j++) {
                  for (int k = y1; k <= y2; k++) {
                    fill[j][k] = true;
                }
            }
        }

        List<Integer> areas = new ArrayList<>();

          for (int i = 0; i < fill.length; i++) {
              for (int j = 0; j < fill[i].length; j++) {
                  if (!fill[i][j]) {
                    areas.add(doFill(fill, i, j));
                }
            }
        }

        Collections.sort(areas);

        int[] results = new int[areas.size()];

          for (int i = 0; i < areas.size(); i++) {
            results[i] = areas.get(i);
        }

        return results;
    }

    private int doFill(boolean[][] fill, int i, int j) {
        int area = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(i, j));

          while (!stack.isEmpty()) {
            Point curr = stack.pop();
              if (isWithinBounds(curr, fill) && !isVisited(curr, fill)) {
                markAsVisited(curr, fill);
                area++;
                visitNeighbours(stack, curr);
            }
        }
        return area;
    }

    private void visitNeighbours(Stack<Point> stack, Point curr) {
        stack.push(new Point(curr.x + 1, curr.y));
        stack.push(new Point(curr.x - 1, curr.y));
        stack.push(new Point(curr.x, curr.y + 1));
        stack.push(new Point(curr.x, curr.y - 1));
    }

    private void markAsVisited(Point curr, boolean[][] fill) {
        fill[curr.x][curr.y] = true;
    }

    private boolean isWithinBounds(Point curr, boolean[][] fill) {
        System.out.println(curr.x + " " + curr.y);
        return curr.x >= 0 && curr.x < fill.length && curr.y >= 0 && curr.y < fill[0].length;
    }

    private boolean isVisited(Point curr, boolean[][] fill) {
        return fill[curr.x][curr.y];
    }

    public static void main(String[] args) {

        // better test cases here
        // http://community.topcoder.com/stat?c=problem_solution&cr=7452866&rd=5857&pm=2998
        GrafixMask g = new GrafixMask();
        int[] res = g.sortedAreas(new String[] {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"});
          for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
