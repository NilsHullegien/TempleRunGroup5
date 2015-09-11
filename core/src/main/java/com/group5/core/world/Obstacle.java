package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;

/**
 * Represents an obstacle for the player to avoid.
 *
 */
public class Obstacle extends WorldObject {
    /**
     * Constructor of the obstacle.
     * @param coord Initial position
     */
    public Obstacle(final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("obstacle.png"), coord);
    }

    /**
     * No implementation.
     */
    @Override
    public void update(final float delta, final World world) {
    }
}
