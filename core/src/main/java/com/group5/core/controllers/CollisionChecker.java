package com.group5.core.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

/**
 * Collision checker for game worlds and their objects.
 */
public class CollisionChecker {
    /**
     * The world to check collisions on.
     */
    private World world;

    /**
     * Creates a new collision checker for the given world.
     *
     * @param w the world to check collisions for
     */
    public CollisionChecker(final World w) {
        this.world = w;
    }

    /**
     * Checks if an object collides with any object in the game.
     *
     * @param w Object which needs to be checked
     * @return True when the object is colliding with another object
     */
    public boolean checkCollision(final WorldObject w) {
        for (WorldObject obj : world.getObjects()) {
            if (obj != w && overlap(w, obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two objects overlap.
     *
     * @param w1 First object
     * @param w2 Second object
     * @return True if the two objects overlap
     */
    public boolean overlap(final WorldObject w1, final WorldObject w2) {
        // TODO: Base this on custom bounding boxes instead of texture size
        return w1.getX() + w1.getWidth() > w2.getX()
                && w1.getX() < w2.getX() + w2.getWidth()
                && w1.getY() + w1.getHeight() > w2.getY()
                && w1.getY() < w2.getY() + w2.getHeight();
    }


}
