package com.geeksforgeeks.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Adjacency list representation of undirected graph.
 * There are many ways to represent a graph. Current implementation uses an array of
 * LinkedLists. Alternative is to use a map of Integer -> List of Integers, which under the
 * hood does the same thing.
 * Note: Generally m denotes number of vertices and n denotes number of edges.
 */
public class DGraph {
  int numVertices;
  LinkedList<Integer>[] adjListArray;

  DGraph(int n) {
    this.numVertices = n;
    this.adjListArray = new LinkedList[n];
    for (int i = 0; i < n; i++) {
      adjListArray[i] = new LinkedList<>();
    }
  }

  /**
   * Adds an edge from src to dest.
   * @param src  source vertex
   * @param dest destination vertex
   */
  void addEdge(int src, int dest) {
    this.adjListArray[src].add(dest);
  }

  private void actOnVertex(int s) {
    System.out.print(s + " ");
  }

  void bfs(int s) {
    boolean[] visited = new boolean[this.numVertices];
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(s);
    visited[s] = true;
    while (queue.size() != 0) {
      s = queue.poll();
      actOnVertex(s);
      Iterator<Integer> it = adjListArray[s].iterator();
      while (it.hasNext()) {
        int n = it.next();
        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }

  void dfs(int s) {
    dfs(s, new boolean[this.numVertices]);
  }

  private void dfs(int s, boolean[] visited) {
    visited[s] = true;
    actOnVertex(s);
    Iterator<Integer> it = adjListArray[s].iterator();
    while (it.hasNext()) {
      int n = it.next();
      if (!visited[n]) {
        dfs(n, visited);
      }
    }
  }

  /**
   * Detects presence of cycles in a directed graph.
   *
   * @return boolean
   */
  public boolean hasCycle() {
    boolean[] visited = new boolean[this.numVertices];
    for (int i = 0; i < this.numVertices; i++) {
      if (!visited[i] && hasCycle(i, visited, new HashSet<>())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Recursive helper for finding cycles.
   *
   * @param s                  starting vertex.
   * @param visited            boolean array to keep track of visited vertices.
   * @param verticesOnRecStack HashSet to keep track of vertices in current path.
   * @return boolean
   */
  private boolean hasCycle(int s, boolean[] visited, HashSet<Integer> verticesOnRecStack) {
    visited[s] = true;
    verticesOnRecStack.add(s);
    Iterator<Integer> adjIt = this.adjListArray[s].iterator();
    while (adjIt.hasNext()) {
      int n = adjIt.next();
      if (verticesOnRecStack.contains(n)) {
        return true;
      }
      if (!visited[n]) {
        if (hasCycle(n, visited, verticesOnRecStack)) {
          return true;
        }
      }
      verticesOnRecStack.remove(s);
    }
    return false;
  }

  /**
   * Finds out if there is a path between two nodes of a directed graph.
   *
   * @param s starting vertex
   * @param e destination vertex
   * @return boolean
   */
  public boolean pathExists(int s, int e) {
    return pathExists(s, e, new boolean[this.numVertices]);
  }

  private boolean pathExists(int s, int e, boolean[] visited) {
    visited[s] = true;
    if (s == e) {
      return true;
    }
    Iterator<Integer> adjIt = this.adjListArray[s].iterator();
    while (adjIt.hasNext()) {
      int n = adjIt.next();
      if (!visited[n]) {
        return pathExists(n, e, visited);
      }
    }
    return false;
  }

  /**
   * Prints one of the possible topological sort of vertices.
   */
  public void topologicalSort() {
    Stack stack = new Stack();
    boolean[] visited = new boolean[this.numVertices];

    for (int i = 0; i < this.numVertices; i++) {
      if (!visited[i]) {
        topologicalSort(i, visited, stack);
      }
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
  }

  private void topologicalSort(int s, boolean[] visited, Stack stack) {
    visited[s] = true;
    Iterator<Integer> adjIt = this.adjListArray[s].iterator();
    while (adjIt.hasNext()) {
      int n = adjIt.next();
      if(!visited[n]) {
        topologicalSort(n, visited, stack);
      }
    }
    stack.push(s);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < this.numVertices; i++) {
      sb.append("Adjacency list of vertex ").append(i).append(" : ");
      sb.append(this.adjListArray[i]);
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * A bunch of small tests to verify the basic functionalities.
   * @param args input args
   */
  public static void main(String[] args) {
    DGraph g = new DGraph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    System.out.println("Following is Breadth First Traversal "
            + "(starting from vertex 2)");

    System.out.println(g);
    g.bfs(2);
    System.out.println();
    g.dfs(2);
    System.out.println();
    System.out.println("Cycle exists for cyclic graph : " + g.hasCycle());

    DGraph notCyclic = new DGraph(4);
    notCyclic.addEdge(1, 2);
    notCyclic.addEdge(2, 3);
    notCyclic.addEdge(3, 0);
    System.out.println("Cycle exists for acyclic graph : " + notCyclic.hasCycle());

    DGraph cyclicDisconnected = new DGraph(7);
    cyclicDisconnected.addEdge(0, 1);
    cyclicDisconnected.addEdge(1, 2);
    cyclicDisconnected.addEdge(2, 3);
    cyclicDisconnected.addEdge(4, 5);
    cyclicDisconnected.addEdge(5, 6);
    cyclicDisconnected.addEdge(6, 4);
    System.out.println("Cycle exists for cyclic disconnected graph : "
            + cyclicDisconnected.hasCycle());
    System.out.printf("Path exists between %d and %d : %s\n",
            1, 3, cyclicDisconnected.pathExists(1, 3));
    System.out.printf("Path exists between %d and %d : %s\n",
            1, 5, cyclicDisconnected.pathExists(1, 5));

    // Create a graph given in the above diagram
    DGraph topSortG = new DGraph(6);
    topSortG.addEdge(5, 2);
    topSortG.addEdge(5, 0);
    topSortG.addEdge(4, 0);
    topSortG.addEdge(4, 1);
    topSortG.addEdge(2, 3);
    topSortG.addEdge(3, 1);

    System.out.println("Topological sort of the given graph : ");
    topSortG.topologicalSort();
  }

}

