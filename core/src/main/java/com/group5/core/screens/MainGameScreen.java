package com.group5.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

/**
 * Main game screen.
 */
public class MainGameScreen implements Screen {

    /**
     * A spritebatch to draw things with.
     */
    private SpriteBatch batch;

    /**
     * The world that contains all game objects.
     */
    private World world;

    /**
     * The camera the world is viewed with.
     */
    private OrthographicCamera camera;

    /**
     * Constructs a new main game screen that plays the actual game.
     * @param b the SpriteBatch to draw textures with
     */
    public MainGameScreen(final SpriteBatch b) {
        Player player = new Player(100, 100);
        this.batch = b;
        this.world = new World();
        this.world.setPlayer(player);

        world.setPlayer(player);
        world.add(new FloorTile(0, 0));

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.f + player.getX(),
                camera.viewportHeight / 2.f + player.getY(), 0);
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);

        camera.position.set(camera.viewportWidth / 2.f + world.getPlayer().getX() - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (WorldObject obj : world.getObjects()) {
            batch.draw(obj.getTexture(), obj.getX(), obj.getY());
            world.getPlayer().jump(world.getJumpTime());
            if(world.getPlayer().getY() >= world.getJumpTime() /5) {
            	world.getPlayer().setIsJumping(false);
            }
        }
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
