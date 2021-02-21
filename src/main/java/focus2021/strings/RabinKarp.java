package focus2021.strings;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    /**
     * Brute force
     */
    private int search(String pattern, String text) {
        int pLen = pattern.length();
        int tLen = text.length();

        if (pLen > tLen) {
            return -1;
        }

        for (int i = 0; i < tLen; i++) {
            int j = 0;
            for (; j < pLen; j++) {
                if (text.charAt(i) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == pLen) {
                return i;
            }
        }
        return -1;
    }

    private long patternHash;
    private int M;
    private long Q;
    private int R = 256; // size of alphabet
    private long RM;    // R^(M-1) % Q

    public RabinKarp(String pattern) {
        this.M = pattern.length();
        this.Q = longRandomPrime();
        this.RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patternHash = hash(pattern, M);
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Horner's method for modular hashing
     * h -> hash value
     * R -> base
     * M -> length of string (think of M digit number of base R)
     * Q -> Size of hashtable
     * The use of a small prime integer such as 31 for R ensures that the bits of all the characters play a role.
     * Since we don't actually build hashtable in rolling hashing, we use a long value larger than 10^20 (value of Q)
     * making probability that random key hashes to same value as our pattern less than 10^-20.
     * This is called Monte Carlo correctness.
     *
     * Actually comparing the substring for correctness once hashvalue matches requires backup of the string and also
     * reduces time complexity to MN which is same as brute force. This algorithm is called Las Vegas algorithm.
     *
     * for (int i = 0; i < M; i++) {
     *     h = (R * h + key.charAt(i)) % Q;
     * }
     */
    private long hash(String key, int M) {
        long h = 0L;
        for (int i = 0; i < M; i++) {
            h = (R * h + key.charAt(i)) % Q;
        }
        return h;
    }

    private int search(String text) {
        int N = text.length();
        // hash value for first M characters in text where M is length of the pattern
        long textHash = hash(text, M);

        if (patternHash == textHash) {
            // text begins with the pattern
            return 0;
        }

        for (int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match.
            textHash = (textHash + Q - RM * text.charAt(i - M) % Q) % Q;
            textHash = (textHash * R + text.charAt(i)) % Q;
            if (patternHash == textHash) {
                return i - M + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new RabinKarp("acad").search("abacadabrabracabracadabrabrabracad"));
    }

}
