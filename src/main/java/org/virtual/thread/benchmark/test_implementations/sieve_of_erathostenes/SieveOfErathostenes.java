package org.virtual.thread.benchmark.test_implementations.sieve_of_erathostenes;

import java.util.BitSet;

/**
 * This program runs the Sieve of Erathostenes benchmark. It compiles all primes
 * up to n
 * @version 1.22 2021-06-17
 * @author Cay Hortsmann
 */
public class SieveOfErathostenes {

    private SieveOfErathostenes() {}

    /**
     * This program runs the Sieve of Erathostenes benchmark. It compiles all primes
     * up to n
     * @author Cay Hortsmann
     * @param n find prime numbers up to n
     */
    public static void findPrimeNumbers(int n) {
        var bitSet = new BitSet(n + 1);
        int i;
        for (i = 2; i <= n; i++) bitSet.set(i);
        i = 2;
        while (i * i <= n) {
            if (bitSet.get(i)) {
                int k = i * i;
                while (k <= n) {
                    bitSet.clear(k);
                    k += i;
                }
            }
            i++;
        }
    }
}
