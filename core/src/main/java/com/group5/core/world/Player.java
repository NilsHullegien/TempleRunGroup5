package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;

/**
 * Represents a game character controlled by the user.
 */
public class Player extends WorldObject {
    /**
     * The current speed the player is moving at.
     */
    private Vector2 speed;

    /**
     * Boolean used for the check if the player is jumping or not.
     * (jumping means player.getY() < 65).
     */
    private boolean isJumping = false;

    /**
     * Constructs a new Player positioned at the given coordinates.
     *
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     */
    public Player(final float x, final float y) {
        super(EndlessRunner.get().getTextureCache().load("playerBlock.png"), x, y);
        speed = new Vector2(250, 0);
    }

    @Override
    public void update(final float delta, final World world) {
        // TODO: There's a cleaner way to handle this
        float oldX = getX();
        setX(oldX + speed.x * delta + world.getGravity().x * delta);
        if (world.getCollider().checkCollision(this)) {
            setX(oldX);
        }

        float oldY = getY();
        setY(oldY + speed.y * delta + world.getGravity().y * delta);
        if (world.getCollider().checkCollision(this)) {
            setY(oldY);
        }
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
     * The function that let the player jump up.
     * (falling down is done by the gravity).
     * NOTE: the actual movement of the player is done in the
     * updateJumpPosition(float) method.
     * @param jumpTime The time the player jumps.
     */
    public void jump(float jumpTime) {
    	if (isJumping && getY() <= jumpTime / 5) {
    	    for (int i = 0; i < 20; i++) {
    	        jumpTime = (float) (jumpTime / 5 * 0.2 * 0.05);
    	        updatePlayerPos((jumpTime));
    	    }
    	    jumpTime = 0;
    	}
    }

    /**
     * Updates the position of the player.
     * @param y the height the player jumps.
     */
    public void updatePlayerPos(final float y) {
    	setY(getY() + y);
    }

    /**
     * Checks if the player is jumping.
     * @return the isJumping variable.
     */
    public boolean isJumping() {
    	return isJumping;
    }

    /**
     * Set the isJumping variable.
     * @param isJ the new value for isJumping (boolean)
     */
    public void setIsJumping(final boolean isJ) {
    	isJumping = isJ;
    }
}
