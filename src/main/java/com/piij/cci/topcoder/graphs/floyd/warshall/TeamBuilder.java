package com.piij.cci.topcoder.graphs.floyd.warshall;

import java.util.Arrays;

/**
 * Ref: http://community.topcoder.com/tc?module=ProblemDetail&rd=4740&pm=2356
 *
 * Created by sharath on 1/6/17.
 */
public class TeamBuilder {
    public int[] specialLocations(String[] paths) {
        int[] ret = {0, 0};
        boolean[][] adj = new boolean[paths.length][paths.length];

        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++) {
                if (i == j || paths[i].charAt(j) == '1') {
                    adj[i][j] = true;
                }
            }
        }

        for (int k = 0; k < adj.length; k++){
            for (int i = 0; i < adj.length; i++){
                for (int j = 0; j < adj.length; j++){
                    adj[i][j] = adj[i][j] || adj[i][k] && adj[k][j];
                }
            }
        }

        // if entire row is true, it means location at index i connects to all other locations
          for (int i = 0; i < adj.length; i++) {
            boolean connected = true;
              for (int j = 0; j < adj.length; j++) {
                  if (!adj[i][j]) {
                    connected = false;
                    break;
                }
            }
              if (connected)
                ret[0]++;
        }

        // if entire column is true, it means location at index i is connected to all other locations
          for (int i = 0; i < adj.length; i++) {
            boolean connected = true;
              for (int j = 0; j < adj.length; j++) {
                  if (!adj[j][i]) {
                    connected = false;
                    break;
                }
            }
              if (connected)
                ret[1]++;
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TeamBuilder().specialLocations(new String[]{"0110000", "1000100", "0000001", "0010000", "0110000", "1000010", "0001000"})));
    }
}
