package com.cake;

import java.util.Arrays;

public class P32PopularGameScores {
  public static void main(String[] args) {
    int[] unsortedScores = {37, 89, 41, 65, 91, 89, 53};
    final int HIGHEST_POSSIBLE_SCORE = 100;
    System.out.println("Output   : " + Arrays.toString(sortScores(unsortedScores, HIGHEST_POSSIBLE_SCORE)));
    System.out.println("Expected : " + Arrays.toString(new int[]{91, 89, 89, 65, 53, 41, 37}));
  }

  public static int[] sortScores(int[] unsortedScores, int highestScore) {
    int[] scoreCounts = new int[highestScore + 1];
    for (int score : unsortedScores) {
      scoreCounts[score]++;
    }
    int[] sortedScores = new int[unsortedScores.length];
    int j = 0;
    for (int score = highestScore; score >= 0; score--) {
      int count = scoreCounts[score];
      for (int occurrence = 0; occurrence < count; occurrence++) {
        sortedScores[j++] = score;
      }
    }
    return sortedScores;
  }
}
