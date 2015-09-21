package com.group5.core.world;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
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
     * Gets the time the player held down the jumpButton. Getter and setter are
     * provided.
     */
    private long jumpTime;

    /**
     * The current player in the game. A game can only have one player at any
     * given time.
     */
    private Player player;

    /**
     * The button pressed for jumping.
     */
    private char jumpButton = Keys.W;

    /**
     * Timer start and stop for determining how long the player jumps.
     */
    private long timerStart = 0L;

    /**
     * The spawner that spawns new objects into the world.
     */
    private Spawner spawner;

    /**
     * Input processor used in LibGDX. Registers when key is pressed/released
     */
    private InputProcessor ip;

    /**
     * Constructs a new, empty world with a default gravity.
     */
    public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, 75);
        objects = new ArrayList<WorldObject>();
        spawner = new Spawner(this);
        ip = new InputProcessor() {

            /**
             * Registers a released button.
             * @param keycode
             *            The integer representation of the button released.
             */
            @Override
            public boolean keyUp(final int keycode) {
                System.out.println("Player Y: " + player.getY());
                if (keycode == jumpButton) {
                    jumpTime = System.currentTimeMillis() - timerStart;
                    if (jumpTime >= 250L) {
                        jumpTime = 250L;
                    }
                    //player.setjump(jumpTime/250* 20f);
                    player.setjump(20f);
                }
                return false;
            }

            /**
             * Registers a pressed button.
             * @param keycode
             *            The integer representation of the button pressed.
             */
            @Override
            public boolean keyDown(final int keycode) {
                if (keycode == jumpButton) {
                    System.out.println("Timer started");
                    timerStart = System.currentTimeMillis();
                }
                return false;
            }

            // ////////////////
            // UNUSED METHODS//
            // ////////////////
            /**
             * Unused.
             */
            @Override
            public boolean touchUp(final int screenX, final int screenY,
                    final int pointer, final int button) {
                return false;
            }

            /**
             * Unused.
             */
            @Override
            public boolean touchDragged(final int screenX, final int screenY,
                    final int pointer) {
                return false;
            }

            /**
             * Unused.
             */
            @Override
            public boolean touchDown(final int screenX, final int screenY,
                    final int pointer, final int button) {
                return false;
            }

            /**
             * Unused.
             */
            @Override
            public boolean scrolled(final int amount) {
                return false;
            }

            /**
             * Unused.
             */
            @Override
            public boolean mouseMoved(final int screenX, final int screenY) {
                return false;
            }

            /**
             * Unused.
             */
            @Override
            public boolean keyTyped(final char character) {
                return false;
            }

            // ////////////////////
            // END UNUSED METHODS//
            // ////////////////////
        };
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

    /**
     * Sets the jumpTime variable.
     * @param newTime
     *            The time jumpTime needs to be set to.
     */
    public void setJumpTime(final long newTime) {
        jumpTime = newTime;
    }

    /**
     * Returns the jumpTime variable.
     * @return the jumpTime variable.
     */
    public long getJumpTime() {
        return jumpTime;
    }
    /**
     * Check whether the player is still alive.
     * @return True if the player is still alive, else false.
     */
    public boolean getGameStatus() {
        return !(player.getY() < 0.f);
    }

    /**
     * Getter for the inputProcessor used to capture:
     * key presses and releases.
     * Used in MainGameScreen.render();
     * @return the InputProcessor
     */
    public InputProcessor getInputProcessor() {
        return ip;
    }

    /**
     * Method that sets the spawner of the world.
     * Used for testing.
     * @param sp the new spawner.
     */
    public void setSpawner(final Spawner sp) {
        spawner = sp;
    }
}
