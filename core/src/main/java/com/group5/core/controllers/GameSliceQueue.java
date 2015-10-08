package com.group5.core.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.util.Logger;
/**
 * The Queue in which the GameSlices live.
 * @author Levi
 *
 */
public class GameSliceQueue {
    /**
    ** Implementation of the actual queue where the GameSlices are stored.
    */
    private LinkedList<GameSlice> slicequeue;
    /**
     * Number of slices in the queue.
     */
    private int slices;
    /**
     * In which sliceindex in the queue the player is currently.
     */
    private int playerinqueue;
    /**
     * Constructs a new GameSliceQueue.
     * @param num number of elements in the queue.
     */
    public GameSliceQueue(final int num) {
        this.slicequeue = new LinkedList<GameSlice>();
        this.slices = num;
    }
    /**
     * Checks if queue is Empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return slicequeue.isEmpty();
    }
    /**
     * Checks where the player is in the queue.
     * @return the index of slices the player is in
     * @throws Exception when player isn't in a queueslice.
     */
    public int getPlayerinQueue() throws Exception {
    int count = 0;
    Iterator<GameSlice> it = slicequeue.iterator();
    boolean b = true;
    while (it.hasNext()) {
        if (!it.hasNext()) {
            b = false;
        }
        GameSlice curr = it.next();
        if (curr.hasPlayer()) {
            return count;
    }
        count++;
    }
    // TODO: need to assign better behaviour when the player isn't in a queue
    return 2000;
    }
    /**
     * Handles adding a GameSlice to the queue.
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
        Logger.get().info("GameSliceQueue", "Creating");
    }
    /**
     * Return current amount of slices in queue.
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
     * @return true if full.
     */
    public boolean full() {
        return length() >= slices;
    }
    /**
     * Gives the leftbottom corner of the slice that adheres to the index.
     * @param slice input index
     * @return the Startpoint
     */
    public Vector2 startPointslice(final int slice) {
        if (slice > slices || slice < 0) {
            new Exception("that slice does not exist");
        }
        Iterator<GameSlice> it = getSliceIterator();
        GameSlice curr = it.next();
        int count = 0;
        if (count == 0 && !it.hasNext() && length() == 2) {
            System.out.println("NO WAY");
        }
        while (!(count == slice)) {
                curr = it.next();
                count++;
            }
        return curr.startPoint;
    }
    /**
     * Get the slices that are onscreen.
     * @return iterator of onscreen slices.
     */
    public Iterator getOnScreenSlices() {
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
     * @return sliceiterator
     */
    public Iterator getSliceIterator() {
        return slicequeue.iterator();
    }
    /**
     * Updates the queue and all the slices in it.
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
     * @return GameSlice
     */
    public GameSlice getLast() {
        return slicequeue.getLast();
    }
}
