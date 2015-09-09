package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;


/**
 * Represents an object that has a presence in a World.
 */
public abstract class WorldObject {

    /**
     * The world-relative x coordinate of the object.
     */
    private float x;

    /**
     * The world-relative y coordinate of the object.
     */
    private float y;

    /**
     * The texture of the object.
     */
    private Texture texture;

    /**
     * Constructs a new world object with the given coordinates and texture.
     *
     * @param tex    location of the texture of the object
     * @param xCoord x coordinate of the object
     * @param yCoord y coordinate of the object
     */
    public WorldObject(final Texture tex,
                       final float xCoord,
                       final float yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.texture = tex;
    }

    /**
     * Returns the object's x coordinate.
     *
     * @return the object's x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the object's x coordinate.
     *
     * @param newX the new x coordinate
     */
    public void setX(final float newX) {
        this.x = newX;
    }

    /**
     * Returns the object's y coordinate.
     *
     * @return the object's y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the object's y coordinate.
     *
     * @param newY the new y coordinate
     */
    public void setY(final float newY) {
        this.y = newY;
    }

    /**
     * Returns the object's texture.
     *
     * @return the object's texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Float.floatToIntBits(x);
        hash = 89 * hash + Float.floatToIntBits(y);
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
                && Math.abs(that.getX() - this.getX()) < 0.01f
                && Math.abs(that.getY() - this.getY()) < 0.01f;
    }

    /**
     * Updates the state of the object according to the world's state.
     *
     * @param delta the time that has passed since the previous frame.
     * @param world the world that the object is currently situated in.
     */
    public abstract void update(final float delta, final World world);
}
