package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;

/**
 * Represents an obstacle for the player to avoid.
 */
public class Obstacle extends WorldObject {

    /**
     * Constructor of the obstacle.
     *
     * @param physicsWorld the physics world to create the obstacle's body in
     * @param coord        Initial position
     */
    public Obstacle(final World physicsWorld, final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("obstacle.png"), coord);
        createPhysicsObject(physicsWorld, coord);
    }

    /**
     * No implementation.
     */
    @Override
    public void update(final float delta, final WorldManager worldManager) {
    }
}
