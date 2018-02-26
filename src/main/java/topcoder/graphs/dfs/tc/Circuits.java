package com.topcoder.graphs.dfs.tc;

import java.util.StringTokenizer;

/**
 * Ref: http://community.topcoder.com/tc?module=ProblemDetail&rd=4494&pm=1593
 *
 * Created by sharath on 12/29/16.
 */
public class Circuits {
    public int howLong(String[] connects, String[] costs) {
        int[][] graph = initializeGraph(connects, costs);

          for (int i = 0; i < connects.length; i++) {
              for (int j = 0; j < connects.length; j++) {
                  for (int k = 0; k < connects.length; k++) {
                      if (graph[i][j] != 0 && graph[j][k] != 0 && graph[i][k] < graph[i][j] + graph[j][k]) {
                        graph[i][k] = graph[i][j] + graph[j][k];
                    }
                }
            }
        }

        return getMaxLength(graph);
    }

    private int getMaxLength(int[][] graph) {
        int maxLength = 0;
          for (int i = 0; i < graph.length; i++) {
              for (int j = 0; j < graph[0].length; j++) {
                maxLength = maxLength < graph[i][j] ? graph[i][j] : maxLength;
            }
        }
        return maxLength;
    }

    private int[][] initializeGraph(String[] connects, String[] costs) {
        int[][] graph = new int[connects.length][connects.length];
          for (int i = 0; i < connects.length; i++) {
            StringTokenizer stConnects = new StringTokenizer(connects[i], " ");
            StringTokenizer stCosts = new StringTokenizer(costs[i], " ");
              while (stConnects.hasMoreTokens()) {
                int con = Integer.parseInt(stConnects.nextToken());
                int cost = Integer.parseInt(stCosts.nextToken());
                graph[i][con] = cost;
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(new Circuits().howLong(
                new String[]{"1 2", "2", ""},
                new String[]{"5 3", "7", ""}));
        System.out.println(new Circuits().howLong(
                new String[]{"", "2 3 5", "4 5", "5 6", "7", "7 8", "8 9", "10", "10 11 12", "11", "12", "12", ""},
                new String[]{"", "3 2 9", "2 4", "6 9", "3", "1 2", "1 2", "5", "5 6 9", "2", "5", "3", ""}));
    }

}
