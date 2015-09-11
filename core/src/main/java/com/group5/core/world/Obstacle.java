package com.group5.core.world;

import com.group5.core.EndlessRunner;

/**
 * Represents an obstacle for the player to avoid.
 *
 */
public class Obstacle extends WorldObject {

    /**
     * Constructor of the obstacle.
     * @param x
     *            the x coordinate of the obstacle.
     * @param y
     *            the y coordinate of the obstacle.
     */
    public Obstacle(final float x, final float y) {
        super(EndlessRunner.get().getTextureCache().load("obstacle.png"), x, y);
    }

    /**
     * No implementation.
     */
    @Override
    public void update(final float delta, final World world) {
    }
}
