import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class ConfigurationTest {

    @Test
    public void testGoodInput() throws IOException {
        String data = "interval = 10\nduration = 100\ndeparture = 200\n";

        Properties input = loadInput(data);

        Configuration props = new Configuration();
        try {
            props.load(input);
        } catch (ConfigurationException e) {
            assertTrue(false);
            return;
        }

        assertEquals(props.getInterval(), 10);
        assertEquals(props.getDuration(), 100);
        assertEquals(props.getDeparture(), 200);
    }

    @Test
    public void testNegativeValues() throws IOException {
        processBadInput("interval = -10\nduration = 100\ndeparture = 200\n");
        processBadInput("interval = 10\nduration = -100\ndeparture = 200\n");
        processBadInput("interval = 10\nduration = 100\ndeparture = -200\n");
    }

    @Test
    public void testInvalidDuration() throws IOException {
        processBadInput("interval = 10\nduration = 99\ndeparture = 200\n");
    }

    @Test
    public void testInvalidDeparture() throws IOException {
        processBadInput("interval = 10\nduration = 100\ndeparture = 199\n");
    }

    @Test
    public void processBadInput(String data) throws IOException {
        Properties input = loadInput(data);

        boolean failed = true;
        Configuration props = new Configuration();
        try {
            props.load(input);
        } catch (ConfigurationException e) {
            failed = false;
        }

        assertTrue(failed);
    }

    @Test
    private Properties loadInput(String data) throws IOException {
        InputStream is = new StringBufferInputStream(data);

        Properties input = new Properties();
        input.load(is);
        is.close();

        return input;
    }
}
