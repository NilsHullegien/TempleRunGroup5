package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;
import com.group5.core.physics.PlayerPhysicsStrategy;

/**
 * Represents a game character controlled by the user.
 */
public class Player extends AnimatedWorldObject {

    /**
     * Whether or not the player is dead.
     */
    private boolean dead = false;

    /**
     * Constructs a new Player positioned at the given coordinates.
     *
     * @param physicsWorld the physics world to create the player's body in
     * @param coord        coordinate
     * @param size         size of player in pixels
     */
    public Player(final World physicsWorld, final Vector2 coord, final Vector2 size) {
        super(EndlessRunner.get().getTextureCache().load("chickentime.png"),
                size,
                6,
                5,
                2,
                null);
        setPhysicsStrategy(new PlayerPhysicsStrategy(physicsWorld, this, size, coord, 10));
    }

    /**
     * Checks whether the player is dead.
     *
     * @return whether the player is dead
     */
    public boolean isDead() {
        dead = dead || getY() < 0.f;
        return dead;
    }

    /**
     * Kills the player instantly.
     */
    public void kill() {
        dead = true;
    }

    @Override
    public void update(final float delta, final WorldManager worldManager) {
        //update the animation
        super.update(delta, worldManager);
    }

    /**
     * The function that let the player jump up. (falling down is done by the
     * gravity). NOTE: the actual movement of the player is done in the
     * updateJumpPosition(float) method.
     *
     * @param jumpIntensity How hard the player jumps.
     */
    public void jump(final float jumpIntensity) {
        ((PlayerPhysicsStrategy) getPhysicsStrategy()).jump(jumpIntensity);
    }
}
