package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;
import com.group5.core.physics.BulletPhysicsStrategy;

/**
 * Class for the bullet that is shot by the ShootingObstacle.
 *
 * @author Nils.
 */
public class Bullet extends Obstacle {

    /**
     * Constructor of a Bullet.
     *
     * @param physicsWorld the world the bullet is in.
     * @param coord        the coordinates of the bullet.
     */
    public Bullet(final World physicsWorld, final Vector2 coord) {
        super(physicsWorld, coord, EndlessRunner.get().getTextureCache().load("shots.png"));
        setPhysicsStrategy(
                new BulletPhysicsStrategy(
                        physicsWorld,
                        this,
                        new Vector2(getTexture().getWidth() * WorldManager.PHYSICS_SCALE_FACTOR,
                                getTexture().getHeight() * WorldManager.PHYSICS_SCALE_FACTOR),
                        coord,
                        2
                )
        );
    }
}
