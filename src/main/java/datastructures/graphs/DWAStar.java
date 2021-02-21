package datastructures.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DWAStar {
  private static class Edge implements Comparable<Edge>{
    Integer vertex;
    Integer weight;
    Integer distToDest;

    public Edge(Integer vertex, Integer weight) {
      this(vertex, weight, null);
    }
    public Edge(Integer vertex, Integer weight, Integer distToDest) {
      this.vertex = vertex;
      this.weight = weight;
      this.distToDest = distToDest;
    }

    @Override
    public String toString() {
      return "(" + vertex + " " + weight + ")";
    }

    @Override
    public int compareTo(Edge e) {
      if (this.weight == e.weight) {
        return 0;
      } else if (this.weight > e.weight) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  int numVertices;
  LinkedList<Edge>[] adjListArray;

  DWAStar(int n) {
    this.numVertices = n;
    this.adjListArray = new LinkedList[n];
    for (int i = 0; i < n; i++) {
      adjListArray[i] = new LinkedList<>();
    }
  }

  void addEdge(int src, int dest, int weight) {
    this.adjListArray[src].add(new Edge(dest, weight));
  }

  /**
   * A* algorithm can be seen as an heuristic extension of Dijkstra’s.
   * Whereas in the Dijkstra’s priority-queue ordering is based only on the distance
   * from the start node to the current, A* algorithm additionally calculates
   * the distance from the current node to the goal-node.
   * Thus the ordering in the priority queue is different and the algorithm
   * calculates more “straightforward” towards the end node in a graph
   * and hence is significantly faster in finding the path than the Dijkstra.
   *
   * Ref: https://www.redblobgames.com/pathfinding/a-star/introduction.html
   * https://www.youtube.com/watch?v=ySN5Wnu88nE
   *
   * @param source
   */
  public void aStar(int source, int dest, int[] distToDestination) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int[] dist = new int[this.numVertices];
    int[] prev = new int[this.numVertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(prev, -1);

    dist[source] = 0;
    pq.add(new Edge(source, 0, distToDestination[source]));
    while (!pq.isEmpty()) {
      Edge curr = pq.poll();
      if (curr.vertex == dest) {
        constructPath(prev, dest);
        return;
      }
      Iterator<Edge> adjIt = this.adjListArray[curr.vertex].iterator();
      while(adjIt.hasNext()) {
        Edge adjE = adjIt.next();
        // calculate predicted distance to the destination node
        int predictedDistance = heuristic(curr.vertex, distToDestination);

        // 1. calculate distance to neighbor. 2. calculate dist from start node
        int neighborDistance = adjE.weight;
        int totalDistance = curr.weight + neighborDistance + predictedDistance;

        if (dist[adjE.vertex] > totalDistance) {
          dist[adjE.vertex] = totalDistance;
          prev[adjE.vertex] = curr.vertex;
          pq.add(new Edge(adjE.vertex, dist[adjE.vertex], predictedDistance));
        }
      }
    }
    System.out.println(Arrays.toString(prev));
    System.out.println(Arrays.toString(dist));
  }

  private void constructPath(int[] prev, int dest) {
    StringBuilder sb = new StringBuilder();
    sb.append(dest);
    int prevNode = prev[dest];
    while (prevNode != -1) {
      sb.append(prevNode);
      prevNode = prev[prevNode];
    }
    System.out.println(sb.reverse().toString());
  }

  private int heuristic(Integer vertex, int[] distToDestination) {
    return distToDestination[vertex];
  }

  public static void main(String[] args) {
    DWAStar d = new DWAStar(7);
    d.addEdge(0, 1, 4);
    d.addEdge(0, 2, 10);
    d.addEdge(1, 3, 21);
    d.addEdge(2, 4, 5);
    d.addEdge(2, 5, 8);
    d.addEdge(4, 3, 5);
    d.addEdge(5, 3, 12);
    d.addEdge(3, 6, 4);

    d.aStar(0, 6, new int[]{24, 25, 14, 4, 55, 16, 0});
  }
}
