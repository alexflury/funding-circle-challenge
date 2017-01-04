package org.flury.funding.circle.challenge;

/**
 * This app runs from the command line and prints a times table to standard output which displays the product of any
 * two of the first {@code n} prime numbers.
 *
 * @author Alex Flury
 */
public class TimesTableApp {

    /**
     * The entry-point method for the app.
     *
     * @param args command line arguments
     * @throws IllegalArgumentException if the command line arguments are invalid
     */
    public static void main(final String[] args) {
        // Parse the table size from the command line arguments.
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing argument: table size");
        }
        int tableSize;
        try {
            tableSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid table size: %s%s", args[0]), e);
        }

        // Print the times table.
        final TimesTablePrinter printer = TimesTablePrinter.withSize(tableSize);
        printer.print(System.out);
    }

}
