package datastructures.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DWGraph {
  private static class Edge implements Comparable<Edge>{
    Integer vertex;
    Integer weight;

    public Edge(Integer vertex, Integer weight) {
      this.vertex = vertex;
      this.weight = weight;
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

  DWGraph(int n) {
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
   * Dijkstra's single source shortest path using min heap or priority queue.
   * Works on directed or undirected, weighted, acyclic graphs with no negative weights.
   * Additional reference : https://www.youtube.com/watch?v=GazC3A4OQTE
   *
   * @param source
   */
  public void dijkstra(int source) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int[] dist = new int[this.numVertices];
    int[] prev = new int[this.numVertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(prev, -1);

    dist[source] = 0;
    pq.add(new Edge(source, 0));
    while (!pq.isEmpty()) {
      Edge curr = pq.poll();
      for (Edge adjE : this.adjListArray[curr.vertex]) {
        // if existing weight is more than new total weight to reach this node, take it
        if (dist[adjE.vertex] > curr.weight + adjE.weight) {
          dist[adjE.vertex] = curr.weight + adjE.weight;
          prev[adjE.vertex] = curr.vertex;
          pq.add(new Edge(adjE.vertex, dist[adjE.vertex]));
        }
      }
    }
    System.out.println(Arrays.toString(prev));
    System.out.println(Arrays.toString(dist));
  }

  /**
   * Single source shortest path for directed graphs with no negative cycles. O(VE)
   * Also discovers negative cycles.
   * Pseudo code:
   * Initialize();
   * // for shortest path p = v0 -> v1 -> v2 ->...vk, k = |v| - 1. There will be no cycles.
   * for i = 1 to |v| - 1
   *  for each edge (u, v) belongs to E
   *    relax(u, v, w)
   * // check for -ve cycles
   * for each edge in graph
   *  if d[v] > d[u] + w(u,v)
   *    then there exists -ve cycle
   * relax:
   * if d[v] > d[u] + w(u,v)
   *   d[v] = d[u] + w(u,v)
   *   parent[v] = u
   * @param source source vertex
   */
  public void belmanFord(int source) {
    int[] dist = new int[this.numVertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    int[] prev = new int[this.numVertices];
    Arrays.fill(prev, -1);
    dist[source] = 0;

    for (int i = 1; i < this.numVertices; i++) {
      for (int u = 0; u < this.adjListArray.length; u++) {
        for (Edge v : this.adjListArray[u]) {
          if (dist[u] != Integer.MAX_VALUE && dist[v.vertex] > v.weight + dist[u]) {
            dist[v.vertex] = v.weight + dist[u];
            prev[v.vertex] = u;
          }
        }
      }
    }
    System.out.println(Arrays.toString(prev));
    System.out.println(Arrays.toString(dist));

    for (int u = 0; u < this.numVertices; u++) {
      for (Edge v : this.adjListArray[u]) {
        if (dist[v.vertex] != Integer.MAX_VALUE &&
                dist[v.vertex] > v.weight + dist[u]) {
          throw new RuntimeException("Graph contains negative cycles");
        }
      }
    }
  }

  public static void main(String[] args) {
    DWGraph d = new DWGraph(7);
    d.addEdge(0, 1, 4);
    d.addEdge(0, 2, 10);
    d.addEdge(1, 3, 21);
    d.addEdge(2, 4, 5);
    d.addEdge(2, 5, 8);
    d.addEdge(4, 3, 5);
    d.addEdge(5, 3, 12);
    d.addEdge(3, 6, 4);

    d.dijkstra(0);
    d.belmanFord(0);

    DWGraph noNegativeCycle = new DWGraph(5);
    noNegativeCycle.addEdge(0, 1, 1);
    noNegativeCycle.addEdge(1, 3, 2);
    noNegativeCycle.addEdge(3, 4, 1);
    noNegativeCycle.addEdge(3, 2, -1);
    noNegativeCycle.addEdge(2, 1, 2);
    noNegativeCycle.belmanFord(0);

    DWGraph negativeCycle = new DWGraph(5);
    negativeCycle.addEdge(0, 1, 1);
    negativeCycle.addEdge(1, 3, 2);
    negativeCycle.addEdge(3, 4, 1);
    negativeCycle.addEdge(3, 2, -4);
    negativeCycle.addEdge(2, 1, 1);
    negativeCycle.belmanFord(0);
  }
}
