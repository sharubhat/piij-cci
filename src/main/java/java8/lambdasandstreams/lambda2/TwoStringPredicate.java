package java8.lambdasandstreams.lambda2;

/**
 * Created by sharath on 1/14/17.
 */
@FunctionalInterface
public interface TwoStringPredicate {
    boolean apply(String s1, String s2);
}
