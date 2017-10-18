package com.geeksforgeeks.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

  public void shortestPath(int s) {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    List<Integer> dist = new ArrayList<>();
    List<Integer> prev = new ArrayList<>();

    for (int i = 0; i < this.numVertices; i++) {
      if(i == s) {
        dist.add(0);
        prev.add(s);
        pq.add(new Edge(i, 0));
      } else {
        dist.add(Integer.MAX_VALUE);
        prev.add(-1);
        pq.add(new Edge(i, Integer.MAX_VALUE));
      }
    }

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      int v = e.vertex;
      int w = e.weight;
      Iterator<Edge> adjIt = this.adjListArray[v].iterator();
      while(adjIt.hasNext()) {
        Edge edge = adjIt.next();

      }
    }

  }
}
