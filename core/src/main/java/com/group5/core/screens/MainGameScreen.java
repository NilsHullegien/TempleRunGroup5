package com.group5.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main game screen.
 */
public class MainGameScreen implements Screen {
    /**
     * A texture.
     */
    private Texture texture;

    /**
     * A spritebatch
     */
    private SpriteBatch batch;

    /**
     * The amount of time that has elapsed.
     */
    private float elapsed;

    public MainGameScreen(SpriteBatch batch) {
        this.batch = batch;
        this.texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
        this.elapsed = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float delta) {
        elapsed += delta;
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture,
                100 + 100 * (float) Math.cos(elapsed),
                100 + 25 * (float) Math.sin(elapsed));
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(final int width, final int height) {

    }

    @Override
    public void dispose() {

    }

}
