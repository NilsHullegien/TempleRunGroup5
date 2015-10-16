package com.group5.core.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

/**
 * The Queue in which the GameSlices live.
 *
 * @author Levi
 */
public class GameSliceQueue {
    /**
     * * Implementation of the actual queue where the GameSlices are stored.
     */
    private LinkedList<GameSlice> slicequeue;
    /**
     * Number of slices in the queue.
     */
    private int slices;

    /**
     * Constructs a new GameSliceQueue.
     *
     * @param num number of elements in the queue.
     */
    public GameSliceQueue(final int num) {
        this.slicequeue = new LinkedList<GameSlice>();
        this.slices = num;
    }

    /**
     * Checks if queue is Empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return slicequeue.isEmpty();
    }

    /**
     * Checks where the player is in the queue.
     *
     * @return the index of slices the player is in
     * @throws Exception when player isn't in a queueslice.
     */
    public int getPlayerinQueue() throws Exception {
        int count = 0;
        Iterator<GameSlice> it = slicequeue.iterator();
        while (it.hasNext()) {
            GameSlice curr = it.next();
            if (curr.hasPlayer()) {
                return count;
            }
            count++;
        }
        // TODO: need to assign better behaviour when the player isn't in a queue
        throw new Exception("Player is not in a slice");
    }

    /**
     * Handles adding a GameSlice to the queue.
     *
     * @param g GameSlice to add
     * @throws IllegalStateException when a slice needs to be removed that can't be removed.
     */
    public void addGameSlice(final GameSlice g) throws IllegalStateException {
        if (full()) {
            if (slicequeue.peekFirst().hasPlayer()) {
                throw new IllegalStateException("Cannot remove slice that has player");
            }
            slicequeue.poll();
        }
        slicequeue.offer(g);
    }

    /**
     * Return current amount of slices in queue.
     *
     * @return slices in queue.
     */
    public int length() {
        if (slicequeue.isEmpty()) {
            return 0;
        }
        int i = 1;
        Iterator<GameSlice> it = slicequeue.iterator();
        it.next();
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }

    /**
     * True if queue is full.
     *
     * @return true if full.
     */
    public boolean full() {
        return length() >= slices;
    }

    /**
     * Gives the leftbottom corner of the slice that adheres to the index.
     *
     * @param slice input index
     * @return the Startpoint
     * @throws IndexOutOfBoundsException if index given does not exist
     */
    public Vector2 startPointslice(final int slice) throws IndexOutOfBoundsException {
        if (slice > slices || slice < 0) {
            throw new IndexOutOfBoundsException("that slice does not exist");
        }
        Iterator<GameSlice> it = getSliceIterator();
        GameSlice curr = it.next();
        int count = 0;
        while (!(count == slice)) {
            curr = it.next();
            count++;
        }
        return curr.getStartPoint();
    }

    /**
     * Get the slices that are onscreen.
     *
     * @return iterator of onscreen slices.
     */
    public Iterator<GameSlice> getOnScreenSlices() {
        LinkedList<GameSlice> ll = new LinkedList<GameSlice>();
        for (GameSlice g : slicequeue) {
            if (g.isonScreen()) {
                ll.add(g);
            }
        }
        return ll.iterator();
    }

    /**
     * Get the iterator of all slices in queue.
     *
     * @return sliceiterator
     */
    public Iterator<GameSlice> getSliceIterator() {
        return slicequeue.iterator();
    }

    /**
     * Updates the queue and all the slices in it.
     *
     * @param playerpos Playerposition
     * @param camerapos Cameraposition
     */
    public void update(final Vector2 playerpos, final Vector2 camerapos) {
        Iterator<GameSlice> it = getSliceIterator();
        while (it.hasNext()) {
            it.next().update(playerpos, camerapos);
        }
    }

    /**
     * Get the last GameSlice in the queue.
     *
     * @return GameSlice
     */
    public GameSlice getLast() {
        return slicequeue.getLast();
    }

    /**
     * Get the first GameSlice in the queue.
     * @return GameSlice.
     */
    public GameSlice getFirst() {
        return slicequeue.getFirst();
    }
}
