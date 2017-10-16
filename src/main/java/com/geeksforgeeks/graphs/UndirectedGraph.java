package com.geeksforgeeks.graphs;

import java.util.LinkedList;

/**
 * Adjacency list representation of undirected graph
 */
public class UndirectedGraph {
  int v;
  LinkedList<Integer>[] adjListArray;

  UndirectedGraph(int v) {
    this.v = v;
    this.adjListArray = new LinkedList[v];
    for (int i = 0; i < v; i++) {
      adjListArray[i] = new LinkedList<>();
    }
  }

  /**
   * Adds an edge from src to dest. Since the graph is undirected, it also adds an
   * edge from dest to src.
   * @param src  source vertex
   * @param dest destination vertex
   */
  void addEdge(int src, int dest) {
    this.adjListArray[src].addFirst(dest);
    this.adjListArray[dest].addFirst(src);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < this.v; i++) {
      sb.append("Adjacency list of vertex ").append(i).append(" : ");
      sb.append(this.adjListArray[i]);
      sb.append("\n");
    }
    return sb.toString();
  }

  public static void main(String args[]) {
    // create the graph given in above figure
    int V = 5;
    UndirectedGraph graph = new UndirectedGraph(V);
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(1, 4);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);

    // print the adjacency list representation of
    // the above graph
    System.out.println(graph);
  }
}
