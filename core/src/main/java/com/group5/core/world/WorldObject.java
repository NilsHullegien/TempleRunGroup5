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
     * The texture of the object.
     */
    private Texture texture;
    /**
     * Constructs a new world object with the given coordinates and texture.
     *
     * @param tex    location of the texture of the object
     * @param coord  coordinate of object
     */
    public WorldObject(final Texture tex,
                       final Vector2 coord
                        ) {
        this.pos = coord;
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
    public int getWidth() {
        return texture.getWidth();
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
    public int getHeight() {
        return texture.getHeight();
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

    /**
     * Sets the texture of a WorldObject.
     * @param t the new texture that the worldObject should get.
     */
    public void setTexture(final Texture t) {
        texture = t;
    }
}
