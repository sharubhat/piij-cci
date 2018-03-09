package topcoder.graphs.dfs.tc;

import java.util.StringTokenizer;

/**
 * Ref: https://community.topcoder.com/stat?c=problem_statement&pm=1524&rd=4472
 *
 * P.S: Lot of room for both major and minor mistakes.
 * Instance variable oddCycle needs to be reset for each call to howMany.
 * Created by sharath on 12/28/16.
 */
public class Marketing {
    private static int UNDECIDED = 0;
    private static int TEENAGER = 1;
    private static int ADULT = 2;
    private boolean oddCycle;

    public long howMany(String[] compete) {
        int len = compete.length;
        boolean[][] table = new boolean[len][len];
        int[] color = new int[len];
        oddCycle = false;

          for (int i = 0; i < len; i++) {
            StringTokenizer st = new StringTokenizer(compete[i], " ");
              while (st.hasMoreTokens()) {
                int j = Integer.parseInt(st.nextToken());
                table[i][j] = table[j][i] = true;
            }
        }

        int ncomp = 0;

          for (int i = 0; i < len; i++) {
              if (color[i] == UNDECIDED) {
                ncomp++;
                dfs(table, color, i, TEENAGER);
            }
        }

          if (oddCycle) {
            return -1;
        }

        // This for now is mystery part. Both logic below yield correct answer, but why?
        return (long)Math.pow(2, ncomp);

//        long res = 1;
//        while (ncomp-- > 0) {
//            res *= 2;
//        }
//        return res;
    }

    private void dfs(boolean[][] table, int[] color, int currIndex, int category) {
        int nextCategory = (category == TEENAGER) ? ADULT : TEENAGER;
          if (color[currIndex] != UNDECIDED) {
              if (color[currIndex] != category) {
                oddCycle = true;
            }
            // category is as expected, return
            return;
        }
        color[currIndex] = category;
          for (int i = 0; i < table.length; i++) {
              if (table[currIndex][i]) {
                dfs(table, color, i, nextCategory);
            }
        }
    }

    public static void main(String[] args) {
        Marketing m = new Marketing();
        System.out.println(m.howMany(new String[]{"1 4", "2", "3", "0", ""}));
        System.out.println(m.howMany(new String[]{"1", "2", "0"}));
        System.out.println(m.howMany(new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}));
    }
}
