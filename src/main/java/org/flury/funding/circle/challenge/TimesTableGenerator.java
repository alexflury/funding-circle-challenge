package org.flury.funding.circle.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates a table which contains the product of any of two of the first {@code n} prime numbers.
 *
 * @author Alex Flury
 */
public class TimesTableGenerator {

    /**
     * The list of prime numbers in the table header.
     */
    final List<Long> primes;

    /**
     * Not a public constructor.  Use {@link #withSize} to get an instance.
     *
     * @param size the number of rows and columns in the table
     */
    private TimesTableGenerator(final int size) {
        // Generate the list of prime numbers in the table header.
        this.primes = PrimeGenerator.listPrimes(size);
    }

    /**
     * Returns an object which generates a table which contains the product of any two of the first {@code n} prime
     * numbers.
     *
     * @param size the number of rows and columns in the table
     * @return an object which generates a table which contains the product of any two of the first {@code size}
     * prime numbers
     * @throws IllegalArgumentException if {@code size <= 0}
     */
    public static TimesTableGenerator withSize(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                    String.format("Table size must be a positive integer: %d", size));
        }
        return new TimesTableGenerator(size);
    }

    /**
     * Returns the list of prime numbers in the table header.
     *
     * @return the list of prime numbers in the table header
     */
    public List<Long> getHeader() {
        return primes;
    }

    /**
     * Returns one row of table content.
     *
     * @param row the row number, between {@code 0} and {@code size - 1}, inclusive
     * @return the content of the specified row
     * @throws IndexOutOfBoundsException if {@code row} is outside of the allowable range
     */
    public List<Long> getRow(final int row) {
        // We will multiply all of the primes by this fixed multiplier.
        final Long multiplier = primes.get(row);

        // Generate the row content.
        final List<Long> content = new ArrayList<>();
        for (Long prime : primes) {
            content.add(prime * multiplier);
        }
        return Collections.unmodifiableList(content);
    }

}
