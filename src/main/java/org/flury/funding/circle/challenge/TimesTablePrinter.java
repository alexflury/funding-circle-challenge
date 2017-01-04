package org.flury.funding.circle.challenge;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * This class formats and prints a times table with a given size to a given output stream.
 *
 * @author Alex Flury
 */
public class TimesTablePrinter {

    /**
     * The number of spaces characters to print between two adjacent table cells.
     */
    private static final Integer CELL_PADDING = 1;

    /**
     * The number of rows and columns in the table.
     */
    final int size;

    /**
     * The number of characters in each cell, including padding characters.
     */
    final int cellWidth;

    /**
     * The format string to use to print the data in one cell.
     */
    final String cellFormat;

    /**
     * The source of the table data.
     */
    final TimesTableGenerator generator;

    /**
     * Not a public constructor.  Use {@link #withSize} to get an instance.
     *
     * @param size the number of rows and columns in the table
     * @throws IllegalArgumentException if {@code size <= 0}
     */
    private TimesTablePrinter(final int size) {
        this.size = size;
        this.generator = TimesTableGenerator.withSize(size);

        // Calculate the width of each cell based on the largest number in the table.
        final Long largestPrime = generator.getHeader().get(generator.getHeader().size() - 1);
        final Long largestCell = largestPrime * largestPrime;
        this.cellWidth = (int) Math.log10(largestCell) + 1 + CELL_PADDING;

        // Generate a format string.
        // Example:
        //   cellWidth = 5
        //   cellFormat = "%5d"
        this.cellFormat = String.format("%%%dd", cellWidth);
    }

    /**
     * Returns an object which prints a times table of a given size.
     *
     * @param size the number of rows and columns in the table
     * @return an object which prints a times table with {@code size} rows and {@code size} columns
     * @throws IllegalArgumentException if {@code size <= 0}
     */
    public static TimesTablePrinter withSize(final int size) {
        return new TimesTablePrinter(size);
    }

    /**
     * Prints the times table to a given output stream.
     *
     * @param outputStream the output stream to print to
     * @throws NullPointerException if {@code outputStream} is {@code null}
     */
    public void print(final OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("Output stream cannot be null.");
        }
        final PrintWriter writer = new PrintWriter(outputStream);

        // Table header.
        printHeader(writer);
        writer.flush();

        // Table rows.
        for (int r = 0; r < size; r++) {
            printRow(writer, r);
            writer.flush();
        }
    }

    /**
     * Prints the header row of the times table.
     *
     * @param writer a {@link PrintWriter} for writing formatted output
     */
    private void printHeader(final PrintWriter writer) {
        // Print a blank cell in the upper left corner of the table.
        for (int c = 0; c < cellWidth; c++) {
            writer.print(' ');
        }

        // Get the header data from the table generator.
        for (Long prime : generator.getHeader()) {
            printCell(writer, prime);
        }
        writer.println();
    }

    /**
     * Prints one row of table content.
     *
     * @param writer a {@link PrintWriter} for writing formatted output
     * @param row the row number
     */
    private void printRow(final PrintWriter writer, final int row) {
        // Print the header row in the leftmost column of the table.
        printCell(writer, generator.getHeader().get(row));

        // Get the row content from the table generator.
        for (Long content : generator.getRow(row)) {
            printCell(writer, content);
        }
        writer.println();
    }

    /**
     * Prints one cell of table content.
     *
     * @param writer a {@link PrintWriter} for writing formatted output
     * @param content the data to print in the cell
     */
    private void printCell(final PrintWriter writer, final Long content) {
        writer.printf(cellFormat, content);
    }

}
