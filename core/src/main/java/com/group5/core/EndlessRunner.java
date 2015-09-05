package com.group5.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.screens.MainGameScreen;

/**
 * Main game class for the endless runner.
 */
public final class EndlessRunner extends Game {

    /**
     * The SpriteBatch to draw stuff with.
     */
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
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
    }
}
