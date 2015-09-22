package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Represents an object that has a presence in a World.
 */
public abstract class WorldObject {

    /**
     * Position of object.
     */
    private Vector2 pos;

    /**
     * Size of object.
     */
    private Vector2 size;

    /**
     * The texture of the object.
     */
    private Texture texture;

    /** Constructs a world object with the given coordinates and texture, and the texture size as size.
     *
     * @param tex location of the texture of the object
     * @param coord coordinate of object
     */
    public WorldObject(final Texture tex, final Vector2 coord) {
        this(tex, new Vector2(tex.getWidth(), tex.getHeight()), coord);
    }

    /**
     * Constructs a new world object with the given coordinates, size and texture.
     *
     * @param tex    location of the texture of the object
     * @param siz   size of the object
     * @param coord  coordinate of object
     */
    public WorldObject(final Texture tex,
                       final Vector2 siz,
                       final Vector2 coord) {
        this.pos = coord;
        this.size = siz;
        this.texture = tex;
    }
    /**
     * Returns the object's x coordinate.
     *
     * @return the object's x coordinate
     */
    public float getX() {
        return pos.x;
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
     * Sets the object's x coordinate.
     *
     * @param newX the new x coordinate
     */
    public void setX(final float newX) {
        this.pos.set(newX, this.pos.y);
    }

    /**
     * Returns the object's y coordinate.
     *
     * @return the object's y coordinate
     */
    public float getY() {
        return pos.y;
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
     * Sets the object's y coordinate.
     *
     * @param newY the new y coordinate
     */
    public void setY(final float newY) {
        this.pos.set(this.pos.x, newY);
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
     * Call function to render a World Object.
     * @param batch The batch the object should draw in
     */
    public void doRender(final SpriteBatch batch) {
        batch.draw(getTexture(), this.pos.x, this.pos.y);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Float.floatToIntBits(pos.x);
        hash = 89 * hash + Float.floatToIntBits(pos.y);
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
                && this.pos.equals(that.pos);
    }

    /**
     * Updates the state of the object according to the world's state.
     *
     * @param delta the time that has passed since the previous frame.
     * @param world the world that the object is currently situated in.
     */
    public abstract void update(final float delta, final World world);
}
