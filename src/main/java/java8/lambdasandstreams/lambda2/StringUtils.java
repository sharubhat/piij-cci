package java8.lambdasandstreams.lambda2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by sharath on 1/14/17.
 */
public class StringUtils {
    public static String betterString(String s1, String s2, TwoStringPredicate p) {
        return p.apply(s1, s2) ? s1 : s2;
    }

    public static List<String> allMatches(List<String> strings, Predicate<String> p) {
        List<String> result = new ArrayList<>();
          for (String s : strings) {
              if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public static List<String> transformedList(List<String> strings, Function<String, String> f) {
        List<String> result = new ArrayList<>();
          for (String s : strings) {
            result.add(f.apply(s));
        }
        return result;
    }
}
