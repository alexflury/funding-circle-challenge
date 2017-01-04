package org.flury.funding.circle.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates a list of the first {@code n} prime numbers.
 *
 * @author Alex Flury
 */
public class PrimeGenerator {

    /**
     * Prevents instantiation.
     */
    private PrimeGenerator() {

    }

    /**
     * Returns a list of the first {@code n} prime numbers.
     *
     * @param size the size of the list
     * @return a list of the first {@code size} prime numbers
     * @throws IllegalArgumentException if {@code size <= 0}
     */
    public static List<Long> listPrimes(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                    String.format("List size must be a positive integer: %d", size));
        }

        // Initialize the list with the prime number 2.
        final List<Long> primes = new ArrayList<>();
        primes.add(2L);

        // As an optimization, return the list if size == 1.
        if (size == 1) {
            return Collections.unmodifiableList(primes);
        }

        // If size > 1, add the number 3 as the second prime number.
        primes.add(3L);

        long prime = 3L;
        for (int p = 2; p < size; p++) {
            // Search for the next prime number to add to the list.  As an optimization, skip even numbers.
            do {
                prime += 2;
            } while (!isPrime(prime, primes));
            primes.add(prime);
        }
        return Collections.unmodifiableList(primes);
    }

    /**
     * Returns {@code true} if a given number greater than {@code 2} is prime.
     *
     * @param n the number to test, greater than 2
     * @param primes a list of all primes less than {@code n}
     * @return {@code true} if {@code n} is prime; {@code false} otherwise
     */
    private static boolean isPrime(final long n, final List<Long> primes) {
        long sqrt = (long) Math.sqrt(n);
        for (int p = 0; p < primes.size() && primes.get(p) <= sqrt; p++) {
            if (n % primes.get(p) == 0) {
                return false;
            }
        }
        return true;
    }

}
