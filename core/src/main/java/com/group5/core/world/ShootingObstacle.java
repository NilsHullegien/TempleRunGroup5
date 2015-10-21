package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;

/**
 * Class for a shooting Obstacle.
 * @author Nils.
 *
 */
public class ShootingObstacle extends Obstacle {

    /**
     * A bullet that is shot by this obstacle.
     */
    private Bullet b;

    /**
     * Constructor for a shooting obstacle.
     * @param physicsWorld the world the shooting obstacle is in.
     * @param coord the coords of the shooting obstacle.
     */
    public ShootingObstacle(final World physicsWorld, final Vector2 coord) {
        super(physicsWorld, coord, EndlessRunner.get().getTextureCache().load("Obstacle1.png"));
        this.b = new Bullet(physicsWorld, coord);
        b.createPhysicsObject(physicsWorld, coord);

    }
}
