package com.group5.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.screens.MainMenu;
import com.group5.core.util.Logger;
import com.group5.core.util.TextureCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Startup class for the Game, from here all textures are loaded
 * and the mainmenu is started.
 */
public class EndlessRunner extends Game {

    /**
     * The current game instance.
     */
    private static EndlessRunner game;
    /**
     * The SpriteBatch to draw stuff with.
     */
    private SpriteBatch batch;
    /**
     * The current texture cache.
     */
    private TextureCache textureCache;

    /**
     * Sets the current game instance.
     *
     * @param g the new current game instance
     */
    static void setGame(final EndlessRunner g) {
        EndlessRunner.game = g;
    }

    /**
     * Returns the current game instance.
     *
     * @return the current game instance
     */
    public static EndlessRunner get() {
        return game;
    }

    /**
     * Returns the current texture cache.
     *
     * @return the current texture cache.
     */
    public TextureCache getTextureCache() {
        return textureCache;
    }

    /**
     * Loads a configuration file and sets relevant configuration in the game.
     */
    public void loadConfiguration() {
        Properties config = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("./game.properties");

            config.load(input);

            String logFile = config.getProperty("log_file");
            if (logFile != null) {
                try {
                    FileOutputStream f = new FileOutputStream(logFile, true);
                    Logger.set(new Logger(f));
                } catch (Exception ex) {
                    Logger.get().error("Settings", "Invalid settings file: " + logFile);
                }
            }

            String threshold = config.getProperty("log_threshold", "DEBUG");
            try {
                Logger.Level t = Enum.valueOf(Logger.Level.class, threshold);
                Logger.get().info("Logging", "Log level is now " + threshold);
                Logger.get().setThreshold(t);
            } catch (IllegalArgumentException ex) {
                Logger.get().error("Invalid logging level set as threshold: " + threshold);
            }
        } catch (FileNotFoundException ex) {
            Logger.get().warning("Game", "Config file could not be found");
        } catch (IOException ex) {
            Logger.get().error("Game", "Something went wrong while reading config");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.get().error("Game", "Something went wrong while closing config file");
                }
            }
        }

    }

    @Override
    public void create() {
        loadConfiguration();
        Logger.get().info("Game", "Creating");
        EndlessRunner.setGame(this);
        batch = new SpriteBatch();
        textureCache = new TextureCache();
        setScreen(new MainMenu(batch));
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        try {
            Logger.get().dispose();
        } catch (IOException ex) {
            System.err.println("ERROR: Failed to dispose of logger");
        }
        batch.dispose();
        textureCache.dispose();
    }
}
