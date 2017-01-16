package java8.lambdasandstreams.lambda2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by sharath on 1/14/17.
 */
public class EntryUtil<T> {
    public static <T> T betterEntry(T t1, T t2, TwoEntryPredicate<T> ep) {
        return ep.apply(t1, t2) ? t1 : t2;
    }

    public static <T> List<T> allMatches(List<T> entries, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T t : entries) {
            if(p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static<T, R> List<R> transformedList(List<T> entries, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T t : entries) {
            result.add(f.apply(t));
        }
        return result;
    }
}
