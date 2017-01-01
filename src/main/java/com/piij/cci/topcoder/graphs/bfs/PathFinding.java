package com.piij.cci.topcoder.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ref: http://www.topcoder.com/tc?module=ProblemDetail&rd=4585&pm=1110
 *
 * Created by sharath on 12/31/16.
 */
public class PathFinding {
    private static class Node {
        int ax, ay, bx, by;
        int steps;  // current num of steps to reach this step

        public Node(int ax, int ay, int bx, int by, int steps) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ax=" + ax +
                    ", ay=" + ay +
                    ", bx=" + bx +
                    ", by=" + by +
                    ", steps=" + steps +
                    '}';
        }
    }

    public int minTurns(String[] board) {
        char[][] graph = initializeGraph(board);
        int width = graph.length;
        int height = graph[0].length;
        boolean[][][][] visited = new boolean[width][height][width][height];

        Node start = findPosAandB(graph);

        Queue<Node> queue = new LinkedList<>();
        pushToQueue(queue, start, visited);

        while(!queue.isEmpty()) {
            Node top = queue.poll();

            // Check if player 1 or player 2 is on top of each other, if so continue
            if(steppingOnEachOther(top))
                continue;

            // Check if the current positions of A and B are the opposite of what they were in start.
            // If they are we have exchanged positions and are finished!
            if (top.ax == start.bx && top.ay == start.by &&
                    top.bx == start.ax && top.by == start.ay)
                return top.steps;
            // Now we need to generate all of the transitions between nodes, we can do this quite easily using some
            // nested for loops, one for each direction that it is possible for one player to move.  Since we need
            // to generate the following deltas: (-1,-1), (-1,0), (-1,1), (0,-1), (0,0), (0,1), (1,-1), (1,0), (1,1)
            // we can use a for loop from -1 to 1 to do exactly that.
            for (int axDelta = -1; axDelta <= 1; axDelta++) {
                for (int ayDelta = -1; ayDelta <= 1; ayDelta++) {
                    for (int bxDelta = -1; bxDelta <= 1; bxDelta++) {
                        for (int byDelta = -1; byDelta <= 1; byDelta++) {
                            // Careful though!  We have to make sure that player 1 and 2 did not swap positions on this turn
                            if ((top.ax == top.bx + bxDelta) && (top.ay == top.by + byDelta) &&
                                    (top.bx == top.ax + axDelta) && (top.by == top.ay + ayDelta))
                                continue;

                            Node adj = new Node(top.ax + axDelta, top.ay + ayDelta,
                                    top.bx + bxDelta, top.by + byDelta,
                                    top.steps + 1);
                            // Check if player 1 or player 2 is out of bounds, or on an X square, if so continue
                            if(outOfBounds(adj, width, height) || isHittingWall(adj, graph))
                                continue;

                            // Add the new node into the queue
                            pushToQueue(queue, adj, visited);
                        }
                    }
                }
            }
        }

        // It is not possible to exchange positions, so
        // we return -1.  This is because we have explored
        // all the states possible from the starting state,
        // and haven't returned an answer yet.
        return -1;
    }

    private boolean isHittingWall(Node adj, char[][] graph) {
        return graph[adj.ax][adj.ay] == 'X' || graph[adj.bx][adj.by] == 'X';
    }

    private boolean steppingOnEachOther(Node top) {
        return top.ax == top.bx && top.ay == top.by;
    }

    private boolean outOfBounds(Node top, int width, int height) {
        return top.ax < 0 || top.ay < 0 || top.ax >= width || top.ay >= height ||
                top.bx < 0 || top.by < 0 || top.bx >= width || top.by >= height;
    }

    private void pushToQueue(Queue<Node> queue, Node v, boolean[][][][] visited) {
        if(visited[v.ax][v.ay][v.bx][v.by])
            return;
        queue.add(v);
        visited[v.ax][v.ay][v.bx][v.by] = true;
    }

    private Node findPosAandB(char[][] graph) {
        int ax = 0, ay = 0, bx = 0, by = 0;
        for(int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 'A') {
                    ax = i;
                    ay = j;
                }
                if(graph[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }
        return new Node(ax, ay, bx, by, 0);
    }

    private char[][] initializeGraph(String[] board) {
        int length = board.length;
        int width = board[0].length();
        char[][] graph = new char[length][width];
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++)
                graph[i][j] = board[i].charAt(j);
        }
        return graph;
    }

    public static void main(String[] args) {
        // expected 397
        System.out.println(new PathFinding().minTurns(new String[]{
                "AB.................X",
                "XXXXXXXXXXXXXXXXXXX.",
                "X................XX.",
                ".XXXXXXXXXXXXXXXX.X.",
                ".XX............XX.X.",
                ".X.XXXXXXXXXXXX.X.X.",
                ".X.XX........XX.X.X.",
                ".X.X.XXXXXXXX.X.X.X.",
                ".X.X.XX....XX.X.X.X.",
                ".X.X.X.XXXX.X.X.X.X.",
                ".X.X.X.X.XX.X.X.X.X.",
                ".X.X.X.X...XX.X.X.X.",
                ".X.X.X.XXXXXX.X.X.X.",
                ".X.X.XX......XX.X.X.",
                ".X.X.XXXXXXXXXX.X.X.",
                ".X.XX..........XX.X.",
                ".X.XXXXXXXXXXXXXX.X.",
                ".XX..............XX.",
                ".XXXXXXXXXXXXXXXXXX.",
                "X..................X"}
                ));
        // expected 2
        System.out.println(new PathFinding().minTurns(new String[]{"....",
                ".A..",
                "..B.",
                "...."}
                ));
        // expected -1
        System.out.println(new PathFinding().minTurns(new String[]{
                "A.................XX",
                ".................XXX",
                "................XXX.",
                "...............XXX..",
                "..............XXX...",
                ".............XXX....",
                "............XXX.....",
                "...........XXX......",
                "..........XXX.......",
                ".........XXX........",
                "........XXX.........",
                ".......XXX..........",
                "......XXX...........",
                ".....XXX............",
                "....XXX.............",
                "...XXX..............",
                "..XXX...............",
                ".XXX................",
                "XXX.................",
                "XX.................B"}
                ));
    }
}
