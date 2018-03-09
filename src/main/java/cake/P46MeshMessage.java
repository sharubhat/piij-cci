package cake;

import java.util.*;

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
    new P46MeshMessage().bfs(network, "Jayden", null);
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
    parents.put(startNode, "");
    while (!nodesToVisit.isEmpty()) {
      String currNode = nodesToVisit.remove();
      if (currNode.equalsIgnoreCase(endNode)) {
        return reconstructPath(startNode, endNode, parents);
      }
      String[] neighbours = graph.get(currNode);
      for (String neighbour : neighbours) {
        if (parents.get(neighbour) == null) {
          nodesToVisit.add(neighbour);
          parents.put(neighbour, currNode);
        }
      }
    }
    return null;
  }

  private static String[] reconstructPath(String startNode, String endNode, Map<String, String> parents) {
    List<String> path = new ArrayList<>();
    String parent = parents.get(endNode);
    path.add(endNode);
    while (!parent.equalsIgnoreCase(startNode) && !parent.equals("")) {
      path.add(parent);
      parent = parents.get(parent);
    }
    Collections.reverse(path);
    return path.toArray(new String[path.size()]);
  }
}
