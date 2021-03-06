package datastructures.graphs;

import java.util.*;

/**
 * Adjacency list representation of undirected graph
 */
public class UndirectedGraph extends Graph {

  UndirectedGraph(int v) {
    this.numVertices = v;
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

  public int lengthOfShortestPath(int source, int dest) {
    boolean[] visited = new boolean[this.numVertices];
    int[] prev = new int[this.numVertices];
    Arrays.fill(prev, -1);
    Queue<Integer> q = new LinkedList<>();
    q.add(source);
    visited[source] = true;
    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int n : this.adjListArray[curr]) {
        if (!visited[n]) {
          q.add(n);
          visited[n] = true;
          prev[n] = curr;
        }
      }
    }
    int curr = dest;
    int level = 0;
    LinkedList<Integer> shortestPath = new LinkedList<>();
    shortestPath.addFirst(curr);
    while (prev[curr] != -1) {
      curr = prev[curr];
      shortestPath.addFirst(curr);
      level++;
    }
    System.out.println(shortestPath);
    return level;
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
    System.out.println(graph.lengthOfShortestPath(0, 3));
  }
}
