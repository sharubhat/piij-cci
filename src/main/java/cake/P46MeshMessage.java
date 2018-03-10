package cake;

import java.util.*;

/**
 * Bidirectional bfs is going to be faster. Explore that option.
 */
public class P46MeshMessage {
  public static void main(String[] args) {
    Map<String, String[]> network = new HashMap<String, String[]>() {{
      put("Min", new String[]{"William", "Jayden", "Omar"});
      put("William", new String[]{"Min", "Noam"});
      put("Jayden", new String[]{"Min", "Amelia", "Ren", "Noam"});
      put("Ren", new String[]{"Jayden", "Omar"});
      put("Amelia", new String[]{"Jayden", "Adam", "Miguel"});
      put("Adam", new String[]{"Amelia", "Miguel", "Sofia", "Lucas"});
      put("Miguel", new String[]{"Amelia", "Adam", "Liam", "Nathan"});
      put("Noam", new String[]{"Nathan", "Jayden", "William"});
      put("Omar", new String[]{"Ren", "Min", "Scott"});
    }};
    System.out.println(
        Arrays.toString(new P46MeshMessage().bfs(network, "Jayden", "Adam"))
    );
  }

  public String[] bfs(Map<String, String[]> graph, String startNode, String endNode) {
    if (!graph.containsKey(startNode)) {
      throw new IllegalArgumentException("startNode doesn't exist in graph");
    }
    if (!graph.containsKey(endNode)) {
      throw new IllegalArgumentException("endNode doesn't exist in graph");
    }

    Map<String, String> parents = new LinkedHashMap<>();
    Queue<String> nodesToVisit = new ArrayDeque<>();
    nodesToVisit.add(startNode);
    parents.put(startNode, null);
    while (!nodesToVisit.isEmpty()) {
      String currNode = nodesToVisit.remove();
      if (currNode.equalsIgnoreCase(endNode)) {
        return reconstructPath(endNode, parents);
      }
      String[] neighbours = graph.get(currNode);
      for (String neighbour : neighbours) {
        if (!parents.containsKey(neighbour)) {
          nodesToVisit.add(neighbour);
          parents.put(neighbour, currNode);
        }
      }
    }
    return null;
  }

  private static String[] reconstructPath(String endNode, Map<String, String> parents) {
    List<String> path = new ArrayList<>();
    String currNode = endNode;
    while (currNode != null) {
      path.add(currNode);
      currNode = parents.get(currNode);
    }
    Collections.reverse(path);
    return path.toArray(new String[path.size()]);
  }
}
