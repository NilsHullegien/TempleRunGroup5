package com.group5.core.world;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.group5.core.controllers.Director;

import java.util.Iterator;

/**
 * Functions as a container for spawned items in the game.
 */
public class WorldManager {

    /**
     * The scale with which pixel values have to be multiplied to get world-scale values.
     */
    public static final float PHYSICS_SCALE_FACTOR = 1 / 50.f;

    /**
     * The Box2D physics world.
     */
    private World physicsWorld;
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
    private Director director;
    /**
     * Input processor used in LibGDX. Registers when key is pressed/released
     */
    private InputProcessor ip;

    /**
     * Constructs a new, empty world with a default gravity.
     */
    public WorldManager() {
        physicsWorld = new World(new Vector2(0, -50), true);

        ip = new InputProcessor() {

            /**
             * Registers a released button.
             * @param keycode
             *            The integer representation of the button released.
             */
            @Override
            public boolean keyUp(final int keycode) {
                if (keycode == jumpButton) {
                    jumpTime = System.currentTimeMillis() - timerStart;
                    if (jumpTime >= 250L) {
                        jumpTime = 250L;
                    }
                    player.jump(jumpTime / 250.f);
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
     * Returns the world manager's physics world.
     *
     * @return the world manager's physics world
     */
    public World getPhysicsWorld() {
        return physicsWorld;
    }

    /**
     * Return the current player.
     *
     * @return the current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the (new) current player.
     *
     * @param p The new player.
     */
    public void setPlayer(final Player p) {
        player = p;
        physicsWorld.setContactListener(new PlayerCollisionListener(player));
    }

    /**
     * Updates all objects present in the world.
     *
     * @param delta the time that has passed since the previous frame.
     * @param score the current score.
     */
    public void update(final float delta, final float score) {
        WorldObject w;
        Iterator<WorldObject> wIter = director.getObjects(false);
        while (wIter.hasNext()) {
            w = wIter.next();
            w.update(delta, this);
            player.update(delta, this);
            director.update(player.getPosition(), score);
        }
        physicsWorld.step(1 / 60f, 6, 2);
    }

    /**
     * Check whether the player is still alive.
     *
     * @return True if the player is still alive, else false.
     */
    public boolean getGameStatus() {
        return !player.isDead();
    }

    /**
     * get the Director.
     *
     * @return the director.
     */
    public Director getDirector() {
        return director;
    }

    /**
     * Set the director.
     *
     * @param d the director.
     */
    public void setDirector(final Director d) {
        this.director = d;
    }

    /**
     * Getter for the inputProcessor used to capture:
     * key presses and releases.
     * Used in MainGameScreen.render();
     *
     * @return the InputProcessor
     */
    public InputProcessor getInputProcessor() {
        return ip;
    }

    /**
     * Getter for the jumpTime.
     * Testing only!
     *
     * @return the jumpTime.
     */
    public long getJumpTime() {
        return jumpTime;
    }

    /**
     * Getter for the timerStart.
     * Testing only!
     *
     * @return the timerStart.
     */
    public long getTimerStart() {
        return timerStart;
    }

    /**
     * Inner class that handles Box2D collisions.
     */
    public static class PlayerCollisionListener implements ContactListener {
        /**
         * The player that is influenced by collisions.
         */
        private Player player;

        /**
         * Constructs a new collision listener that influences the given player.
         *
         * @param p the player to influence
         */
        public PlayerCollisionListener(final Player p) {
            this.player = p;
        }

        @Override
        public void beginContact(final Contact contact) {
            if (contact.getFixtureA().getUserData() == player
                    && contact.getFixtureB().getUserData() instanceof Obstacle
                    || contact.getFixtureB().getUserData() == player
                    && contact.getFixtureA().getUserData() instanceof Obstacle) {
                WorldManifold w = contact.getWorldManifold();
                float angle = w.getNormal().angle();
                if ((angle < 45
                        || angle > 135 && angle < 225
                        || angle > 315)
                        && !player.isDead()) {
                    player.kill();
                }
            }
        }

        @Override
        public void endContact(final Contact contact) {

        }

        @Override
        public void preSolve(final Contact contact, final Manifold oldManifold) {

        }

        @Override
        public void postSolve(final Contact contact, final ContactImpulse impulse) {

        }
    }
}
