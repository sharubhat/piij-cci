package com.piij.cci.topcoder.graphs.dijkstra.robocourier;

import java.util.*;

/**
 * Ref: http://community.topcoder.com/tc?module=ProblemDetail&rd=4555&pm=1749
 * 
 * First thing to understand here is that when robot turns right or left, it just turns by 60 degree w.r.t
 * current facing direction and doesn't actually move. It's very important to understand that it is just a
 * regular grid, however you are allowed to walk into only nodes that are corners of hexagon.
 * Refer to picture in this package.
 *
 * Created by sharath on 1/6/17.
 */
public class RoboCourier {
    private Map<Node, Integer> map;

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            return y == node.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    private static class Graph {
        List<Set<Integer>> adj = new ArrayList<>();

        public void addEdge(int u, int v) {
            assert u < adj.size() && v < adj.size();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public void addNode() {
            adj.add(new HashSet<>());
        }
    }

    class State implements Comparable<State> {
        int cost, dir, mov;
        Node node;

        public State(Node node, int dir, int cost, int mov) {
            this.node = node;
            this.dir = dir;
            this.cost = cost;
            this.mov = mov;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.cost, o.cost);
        }

        int getVisitedArrayNum() {
            return map.get(node) * 6 + dir;
        }
    }

    public int timeToDeliver(String[] path) {
        int[] dx = {0, -1, -1, 0, 1, 1};
        int[] dy = {2, 1, -1, -2, -1, 1};
        int dir = 0, count = 0;

        Node cur = new Node(0, 0);
        map = new HashMap<>();
        map.put(cur, 0);
        count++;

        Graph g = new Graph();
        g.addNode();

        Node start = new Node(0, 0);
        for (String pathIter : path) {
            for (char c : pathIter.toCharArray()) {
                if (c == 'R') {
                    dir = (dir + 1) % dx.length;
                } else if (c == 'L') {
                    dir = (dir + 5) % dx.length;
                } else {
                    Node next = new Node(cur.x + dx[dir], cur.y + dy[dir]);
                    if (!map.containsKey(next)) {
                        map.put(next, count);
                        count++;
                        g.addNode();
                    }
                    g.addEdge(map.get(cur), map.get(next));
                    cur = next;
                }
            }
        }
        Node target = cur;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(start, 0, 0, 0));
        boolean[] visited = new boolean[count * 6];
        int ret = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            State top = pq.remove();
            if (visited[top.getVisitedArrayNum()])
                continue;
            visited[top.getVisitedArrayNum()] = true;

            if (top.node.equals(target) && top.cost < ret) {
                ret = top.cost;
            }

            // move forward
            State prev = top;
            Node nextNode = new Node(prev.node.x + dx[prev.dir], prev.node.y + dy[prev.dir]);
            int newCost = prev.cost + (prev.mov < 2 ? 4 : 2);
            State nextState = new State(nextNode, prev.dir, newCost, Math.min(2, prev.mov + 1));
            while (g.adj.get(map.get(prev.node)).contains(map.get(nextNode)) && !visited[nextState.getVisitedArrayNum()]) {
                pq.add(nextState);
                prev = nextState;
                newCost = prev.cost + (prev.mov < 2 ? 4 : 2);
                nextNode = new Node(prev.node.x + dx[prev.dir], prev.node.y + dy[prev.dir]);
                nextState = new State(nextNode, prev.dir, newCost, Math.min(2, prev.mov + 1));
            }

            // turn right
            int nextDir = (top.dir + 1) % dx.length;
            nextState = new State(top.node, nextDir, top.cost + 3, 0);
            if (!visited[nextState.getVisitedArrayNum()]) {
                pq.add(nextState);
            }

            // turn left
            nextDir = (top.dir + 5) % dx.length;
            nextState = new State(top.node, nextDir, top.cost + 3, 0);
            if (!visited[nextState.getVisitedArrayNum()]) {
                pq.add(nextState);
            }
        }
        return ret;
    }
}
