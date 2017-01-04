package org.flury.funding.circle.challenge;

import org.junit.Test;

import java.io.*;
import static org.junit.Assert.*;

public class TimesTablePrinterTest {

    /**
     * {@link TimesTablePrinter#print} should print the correct tables for small table sizes.
     */
    @Test
    public void testPrint() throws Exception {
        testPrintWithSize(1, "org/flury/funding/circle/challenge/expected.table.1.txt");
        testPrintWithSize(2, "org/flury/funding/circle/challenge/expected.table.2.txt");
        testPrintWithSize(3, "org/flury/funding/circle/challenge/expected.table.3.txt");
        testPrintWithSize(4, "org/flury/funding/circle/challenge/expected.table.4.txt");
        testPrintWithSize(5, "org/flury/funding/circle/challenge/expected.table.5.txt");
    }

    private void testPrintWithSize(final int size, final String resourceName) throws Exception {
        TimesTablePrinter printer = TimesTablePrinter.withSize(size);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        printer.print(outputStream);
        byte[] actualTable = outputStream.toByteArray();
        BufferedReader actualReader = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(actualTable)));
        BufferedReader expectedReader = new BufferedReader(new FileReader(new File(
                this.getClass().getClassLoader().getResource(resourceName).toURI())));
        assertReaderEquals(expectedReader, actualReader);
    }

    private void assertReaderEquals(
            final BufferedReader expectedReader, final BufferedReader actualReader)
            throws Exception {
        String expectedLine;
        while ((expectedLine = expectedReader.readLine()) != null) {
            String actualLine = actualReader.readLine();
            assertEquals(expectedLine, actualLine);
        }
        assertNull(actualReader.readLine());
    }

}
