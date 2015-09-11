package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;
import com.group5.core.controllers.Spawner;

import java.util.ArrayList;
import java.util.Iterator;
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
     * The current player in the game. A game can only have one player at any
     * given time.
     */
    private Player player;

    /**
     * The spawner that spawns new objects into the world.
     */
    private Spawner spawner;

    /**
     * Constructs a new, empty world with a default gravity.
     */
    public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, -150.f);
        objects = new ArrayList<WorldObject>();
        spawner = new Spawner(this);
    }

    /**
     * Adds an object to the world.
     *
     * @param object
     *            Object you want to add to the world
     */
    public void add(final WorldObject object) {
        objects.add(object);
    }

    /**
     * Checks if an object is contained in the world.
     *
     * @param object the object that might be contained in the world
     * @return whether the object is contained in the world
     */
    public boolean contains(final WorldObject object) {
        return objects.contains(object);
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
     * Returns the world's spawner.
     * @return Spawner which spawns new objects into the world.
     */
    public Spawner getSpawner() {
        return spawner;
    }

    /**
     * Set the (new) current player.
     * @param p
     *            The new player.
     */
    public void setPlayer(final Player p) {
        objects.remove(player);
        player = p;
        objects.add(p);
    }

    /**
     * Return the current player.
     * @return the current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Updates all objects present in the world.
     *
     * @param delta
     * the time that has passed since the previous frame.
     */
    public void update(final float delta) {
        spawner.spawnBlocks();
        WorldObject w;
        Iterator<WorldObject> wIter = objects.iterator();
        while (wIter.hasNext()) {
            w = wIter.next();

            if ((w.getX() + w.getTexture().getWidth()) < (player.getX() - 1000)) {
                wIter.remove();
                continue;
            }

            w.update(delta, this);
        }
    }
}
