package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.util.Logger;
import com.group5.core.world.WorldObject;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Director class which will direct what will appear in the world.
 */
public class Director {
    /**
     * Physics world.
     */
    private final World world;
    /**
     * GameSliceQueue assigned to Director.
     */
    private GameSliceQueue queue;
    /**
     * Position of the player, updated every iteration.
     */
    private Vector2 playerPosition;
    /**
     * Camera position, meaning the location of the camera
     * relative to the screen.
     */
    private Vector2 cameraPosition;
    /**
     * Amount of slices that have to be in front.
     */
    private int minfront = 2;
    /**
     * The current score.
     */
    private float score = 0;

    /**
     * Director commands the creation of levels.
     *
     * @param amount    of slices in the queue
     * @param mf        minimal slices in front of the player
     * @param playerpos current position of player
     * @param w         physicsworld
     * @param camerapos position of camera relative to the screen
     */
    public Director(final int amount, final int mf, final Vector2 playerpos, final World w, final Vector2 camerapos) {
        this.playerPosition = playerpos;
        this.cameraPosition = camerapos;
        this.world = w;
        this.minfront = mf;
        this.queue = initiateQueue(amount, w);
    }

    /**
     * Gives an iterator with all objects in some/all gameslices.
     *
     * @param onlyonScreen get all slices or those that are only visible
     * @return iterator with WorldObjects
     */
    public Iterator<WorldObject> getObjects(final boolean onlyonScreen) {
        Iterator<GameSlice> ig;
        if (onlyonScreen) {
            ig = queue.getOnScreenSlices();
        } else {
            ig = queue.getSliceIterator();
        }
        LinkedList<WorldObject> ll = new LinkedList<WorldObject>();
        while (ig.hasNext()) {
            Iterator<WorldObject> iw = ig.next().getAll();
            while (iw.hasNext()) {
                ll.add(iw.next());
            }
        }
        return ll.iterator();
    }

    /**
     * Initiate the GameSliceQueue.
     *
     * @param amount of gameslices
     * @param w      physicsworld
     * @return returns the queue
     */
    private GameSliceQueue initiateQueue(final int amount, final World w) {
        GameSliceQueue gsq = new GameSliceQueue(amount);
        if (amount <= 0) {
            return gsq;
        }
        gsq.addGameSlice(GameSliceCasting.cast(score, w));
        gsq.getLast().update(playerPosition, cameraPosition);
        while (gsq.length() < amount) {
            gsq.addGameSlice(GameSliceCasting.cast(score, gsq.getLast(), w));
            gsq.getLast().update(playerPosition, cameraPosition);
        }
        return gsq;
    }

    /**
     * Commands GameSliceCast for appropriate levelslice and sends it to the queue.
     *
     * @param w physicsworld
     */
    private void addGameSlice(final World w) {
        Logger.get().info("Director", "Adding GameSlice");
        GameSlice g;
        if (queue.isEmpty()) {
            g = GameSliceCasting.cast(score, w);
        } else {
            g = GameSliceCasting.cast(score, queue.getLast(), w);
        }
        queue.addGameSlice(g);
    }

    /**
     * This function handles adding new GameSlice if there
     * are to little slices in front of the player.
     *
     * @param minimal minfront
     */
    public void directQueue(final int minimal) {
        try {
            if (queue.getPlayerinQueue() > minimal) {
                addGameSlice(world);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addGameSlice(world);
        }
    }

    /**
     * Get the queue.
     *
     * @return queue
     */
    public GameSliceQueue getQueue() {
        return queue;
    }

    /**
     * updates Director which commands the queue to update.
     *
     * @param playerpos position of player.
     * @param currentscore the current score
     */
    public void update(final Vector2 playerpos, final float currentscore) {
        this.playerPosition = playerpos;
        this.score = currentscore;
        directQueue(minfront);
        queue.update(playerpos, cameraPosition);
    }
}
