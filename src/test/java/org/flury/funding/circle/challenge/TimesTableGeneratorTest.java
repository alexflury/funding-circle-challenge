package org.flury.funding.circle.challenge;

import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;

public class TimesTableGeneratorTest {

    /**
     * {@link TimesTableGenerator#withSize} should correctly generate small times tables.
     */
    @Test
    public void testSmallTable() {
        // size = 1
        TimesTableGenerator generator = TimesTableGenerator.withSize(1);
        assertEquals(Arrays.asList(new Long(2)), generator.getHeader());
        assertEquals(Arrays.asList(new Long(4)), generator.getRow(0));

        // size = 2
        generator = TimesTableGenerator.withSize(2);
        assertEquals(
                Arrays.asList(new Long(2), new Long(3)),
                generator.getHeader());
        assertEquals(
                Arrays.asList(new Long(4), new Long(6)),
                generator.getRow(0));
        assertEquals(
                Arrays.asList(new Long(6), new Long(9)),
                generator.getRow(1));

        // size = 3
        generator = TimesTableGenerator.withSize(3);
        assertEquals(
                Arrays.asList(new Long(2), new Long(3), new Long(5)),
                generator.getHeader());
        assertEquals(
                Arrays.asList(new Long(4), new Long(6), new Long(10)),
                generator.getRow(0));
        assertEquals(
                Arrays.asList(new Long(6), new Long(9), new Long(15)),
                generator.getRow(1));
        assertEquals(
                Arrays.asList(new Long(10), new Long(15), new Long(25)),
                generator.getRow(2));
    }

    /**
     * {@link TimesTableGenerator#withSize} should correctly generate large times tables.
     */
    @Test
    public void testLargeTable() {
        TimesTableGenerator generator = TimesTableGenerator.withSize(1000);

        // The 5th prime = 11
        assertEquals(11, generator.getHeader().get(4).longValue());
        // The 7th prime = 17
        assertEquals(17, generator.getHeader().get(6).longValue());
        // 11 * 17 = 187
        assertEquals(187, generator.getRow(4).get(6).longValue());
        assertEquals(187, generator.getRow(6).get(4).longValue());

        // The 55th prime = 257
        assertEquals(257, generator.getHeader().get(54).longValue());
        // The 77th prime = 389
        assertEquals(389, generator.getHeader().get(76).longValue());
        // 257 * 389 = 99973
        assertEquals(99973, generator.getRow(54).get(76).longValue());
        assertEquals(99973, generator.getRow(76).get(54).longValue());

        // The 555th prime = 4019
        assertEquals(4019, generator.getHeader().get(554).longValue());
        // The 777th prime = 5903
        assertEquals(5903, generator.getHeader().get(776).longValue());
        // 4019 * 5903 = 23724157
        assertEquals(23724157, generator.getRow(554).get(776).longValue());
        assertEquals(23724157, generator.getRow(776).get(554).longValue());
    }

    /**
     * {@link TimesTableGenerator#withSize} should throw an exception if the table size is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        TimesTableGenerator.withSize(0);
    }

    /**
     * {@link TimesTableGenerator#withSize} should throw an exception if the table size is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSize() {
        TimesTableGenerator.withSize(-1);
    }

    /**
     * {@link TimesTableGenerator#getRow} should throw an exception if the row number is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRowOutOfBounds() {
        TimesTableGenerator.withSize(3).getRow(3);
    }

}
