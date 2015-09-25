package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


/**
 * Represents an object that has a presence in a WorldManager.
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

    /**
     * The object's physics body.
     */
    private Body physicsBody;

    /**
     * Constructs a world object with the given coordinates and texture, and the texture size as size.
     *
     * @param tex   location of the texture of the object
     * @param coord coordinate of object
     */
    public WorldObject(final Texture tex, final Vector2 coord) {
        this(tex, new Vector2(tex.getWidth() / 50.f, tex.getHeight() / 50.f), coord);
    }

    /**
     * Constructs a new world object with the given coordinates, size and texture.
     *
     * @param tex   location of the texture of the object
     * @param siz   size of the object
     * @param coord coordinate of object
     */
    public WorldObject(
            final Texture tex,
            final Vector2 siz,
            final Vector2 coord) {
        this.pos = coord;
        this.size = siz;
        this.texture = tex;
    }

    /**
     * Returns the object's physics body.
     *
     * @return the object's physics body
     */
    public Body getPhysicsBody() {
        return physicsBody;
    }

    /**
     * Sets the object's physics body.
     *
     * @param body the object's physics body
     */
    public void setPhysicsBody(final Body body) {
        this.physicsBody = body;
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
     * Sets the object's x coordinate.
     *
     * @param newX the new x coordinate
     */
    public void setX(final float newX) {
        this.pos.set(newX, this.pos.y);
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
        return pos.y;
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
     * Call function to render a WorldManager Object.
     *
     * @param batch The batch the object should draw in
     */
    public void doRender(final SpriteBatch batch) {
        pos = getPhysicsBody().getPosition();
        batch.draw(getTexture(),
                pos.x * 50.f, pos.y * 50.f,
                0, 0,
                getWidth() * 50.f, getHeight() * 50.f,
                1, 1,
                (float) Math.toDegrees(getPhysicsBody().getAngle()),
                0, 0,
                (int) getWidth() * 50, (int) getHeight() * 50,
                false, false);
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
     * Updates the state of the object according to the worldManager's state.
     *
     * @param delta        the time that has passed since the previous frame.
     * @param worldManager the worldManager that the object is currently situated in.
     */
    public abstract void update(final float delta, final WorldManager worldManager);

    /**
     * Sets the texture of a WorldObject.
     * @param t the new texture that the worldObject should get.
     */
    public void setTexture(final Texture t) {
        texture = t;
    }
}
