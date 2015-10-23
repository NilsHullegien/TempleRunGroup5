package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.physics.StaticPhysicsStrategy;

/**
 * Represents an obstacle for the player to avoid.
 */
public class Obstacle extends WorldObject {

    /**
     * Constructor of the obstacle.
     *
     * @param physicsWorld the physics world to create the obstacle's body in.
     * @param coord        Initial position.
     * @param tex          Texture of the Obstacle.
     */
    public Obstacle(final World physicsWorld, final Vector2 coord, final Texture tex) {
        super(tex,
                new Vector2(tex.getWidth() * WorldManager.PHYSICS_SCALE_FACTOR,
                        tex.getHeight() * WorldManager.PHYSICS_SCALE_FACTOR)
        );

        setPhysicsStrategy(new StaticPhysicsStrategy(physicsWorld,
                this,
                new Vector2(getTexture().getWidth() * WorldManager.PHYSICS_SCALE_FACTOR,
                        getTexture().getHeight() * WorldManager.PHYSICS_SCALE_FACTOR),
                coord));
    }
}
