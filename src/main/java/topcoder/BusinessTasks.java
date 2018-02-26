package topcoder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BusinessTasks {
  public String getTask(String[] list, int n) {
    List<String> tasks = new LinkedList<>();
    Collections.addAll(tasks, list);

    int indexToRemove = 0;
    while (tasks.size() > 1) {
      indexToRemove = (indexToRemove + n - 1) % tasks.size();
      tasks.remove(indexToRemove);
    }
    return tasks.get(0);
  }

  public static void main(String[] args) {
    BusinessTasks bt = new BusinessTasks();
    System.out.println(bt.getTask(new String[] {"a", "b", "c", "d"}, 2));
    System.out.println(bt.getTask(new String[] {"a","b"}, 1000));
    System.out.println(bt.getTask(new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t", "u","v","w","x","y","z"}, 17));
  }
}
