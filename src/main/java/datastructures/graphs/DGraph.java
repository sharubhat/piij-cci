package geeksforgeeks.graphs;

import java.util.*;

/**
 * Adjacency list representation of directed graph.
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

  /**
   * It's important not to visit a node twice. It can be achieved in many way. Either use a boolean array
   * of visited nodes(or set) or check if a level or parent has been defined for the node in question.
   * Using Integer array instead of primitive int array avoids the necessity to initialize the array.
   *
   * @param s source node
   */
  public void bfs(int s) {
    Integer[] levels = new Integer[this.numVertices];
    Integer[] parents = new Integer[this.numVertices];
    Queue<Integer> verticesToVisit = new ArrayDeque<>();
    verticesToVisit.add(s);
    int level = 0;
    levels[s] = level++;
    parents[s] = -1;
    while (!verticesToVisit.isEmpty()) {
      s = verticesToVisit.poll();
      actOnVertex(s);
      Iterator<Integer> it = adjListArray[s].iterator();
      while (it.hasNext()) {
        int n = it.next();
        if (parents[n] == null) {
          verticesToVisit.add(n);
          levels[n] = level;
          parents[n] = s;
        }
      }
      level++;
    }
    System.out.println("Level : " + Arrays.toString(levels));
    System.out.println("Parent : " + Arrays.toString(parents));
  }

  /**
   * Strategy explained by MIT professor Erik Demaine.
   * No need to use visited flag per node. Tracking parent helps in solving various problems and also
   * can be used as alternative for visited flag.
   * Topological Sort:
   * Reversing the finishing times of the vertices (order in which the leaf nodes in dfs tree get visited)
   * provides topological sort. An example problem is job scheduling. This can be easily solved by
   * running dfs on each node and adding them to a list and then reversing or adding to beginning of
   * linked list which naturally reverses the order.
   */
  public void dfs() {
    LinkedList<Integer> topologicalSort = new LinkedList<>();
    Integer[] parents = new Integer[this.numVertices];
    for (int i = 0; i < this.numVertices; i++) {
      if (parents[i] == null) {
        dfsVisit(i, parents, topologicalSort);
      }
    }
    System.out.println("DFS parents : " + Arrays.toString(parents));
    System.out.println("Topological sort : " + topologicalSort);
  }

  private void dfsVisit(int s, Integer[] parents, LinkedList<Integer> tSort) {
    Iterator<Integer> it = adjListArray[s].iterator();
    actOnVertex(s);
    while (it.hasNext()) {
      int curr = it.next();
      if(parents[curr] == null) {
        parents[curr] = s;
        dfsVisit(curr, parents, tSort);
      }
    }
    tSort.addFirst(s);
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

    DGraph dfsG = new DGraph(6);
    dfsG.addEdge(0, 1);
    dfsG.addEdge(1, 4);
    dfsG.addEdge(4, 3);
    dfsG.addEdge(3, 1);
    dfsG.addEdge(0, 3);
    dfsG.addEdge(2, 4);
    dfsG.addEdge(2, 5);
    dfsG.addEdge(5, 5);

    dfsG.dfs();

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
  }

}

