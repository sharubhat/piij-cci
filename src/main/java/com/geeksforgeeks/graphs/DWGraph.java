package com.geeksforgeeks.graphs;

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
   * Dijkstra's single source shortest path using min heap or priority queue
   *
   * @param source
   */
  public void shortestPath(int source) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    int[] dist = new int[this.numVertices];
    int[] prev = new int[this.numVertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(prev, -1);

    dist[source] = 0;
    pq.add(new Edge(source, 0));
    while (!pq.isEmpty()) {
      Edge curr = pq.poll();
      Iterator<Edge> adjIt = this.adjListArray[curr.vertex].iterator();
      while(adjIt.hasNext()) {
        Edge adjE = adjIt.next();
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

    d.shortestPath(0);
  }
}
