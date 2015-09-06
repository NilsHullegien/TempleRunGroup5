package com.group5.core.screens;

import worldObjects.FloorTile;
import worldObjects.Player;
import worldObjects.World;
import worldObjects.WorldObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.WorldController;

/**
 * Main game screen.
 */
public class RunGame implements Screen {

    /**
     * A spritebatch
     */
    private SpriteBatch batch;

    /**
     * The amount of time that has elapsed.
     */
    private float elapsed;
    

    public RunGame() {
    	batch = new SpriteBatch();

 
        WorldController.createWorld();
        WorldController.add(new Player(100,100));
        WorldController.add(new FloorTile(0,0));
        
      
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
        World w = WorldController.getWorld();
        for(WorldObject obj: w.getObjects()){
        	batch.draw(new Texture(obj.getTexture()), obj.getX(), obj.getY());
        }
        w.updatePosFloorTiles();
        w.updatePosPlayer();
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
