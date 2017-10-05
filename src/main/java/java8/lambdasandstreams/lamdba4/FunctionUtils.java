package java8.lambdasandstreams.lamdba4;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by sharath on 1/14/17.
 */
public class FunctionUtils {
    public static <T> Predicate<T> allPassPredicate(Predicate<T>... tests) {
        Predicate<T> result = e -> true;
          for (Predicate<T> test : tests) {
            result = result.and(test);
        }
        return result;
    }

    public static <T> T firstAllMatch(Stream<T> elements, Predicate<T>... tests) {
        Predicate<T> combinedTests = allPassPredicate(tests);
        return elements.filter(combinedTests).findFirst().orElse(null);
    }

    public static<T> Predicate<T> anyPassPredicate(Predicate<T>... tests) {
        Predicate<T> result = e -> false;
          for (Predicate<T> test : tests) {
            result = result.or(test);
        }
        return result;
    }

    public static <T> T firstAnyMatch(Stream<T> elements, Predicate<T>... tests) {
        Predicate<T> combinedTests = anyPassPredicate(tests);
        return elements.filter(combinedTests).findFirst().orElse(null);
    }
}
