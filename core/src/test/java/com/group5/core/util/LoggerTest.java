package com.group5.core.util;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the logging system.
 */
public class LoggerTest {
    private BufferedReader inputReader;
    private Logger logger;

    /**
     * Initializes the test logger and input reader.
     *
     * @throws IOException if something goes wrong while instantiating reader
     */
    @Before
    public void setUp() throws IOException {
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();
        outputStream.connect(inputStream);
        inputReader = new BufferedReader(new InputStreamReader(inputStream));
        logger = new Logger(outputStream);
    }

    /**
     * Tests whether the static set() and get() methods set and get the right instance.
     */
    @Test
    public void testStaticSetterSetsInstance() {
        Logger.set(logger);
        assertSame(Logger.get(), logger);
    }

    /**
     * Tests whether a call to the log method contains all required data.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testLogCallContainsLevelTagAndMessage() throws IOException {
        logger.log(Logger.Level.DEBUG, "LoggerTest", "This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.DEBUG.toString()));
        assertTrue(msg.contains("LoggerTest"));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the debug method contains all required data and the debug level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testDebugCallContainsDebugLevelTagAndMessage() throws IOException {
        logger.debug("LoggerTest", "This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.DEBUG.toString()));
        assertTrue(msg.contains("LoggerTest"));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the debug method without a tag contains all required data and the debug level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testUntaggedDebugCallContainsDebugLevelAndMessage() throws IOException {
        logger.debug("This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.DEBUG.toString()));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the info method contains all required data and the info level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testInfoCallContainsInfoLevelTagAndMessage() throws IOException {
        logger.info("LoggerTest", "This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.INFO.toString()));
        assertTrue(msg.contains("LoggerTest"));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the info method without a tag contains all required data and the info level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testUntaggedInfoCallContainsDebugLevelAndMessage() throws IOException {
        logger.info("This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.INFO.toString()));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the warning method contains all required data and the warning level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testDebugCallContainsWarningLevelTagAndMessage() throws IOException {
        logger.warning("LoggerTest", "This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.WARNING.toString()));
        assertTrue(msg.contains("LoggerTest"));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the warning method without a tag contains all required data and the warning level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testUntaggedWarningCallContainsDebugLevelAndMessage() throws IOException {
        logger.warning("This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.WARNING.toString()));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the error method contains all required data and the error level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testDebugCallContainsErrorLevelTagAndMessage() throws IOException {
        logger.error("LoggerTest", "This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.ERROR.toString()));
        assertTrue(msg.contains("LoggerTest"));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the error method without a tag contains all required data and the error level.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testUntaggedErrorCallContainsDebugLevelAndMessage() throws IOException {
        logger.error("This is a test message");
        String msg = inputReader.readLine();

        assertTrue(msg.contains(Logger.Level.ERROR.toString()));
        assertTrue(msg.contains("This is a test message"));
    }

    /**
     * Tests whether a call to the log method with the none level is not printed.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testLogWithNoneLevelIsNotPrinted() throws IOException {
        logger.log(Logger.Level.NONE, "LoggerTest", "This is a test message");

        // Check that the input reader is empty
        assertTrue(!inputReader.ready());
    }

    /**
     * Tests whether a call to the log method with a level below the set threshold level is not printed.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testLogWithLevelBelowThresholdIsNotPrinted() throws IOException {
        logger.setThreshold(Logger.Level.INFO);
        logger.debug("This is a test");

        assertTrue(!inputReader.ready());
    }

    /**
     * Tests whether a call to the dispose method disables the input reader.
     *
     * @throws IOException if something goes wrong reading from the logger output
     */
    @Test
    public void testDisposalClosesUnderlyingStream() throws IOException {
        logger.dispose();
        logger.info("This is a test");
        assertTrue(!inputReader.ready());
    }

}
