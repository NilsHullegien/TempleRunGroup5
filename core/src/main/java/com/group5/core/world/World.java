package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Functions as a container for spawned items in the game.
 */
public class World {
    /**
     * The collision checker that is used to verify this world's state.
     */
    private CollisionChecker collider;

    /**
     * The objects that are present in this world.
     */
    private ArrayList<WorldObject> objects;

    /**
     * The gravity this world is operating in.
     */
    private Vector2 gravity;

    /**
     * Constructs a new, empty world with a default gravity.
     */
    public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, -150.f);
        objects = new ArrayList<WorldObject>();
    }

    /**
     * Adds an object to the world.
     *
     * @param object Object you want to add to the world
     */
    public void add(final WorldObject object) {
        objects.add(object);
    }

    /**
     * Returns the world's collider.
     *
     * @return the world's collider
     */
    public CollisionChecker getCollider() {
        return collider;
    }

    /**
     * Returns the world's operating gravity.
     *
     * @return the world's operating gravity
     */
    public Vector2 getGravity() {
        return gravity;
    }

    /**
     * Returns the objects contained in the world.
     *
     * @return the objects contained in the world.
     */
    public List<WorldObject> getObjects() {
        return objects;
    }

    /**
     * Updates all objects present in the world.
     *
     * @param delta the time that has passed since the previous frame.
     */
    public void update(final float delta) {
        for (WorldObject w : objects) {
            w.update(delta, this);
        }
    }

}
