package topcoder.graphs.dijkstra;

import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * Created by sharath on 1/5/17.
 */
public class KiloManX {
    private static class Node implements Comparable<Node>{
        int weapons;
        int shots;
        public Node(int weapons, int shots){
            this.weapons = weapons;
            this.shots = shots;
        }
        @Override
        public int compareTo(Node n){
            return Integer.compare(shots, n.shots);
        }
    }

    public int leastShots(String[] damageChart, int[] bossHealth) {
        int numWeapons = damageChart.length;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        BitSet visited = new BitSet(1 << numWeapons);

          while (!pq.isEmpty()) {
            Node top = pq.remove();

              if (visited.get(top.weapons))
                continue;
            visited.set(top.weapons);

            // A quick trick to check if we have all the weapons, meaning we defeated all the bosses.
            // We use the fact that (2^numWeapons - 1) will have all the numWeapons bits set to 1.
            if (top.weapons == (1 << numWeapons) - 1) {
                return top.shots;
            }

              for (int i = 0; i < numWeapons; i++) {
                // Check if we've already visited this boss, then don't bother trying him again
                if (((top.weapons >> i) & 1) != 0) continue;

                // Now figure out what the best amount of time that we can destroy this boss is, given the weapons we have.
                // We initialize this value to the boss's health, as that is our default (with our KiloBuster).
                int best = bossHealth[i];
                  for (int j = 0; j < numWeapons; j++) {
                    // cannot defeat same person with same weapon
                      if (i == j)
                        continue;
                      if ((((top.weapons >> j) & 1) != 0) && damageChart[j].charAt(i) != '0') {
                        // We have this weapon, so try using it to defeat this boss
                        int shotsNeeded = bossHealth[i] / (damageChart[j].charAt(i) - '0');
                        if (bossHealth[i] % (damageChart[j].charAt(i) - '0') != 0) {
                            shotsNeeded++;
                        }
                        best = Math.min(best, shotsNeeded);
                    }
                }
                // Add the new node to be searched, showing that we defeated boss i, and we used 'best' shots to defeat him.
                pq.add(new Node(top.weapons | (1 << i), top.shots + best));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KiloManX().leastShots(new String[]{"070","500","140"}, new int[]{150,150,150}));
    }
}
