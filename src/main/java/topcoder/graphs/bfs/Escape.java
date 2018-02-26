package com.topcoder.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Ref: https://community.topcoder.com/tc?module=ProblemDetail&rd=4371&pm=1170
 *
 * Created by sharath on 1/1/17.
 */
public class Escape {
    private enum State {
        NORMAL, HARMFUL, DEADLY
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public int lowest(String[] harmful, String[] deadly) {
        State graph[][] = initializeGraph(harmful, deadly);
        int width = graph.length;
        int height = graph.length;
        int[][] visited = new int[width][height];
          for (int i = 0; i < visited.length; i++) {
              for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = -1;
            }
        }

        Queue<Node> queue = new LinkedList<>();

        Node start = new Node(0, 0);
        visited[start.x][start.y] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node top = queue.poll();

            int[][] delta = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
              for (int[] entry : delta) {
                int dx = entry[0];
                int dy = entry[1];
                // if moving out of bounds, ignore
                  if (outOfBounds(graph, top.x + dx, top.y + dy))
                    continue;
                // if moving to deadly zone, ignore
                  if (isDeadly(graph, top.x + dx, top.y + dy))
                    continue;
                int steps = -1;
                if (isNormal(graph, top.x + dx, top.y + dy)) {
                    steps = 0;
                }
                // if in deadly zone, going to harmful zone - loss of one life
                if (isHarmful(graph, top.x + dx, top.y + dy)) {
                    steps = 1;
                }
                  if (steps != -1) {
                    pushToQueue(queue, top, dx, dy, visited, steps);
                }
            }
        }

        return visited[500][500];
    }

    private void pushToQueue(Queue<Node> queue, Node top, int x, int y, int[][] visited, int steps) {
        int newX = top.x + x;
        int newY = top.y + y;
        int loss = visited[top.x][top.y] + steps;
          if (visited[newX][newY] != -1) {
              if (visited[newX][newY] > loss) {
                visited[newX][newY] = loss;
                // once the life required has been updated by a smaller value, we need to run a new search on that node
                queue.add(new Node(newX, newY));
            }
            return;
        }
        queue.add(new Node(newX, newY));
        visited[newX][newY] = loss;
    }

    private boolean outOfBounds(State[][] graph, int x, int y) {
        return x < 0 || y < 0 || x >= graph.length || y >= graph.length;
    }

    private boolean isDeadly(State[][] graph, int x, int y) {
        return graph[x][y] == State.DEADLY;
    }

    private boolean isHarmful(State[][] graph, int x, int y) {
        return graph[x][y] == State.HARMFUL;
    }

    private boolean isNormal(State[][] graph, int x, int y) {
        return graph[x][y] == State.NORMAL;
    }

    private State[][] initializeGraph(String[] harmful, String[] deadly) {
        State[][] graph = new State[501][501];
          for (int i = 0; i < 501; i++) {
              for (int j = 0; j < 501; j++) {
                graph[i][j] = State.NORMAL;
            }
        }
        fill(harmful, graph, State.HARMFUL);
        fill(deadly, graph, State.DEADLY);
        return graph;
    }

    private void fill(String[] states, State[][] graph, State color) {
        for (String s : states) {
            StringTokenizer st = new StringTokenizer(s, " ");
            while (st.hasMoreTokens()) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                  if (x2 < x1) {
                    int tmp = x2;
                    x2 = x1;
                    x1 = tmp;
                }

                  if (y2 < y1) {
                    int tmp = y2;
                    y2 = y1;
                    y1 = tmp;
                }

                for (int i = x1; i <= x2; i++) {
                    for (int j = y1; j <= y2; j++) {
                        graph[i][j] = color;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Escape().lowest(new String[]{"200 100 300 500", "300 100 100 0", "200 400 300 100", "200 400 400 300", "100 400 100 0", "100 200 0 100", "100 0 300 400", "0 300 200 200", "0 300 0 100", "200 300 500 200", "400 300 500 500"}, new String[]{"240 275 125 30", "25 105 0 25", "20 260 475 155"}));
    }
}
