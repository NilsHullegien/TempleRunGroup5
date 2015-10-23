package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.physics.PhysicsStrategy;


/**
 * Represents an object that has a presence in a WorldManager.
 */
public abstract class WorldObject {

    /**
     * Size of object.
     */
    private Vector2 size;

    /**
     * The texture of the object.
     */
    private Texture texture;

    /**
     * The object's physics body.
     */
    private PhysicsStrategy physicsStrategy;

    /**
     * Constructs a new world object with the given coordinates, size and texture.
     *
     * @param tex  location of the texture of the object
     * @param sz   size of object
     * @param strategy the physics strategy
     */
    public WorldObject(
            final Texture tex,
            final Vector2 sz,
            final PhysicsStrategy strategy) {
        this.size = sz;
        this.texture = tex;
        this.physicsStrategy = strategy;
    }

    /**
     * Returns the object's physics strategy.
     * @return the object's physics strategy
     */
    public PhysicsStrategy getPhysicsStrategy() {
        return physicsStrategy;
    }

    /**
     * Updates the object's physics strategy.
     * @param strategy the new physics strategy.
     */
    public void setPhysicsStrategy(final PhysicsStrategy strategy) {
        this.physicsStrategy = strategy;
    }

    /**
     * Returns the object's x coordinate.
     *
     * @return the object's x coordinate
     */
    public float getX() {
        return getPhysicsStrategy().getBody().getPosition().x;
    }

    /**
     * Returns the object's width.
     *
     * @return the object's width
     */
    public float getWidth() {
        return size.x;
    }

    /**
     * Returns the object's y coordinate.
     *
     * @return the object's y coordinate
     */
    public float getY() {
        return getPhysicsStrategy().getBody().getPosition().y;
    }

    /**
     * Returns the object's height.
     *
     * @return the object's height
     */
    public float getHeight() {
        return size.y;
    }

    /**
     * Returns the object's texture.
     *
     * @return the object's texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Sets the object's texture.
     * @param tex the new texture of the object
     */
    public void setTexture(final Texture tex) {
        texture = tex;
    }

    /**
     * Return position of player.
     *
     * @return pos of Player
     */
    public Vector2 getPosition() {
        return getPhysicsStrategy().getBody().getPosition();
    }

    /**
     * Call function to render a WorldManager Object.
     *
     * @param batch The batch the object should draw in
     */
    public void doRender(final SpriteBatch batch) {
        batch.draw(getTexture(),
                getX(), getY(),
                0, 0,
                getWidth(), getHeight(),
                1, 1,
                (float) Math.toDegrees(getPhysicsStrategy().getBody().getAngle()),
                0, 0,
                (int) (getWidth() / WorldManager.PHYSICS_SCALE_FACTOR),
                (int) (getHeight() / WorldManager.PHYSICS_SCALE_FACTOR),
                false, false);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Float.floatToIntBits(getX());
        hash = 89 * hash + Float.floatToIntBits(getY());
        hash = 89 * hash + texture.hashCode();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof WorldObject)) {
            return false;
        }
        WorldObject that = (WorldObject) obj;
        return texture == that.texture
                && this.size.equals(that.size)
                && getPosition().equals(that.getPosition());
    }

    /**
     * Updates the state of the object according to the worldManager's state.
     *
     * @param delta        the time that has passed since the previous frame.
     * @param worldManager the worldManager that the object is currently situated in.
     */
    public void update(final float delta, final WorldManager worldManager) {
        physicsStrategy.update(delta);
    }
}
