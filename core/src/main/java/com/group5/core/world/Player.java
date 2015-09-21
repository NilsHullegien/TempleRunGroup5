package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;

/**
 * Represents a game character controlled by the user.
 */
public class Player extends AnimatedWorldObject {
    /**
     * The current speed the player is moving at.
     */
    private Vector2 speed;
    /**
     * Speed the player jumps with.
     */
    private float jumpspeed = 0;
    /**
     * time Player is in the air.
     */
    private float falltime = 0;
    /**
     * Boolean if the player is jumping.
     */
    private boolean jumping = false;
    /**
     * Bouncefudge.
     */
    private float bouncefudge = 1000;

    /**
     * Constructs a new Player positioned at the given coordinates.
     * @param coord coordinate
     * @param sizex size of player in pixels
     * @param sizey size of player in pixels
     */
    public Player(final Vector2 coord, final int sizex, final int sizey) {
        super(EndlessRunner.get().getTextureCache().load("chickentime.png")
        , coord, sizex, sizey, 6, 5, 2);
        speed = new Vector2(500, 0);
    }

    @Override
    public void update(final float delta, final World world) {
        setX(getX() + delta * speed.x);
        if (world.getCollider().checkCollision(this) && jumping) {
                speed.y = this.jumpspeed;
                jumping = false;
            }
        if (world.getCollider().checkCollision(this)) {
            world.getCollider().yBounce(this, bouncefudge);
        }
         if (world.getCollider().checkCollision(this) && speed.y < 0) {
            jumping = false;
            speed.y = 0;
            falltime = 0;
            jumpspeed = 0;
        } else {
            this.falltime = this.falltime  + (float) delta;
            // fudgy jump to let it look okay
            float jumpmovement = (float) (jumpspeed * Math.sqrt(falltime) * 1.2);
            speed.y =  jumpmovement - (float) (0.5 * world.getGravity().y * (falltime) * (falltime));
       }
        setY(getY() + speed.y);
        jumping = false;
        //update the animation
        super.update(delta, world);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash * super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player that = (Player) obj;
        return super.equals(obj) && this.speed.equals(that.speed);
    }

    /**
     * The function that let the player jump up. (falling down is done by the
     * gravity). NOTE: the actual movement of the player is done in the
     * updateJumpPosition(float) method.
     * @param jumpingspeed
     *            The time the player jumps.
     */
    public void setjump(final float jumpingspeed) {
        jumping = true;
        this.jumpspeed = jumpingspeed;
    }

    /**
     * Get the speed of the player.
     * @return Vector2
     */
    public Vector2 getSpeed() {
        return this.speed;
    }

    /**
     * Returns the jumpSpeed of the player.
     * Used for testing.
     * @return jumpSpeed of the player.
     */
    public float getJumpSpeed() {
        return jumpspeed;
    }

    /**
     * Returns whether or not the player is jumping.
     * Used for testing.
     * @return jumping true if the player is jumping.
     */
    public boolean getJumping() {
        return jumping;
    }

    /**
     * Sets the players status of jumping.
     * Used for testing.
     * @param jump the new boolean value for jumping (True/False).
     */
    public void setJumping(final boolean jump) {
        jumping = jump;
    }
}
