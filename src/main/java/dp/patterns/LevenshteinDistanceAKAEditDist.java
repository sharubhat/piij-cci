package dp.patterns;

/**
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/2-dimensional/tutorial/
 * Edit distance is a way of quantifying how dissimilar two strings are, i.e., how many operations (add, delete or replace character)
 * it would take to transform one string to the other. This is one of the most common variants of edit distance,
 * also called Levenshtein distance, named after Soviet computer scientist, Vladimir Levenshtein.
 * There are 3 operations which can be applied to either string, namely: insertion, deletion and replacement.
 */
public class LevenshteinDistanceAKAEditDist {

    private static int editDistance(String source, String target) {
        int m = source.length();    // source is the column in the matrix
        int n = target.length();    // target is the row

        // for all i, j, dp[i][j] will hold the edit distance between the first
        // i characters of source string and first j characters of target string
        int[][] distanceMemo = new int[m + 1][n + 1];

        // Source can be transformed into target prefix by inserting all of the characters in the prefix.
        for (int i = 1; i <= n; i++) {
            distanceMemo[0][i] = i;
        }

        // 0th column in the target(which is the row) is empty character.
        // Source prefixes can be transformed into empty string by deleting all of the characters.
        for (int i = 1; i <= m; i++) {
            distanceMemo[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (source.charAt(i-1) == target.charAt(j - 1)) {
                    distanceMemo[i][j] = distanceMemo[i - 1][j - 1];    // no operation required as chars are same
                } else {
                    distanceMemo[i][j] = 1 + Math.min(distanceMemo[i - 1][j - 1],           // replacement
                            Math.min(distanceMemo[i][j - 1],                      // insertion
                                    distanceMemo[i - 1][j]));                     // deletion
                }
            }
        }
        return distanceMemo[m][n];
    }

    public static void main(String[] args) {
        String s1 = "sitting";
        String s2 = "kitten";
        int editDist = editDistance(s1, s2);
        System.out.printf("Edit distance between %s and %s is %d%n", s1, s2, editDist);
    }
}
