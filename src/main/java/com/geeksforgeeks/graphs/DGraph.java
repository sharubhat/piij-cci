package com.geeksforgeeks.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Adjacency list representation of undirected graph.
 * There are many ways to represent a graph. Current implementation uses an array of
 * LinkedLists. Alternative is to use a HashMap<Integer, List<Integer>> which under the
 * hood does the same thing.
 */
public class DGraph {
  int v;
  LinkedList<Integer>[] adjListArray;

  DGraph(int v) {
    this.v = v;
    this.adjListArray = new LinkedList[v];
    for (int i = 0; i < v; i++) {
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
    boolean[] visited = new boolean[v];
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(s);
    visited[s] = true;
    while(queue.size() != 0) {
      s = queue.poll();
      actOnVertex(s);
      Iterator<Integer> it = adjListArray[s].iterator();
      while(it.hasNext()) {
        int n = it.next();
        if(!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }

  void dfs(int s) {
    dfs(s, new boolean[this.v]);
  }

  private void dfs(int s, boolean[] visited) {
    visited[s] = true;
    actOnVertex(s);
    Iterator<Integer> it = adjListArray[s].iterator();
    while (it.hasNext()) {
      int n = it.next();
      if(!visited[n]) {
        dfs(n, visited);
      }
    }
  }

  public boolean hasCycle() {
    boolean[] visited = new boolean[v];
    for (int i = 0; i < v; i++) {
      if (!visited[i] && hasCycle(i, visited, new HashSet<>())) {
        return true;
      }
    }
    return false;
  }

  private boolean hasCycle(int s, boolean[] visited, HashSet<Integer> verticesOnRecStack) {
    visited[s] = true;
    verticesOnRecStack.add(s);
    Iterator<Integer> adjIt = this.adjListArray[s].iterator();
    while(adjIt.hasNext()) {
      int n = adjIt.next();
      if (verticesOnRecStack.contains(n)) {
        return true;
      }
      if(!visited[n]) {
        if(hasCycle(n, visited, verticesOnRecStack)) {
          return true;
        }
      }
      verticesOnRecStack.remove(s);
    }
    return false;
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

  public static void main(String args[])
  {
    DGraph g = new DGraph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    System.out.println("Following is Breadth First Traversal "+
            "(starting from vertex 2)");

    System.out.println(g);
    g.bfs(2);
    System.out.println();
    g.dfs(2);
    System.out.println();
    System.out.println(g.hasCycle());

    DGraph notCyclic = new DGraph(4);
    notCyclic.addEdge(1, 2);
    notCyclic.addEdge(2, 3);
    notCyclic.addEdge(3, 0);
    System.out.println(notCyclic.hasCycle());

    DGraph cyclicDisconnected = new DGraph(7);
    cyclicDisconnected.addEdge(0, 1);
    cyclicDisconnected.addEdge(1, 2);
    cyclicDisconnected.addEdge(2, 3);
    cyclicDisconnected.addEdge(4, 5);
    cyclicDisconnected.addEdge(5, 6);
    cyclicDisconnected.addEdge(6, 4);
    System.out.println(cyclicDisconnected.hasCycle());
  }

}

