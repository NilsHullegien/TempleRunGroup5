package com.group5.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.screens.MainMenu;
/**
 * Main game class for the endless runner.
 */
public final class EndlessRunner extends Game {
	
    @Override
    public void create() {
    	SpriteBatch gBatch = new SpriteBatch();
    	
    	//load stuff in gbatch that should be loaded at startup
    	
    	//go into the MainMenu
       setScreen(new MainMenu(gBatch));
    }
    
    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void render() {
       super.render();
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
