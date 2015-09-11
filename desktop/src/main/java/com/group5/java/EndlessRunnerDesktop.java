package com.group5.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group5.core.EndlessRunner;

/**
 * Desktop runner for the EndlessRunner game class.
 */
public abstract class EndlessRunnerDesktop {
    /**
     * Main method that starts the game.
     * @param args the command line parameters passed in
     */
    public static void main(final String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new EndlessRunner(), config);
    }
}
git