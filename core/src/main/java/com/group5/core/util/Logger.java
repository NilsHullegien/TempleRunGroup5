package com.group5.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logging system for the game.
 */
public class Logger {

    /**
     * Logging level at which a message is valued.
     */
    public enum Level {
        /**
         * The logged message concerns debugging.
         */
        DEBUG,

        /**
         * The logged message is information.
         */
        INFO,

        /**
         * The logged message is a warning.
         */
        WARNING,

        /**
         * The logged message is an error.
         */
        ERROR,

        /**
         * The logged message should not be logged.
         */
        NONE
    }

    /**
     * Current logger instance.
     */
    private static Logger instance;

    static {
        // Instantiate the logger with a system out one by default.
        instance = new Logger();
    }

    /**
     * Returns the current logger instance.
     * @return the current logger instance
     */
    public static Logger get() {
        return Logger.instance;
    }

    /**
     * Sets the current logger instance.
     * @param logger the new logger instance
     */
    public static void set(final Logger logger) {
        Logger.instance = logger;
    }

    /**
     * The output stream to write to.
     */
    private OutputStream outStream;

    /**
     * The writer to write to outStream with.
     */
    private PrintWriter outStreamWriter;

    /**
     * The minimum logging level to output for.
     */
    private Level threshold = Level.DEBUG;

    /**
     * The date formatter used for printing log timestamps.
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");

    /**
     * Creates a new logger that prints to system out.
     */
    public Logger() {
        this(System.out);
    }

    /**
     * Creates a new logger that prints to the passed in output stream.
     * @param out the output stream to log to
     */
    public Logger(final OutputStream out) {
        this.outStream = out;
        this.outStreamWriter = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
    }

    /**
     * Sets the minimum logging level threshold.
     * @param level the minimum logging level threshold
     */
    public void setThreshold(final Level level) {
        threshold = level;
    }

    /**
     * Logs the passed in message with the passed in level and tag.
     * @param level the level at which the message is valued
     * @param tag the tag the message is marked with
     * @param message the message to be logged
     */
    public void log(final Level level, final String tag, final String message) {
        // Check if the message level is at or above the minimum level
        if (level.compareTo(threshold) >= 0 && level != Level.NONE) {
            String tagString = "";
            if (tag != null) {
                tagString = "[" + String.format("%12s", tag) + "] ";
            }

            outStreamWriter.printf("[%7s] %s - %s%s%n", level.toString(), dateFormat.format(new Date()), tagString, message);
            outStreamWriter.flush();
        }
    }

    /**
     * Logs the passed in message at the debug level.
     * @param message the message to be logged
     */
    public void debug(final String message) {
        debug(null, message);
    }

    /**
     * Logs the passed in message marked with the passed in tag at the debug level.
     * @param tag the tag the message is marked with
     * @param message the message to be logged
     */
    public void debug(final String tag, final String message) {
        log(Level.DEBUG, tag, message);
    }

    /**
     * Logs the passed in message at the info level.
     * @param message the message to be logged
     */
    public void info(final String message) {
        info(null, message);
    }

    /**
     * Logs the passed in message marked with the passed in tag at the info level.
     * @param tag the tag the message is marked with
     * @param message the message to be logged
     */
    public void info(final String tag, final String message) {
        log(Level.INFO, tag, message);
    }

    /**
     * Logs the passed in message at the warning level.
     * @param message the message to be logged
     */
    public void warning(final String message) {
        warning(null, message);
    }

    /**
     * Logs the passed in message marked with the passed in tag at the warning level.
     * @param tag the tag the message is marked with
     * @param message the message to be logged
     */
    public void warning(final String tag, final String message) {
        log(Level.WARNING, tag, message);
    }

    /**
     * Logs the passed in message at the error level.
     * @param message the message to be logged
     */
    public void error(final String message) {
        error(null, message);
    }

    /**
     * Logs the passed in message marked with the passed in tag at the error level.
     * @param tag the tag the message is marked with
     * @param message the message to be logged
     */
    public void error(final String tag, final String message) {
        log(Level.ERROR, tag, message);
    }

    /**
     * Disposes of the logger's held resources.
     * @throws IOException if something goes wrong while closing the stream
     */
    public void dispose() throws IOException {
        outStreamWriter.close();
        outStream.close();
    }
}
