package codechef;

import java.util.Scanner;

/**
 * https://www.codechef.com/problems/TLG
 */
public class P1LeadGame {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int rounds = Integer.parseInt(scanner.nextLine());
    int player1Score = 0;
    int player2Score = 0;
    int max = 0;
    String leader = "";

    for (int i = 0; i < rounds; i++) {
      String[] scores = scanner.nextLine().split(" ");
      player1Score += Integer.parseInt(scores[0]);
      player2Score += Integer.parseInt(scores[1]);

      int lead = Math.abs(player1Score - player2Score);
      if (max < lead) {
        max = lead;
        leader = player1Score > player2Score ? "1" : "2";
      }
    }

    System.out.println(leader + " " + max);
  }
}
