package com.group5.core.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Strategy superclass for physics strategies.
 */
public abstract class PhysicsStrategy {
    /**
     * The world in which the strategy is executed.
     */
    private World world;

    /**
     * The physics body the strategy acts on.
     */
    private Body body;

    /**
     * Creates a new physics strategy for the given world.
     *
     * @param w the world to act in.
     */
    public PhysicsStrategy(final World w) {
        this.world = w;
    }

    /**
     * Returns the strategy's world.
     *
     * @return the strategy's world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Returns the strategy's body.
     *
     * @return the strategy's body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets a new physics body for the strategy.
     *
     * @param b the new physics body
     */
    protected void setBody(final Body b) {
        this.body = b;
    }

    /**
     * Updates the physics forces.
     *
     * @param delta the amount of time passed since the last update.
     */
    public abstract void update(final float delta);

}
