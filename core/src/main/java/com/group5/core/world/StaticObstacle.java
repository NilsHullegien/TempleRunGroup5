package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Represents an obstacle for the player to avoid.
 */
public class StaticObstacle extends Obstacle {

    /**
     * Constructor of the obstacle.
     *
     * @param physicsWorld the physics world to create the obstacle's body in
     * @param coord        Initial position
     * @param tex Texture of the StaticObstacle.
     */
    public StaticObstacle(final World physicsWorld, final Vector2 coord, final Texture tex) {
        super(physicsWorld, coord, tex);
    }

    /**
     * No implementation.
     */
    @Override
    public void update(final float delta, final WorldManager worldManager) {
    }
}
