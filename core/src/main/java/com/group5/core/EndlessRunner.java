package com.group5.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group5.core.screens.MainMenu;

/**
 * Startup class for the Game, from here all textures are loaded
 * and the mainmenu is started.
 */
public final class EndlessRunner extends Game {

	@Override
    public void create() {
       SpriteBatch batch = new SpriteBatch();
       setScreen(new MainMenu(batch));
       }
}
