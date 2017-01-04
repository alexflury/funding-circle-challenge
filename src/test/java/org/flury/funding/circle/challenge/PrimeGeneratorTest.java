package org.flury.funding.circle.challenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PrimeGeneratorTest {

    /**
     * {@link PrimeGenerator#listPrimes} should return the list of the first {@code n} primes for small values of
     * {@code n}.
     */
    @Test
    public void testListPrimesSmall() {
        assertEquals(
                Arrays.asList(new Long(2)),
                PrimeGenerator.listPrimes(1));
        assertEquals(
                Arrays.asList(
                        new Long(2),
                        new Long(3)),
                PrimeGenerator.listPrimes(2));
        assertEquals(
                Arrays.asList(
                        new Long(2),
                        new Long(3),
                        new Long(5)),
                PrimeGenerator.listPrimes(3));
        assertEquals(
                Arrays.asList(
                        new Long(2),
                        new Long(3),
                        new Long(5),
                        new Long(7)),
                PrimeGenerator.listPrimes(4));
        assertEquals(
                Arrays.asList(
                        new Long(2),
                        new Long(3),
                        new Long(5),
                        new Long(7),
                        new Long(11)),
                PrimeGenerator.listPrimes(5));
    }

    /**
     * {@link PrimeGenerator#listPrimes} should correctly calculate large prime numbers.
     */
    @Test
    public void testListPrimesLarge() {
        // The 10th prime = 29
        assertEquals(29L, PrimeGenerator.listPrimes(10).get(9).longValue());
        // The 100th prime = 541
        assertEquals(541L, PrimeGenerator.listPrimes(100).get(99).longValue());
        // The 1000th prime = 7919
        assertEquals(7919L, PrimeGenerator.listPrimes(1000).get(999).longValue());
    }

    /**
     * {@link PrimeGenerator#listPrimes} should throw an exception if the size of the list is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testListPrimesEmpty() {
        PrimeGenerator.listPrimes(0);
    }

    /**
     * {@link PrimeGenerator#listPrimes} should throw an exception if the size of the list is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testListPrimesNegativeSize() {
        PrimeGenerator.listPrimes(-1);
    }

}
