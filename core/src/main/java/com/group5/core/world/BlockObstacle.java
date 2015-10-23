package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;

/**
 * Class that generates a static block obstacle.
 *
 * @author Nils
 */
public class BlockObstacle extends StaticObstacle {

    /**
     * Constructor for a shooting obstacle.
     *
     * @param physicsWorld the world the shooting obstacle is in.
     * @param coord        the coords of the shooting obstacle.
     */
    public BlockObstacle(final World physicsWorld, final Vector2 coord) {
        super(physicsWorld, coord, EndlessRunner.get().getTextureCache().load("obstacle.png"));
    }
}
