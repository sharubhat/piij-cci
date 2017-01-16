package java8.lambdasandstreams;

import java.util.Arrays;

/**
 * Created by sharath on 1/14/17.
 */
public class Lambda1 {
    /*
        Basic lambdas. Make an array containing a few Strings. Sort it by • length (i.e., shortest to longest)
        (Hint: this exact solution was shown in the lecture)
        • reverse length (i.e., longest to shortest) (Hint: minor variation of the first bullet)
        • alphabetically by the first character only
        (Hint: charAt(0) returns the numeric code for the first character)
        • Strings that contain “e” first, everything else second. For now, put the code directly in the lambda. (Hint: remember that the body of a lambda is allowed to have curly braces and a return state-
        ment. See the first two lambda examples in the notes.)
        • Redo the previous problem, but use a static helper method so that your lambda looks like this:
         Arrays.sort(words, (s1,s2) -> Utils.yourMethod(s1,s2))
     */
    public static void main(String[] args) {
        String[] names = {"Bob", "Mario", "Carmine", "Falcon", "Bruce"};

        // sort by length
        Arrays.sort(names, (s1, s2) -> {
                    if (s1.length() - s2.length() < 0)
                        return -1;
                    if (s1.length() - s2.length() > 0)
                        return 1;
                    return 0;
                }
        );
        System.out.println(Arrays.asList(names));

        // sort by reverse length
        Arrays.sort(names, (s1, s2) -> {
                    if (s1.length() - s2.length() > 0)
                        return -1;
                    if (s1.length() - s2.length() < 0)
                        return 1;
                    return 0;
                }
        );

        System.out.println(Arrays.asList(names));

        // alphabetically by first character only
        Arrays.sort(names, (s1, s2) -> {
                    if (s1.charAt(0) - s2.charAt(0) < 0)
                        return -1;
                    if (s1.charAt(0) - s2.charAt(0) > 0)
                        return 1;
                    return 0;
                }
        );

        System.out.println(Arrays.asList(names));

        // strings containing 'e' first and rest next
        Arrays.sort(names, (s1, s2) -> {
                    if (s1.contains("e")) {
                        if(s2.contains("e"))
                            return 0;
                        else
                            return -1;
                    }
                    return 1;
                }
        );

        System.out.println(Arrays.asList(names));

        System.out.println();

        names = new String[]{"Bob", "Mario", "Carmine", "Falcon", "Bruce"};
        // sort by length
        Arrays.sort(names, (s1, s2) -> Util.compareByLength(s1, s2));
        System.out.println(Arrays.asList(names));

        // sort by reverse length
        Arrays.sort(names, (s1, s2) -> Util.compareByReverseLength(s1, s2));
        System.out.println(Arrays.asList(names));

        // alphabetically by first character only
        Arrays.sort(names, (s1, s2) -> Util.compareByFirstChar(s1, s2));
        System.out.println(Arrays.asList(names));

        // strings containing 'e' first and rest next
        Arrays.sort(names, (s1, s2) -> Util.compareContainsE(s1, s2));
        System.out.println(Arrays.asList(names));
    }

    private static class Util {
        public static int compareByLength(String s1, String s2) {
            if (s1.length() - s2.length() < 0)
                return -1;
            if (s1.length() - s2.length() > 0)
                return 1;
            return 0;
        }

        public static int compareByReverseLength(String s1, String s2) {
            if (s1.length() - s2.length() > 0)
                return -1;
            if (s1.length() - s2.length() < 0)
                return 1;
            return 0;
        }

        public static int compareByFirstChar(String s1, String s2) {
            if (s1.charAt(0) - s2.charAt(0) < 0)
                return -1;
            if (s1.charAt(0) - s2.charAt(0) > 0)
                return 1;
            return 0;
        }

        public static int compareContainsE(String s1, String s2) {
            if (s1.contains("e")) {
                if(s2.contains("e"))
                    return 0;
                else
                    return -1;
            }
            return 1;
        }
    }
}
