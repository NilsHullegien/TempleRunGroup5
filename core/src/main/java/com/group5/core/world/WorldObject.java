package com.group5.core.world;

import com.badlogic.gdx.files.FileHandle;
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
     * @param tex location of the texture of the object
     * @param xCoord x coordinate of the object
     * @param yCoord y coordinate of the object
     */
    public WorldObject(final FileHandle tex,
                       final float xCoord,
                       final float yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.texture = new Texture(tex);
    }

    /**
     * Returns the object's x coordinate.
     * @return the object's x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the object's x coordinate.
     * @param newX the new x coordinate
     */
    public void setX(final float newX) {
        this.x = newX;
    }

    /**
     * Returns the object's y coordinate.
     * @return the object's y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the object's y coordinate.
     * @param newY the new y coordinate
     */
    public void setY(final float newY) {
        this.y = newY;
    }

    /**
     * Returns the object's texture.
     * @return the object's texture
     */
    public Texture getTexture() {
        return this.texture;
    }
    
    /**
     * Updates the state of the object according to the world's state.
     * @param delta the time that has passed since the previous frame.
     * @param world the world that the object is currently situated in.
     */
    public abstract void update(final float delta, final World world);
    
    public abstract boolean equals(Object obj);
    
}
