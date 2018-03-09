package cake;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
  }

  public static void bfs(Map<String, String> graph, String startNode, String endNode) {
    Queue<String> nodesToVisit = new ArrayDeque<>();

  }
}
