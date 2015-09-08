package com.group5.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.screens.MainGameScreen;
import com.group5.core.util.TextureCache;

/**
 * Main game class for the endless runner.
 */
public class EndlessRunner extends Game {

    /**
     * The current game instance.
     */
    private static EndlessRunner game;

    /**
     * Sets the current game instance.
     * @param g the new current game instance
     */
    static void setGame(final EndlessRunner g) {
        EndlessRunner.game = g;
    }

    /**
     * Returns the current game instance.
     * @return the current game instance
     */
    public static EndlessRunner get() {
        return game;
    }

    /**
     * The SpriteBatch to draw stuff with.
     */
    private SpriteBatch batch;

    /**
     * The current texture cache.
     */
    private TextureCache textureCache;

    /**
     * Returns the current texture cache.
     * @return the current texture cache.
     */
    public TextureCache getTextureCache() {
        return textureCache;
    }

    @Override
    public void create() {
        EndlessRunner.setGame(this);
        batch = new SpriteBatch();
        textureCache = new TextureCache();
        setScreen(new MainGameScreen(batch));
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
        batch.dispose();
        textureCache.dispose();
    }
}
