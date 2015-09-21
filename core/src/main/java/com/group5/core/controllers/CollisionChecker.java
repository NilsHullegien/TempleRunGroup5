package com.group5.core.controllers;

import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

/**
 * Collision checker for game worlds and their objects.
 */
public class CollisionChecker {
    /**
     * The world to check collisions on.
     */
    private World world;

    /**
     * Creates a new collision checker for the given world.
     *
     * @param w the world to check collisions for
     */
    public CollisionChecker(final World w) {
        this.world = w;
    }

    /**
     * Checks if an object collides with any object in the game.
     *
     * @param w Object which needs to be checked
     * @return True when the object is colliding with another object
     */
    public boolean checkCollision(final WorldObject w) {
        for (WorldObject obj : world.getObjects()) {
            if (obj != w && overlap(w, obj)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if an object has moved partially inside an other object vertically and bounces it back.
     * @param w object to bounce back
     * @param maxdiff the maximum difference
     */
    public void yBounce(final WorldObject w, final float maxdiff) {
        for (WorldObject obj : world.getObjects()) {
             if (obj != w && overlap(w, obj)) {
                 if (overlapTop(w, obj)) {
                     float diff = obj.getHeight() + obj.getY() - w.getY();
                     if (diff < maxdiff) {
                         // crazily enough need to recompute the difference here
                         w.setY(w.getY() + obj.getHeight() + obj.getY() - w.getY());
                         System.out.println(diff);
                     }
                 } else {
                     if (overlapBottom(w, obj)) {
                         float diff = w.getY() + w.getHeight() - obj.getY();
                         if (diff < maxdiff) {
                             // crazily enough need to recompute the difference here
                             w.setY(w.getY() - w.getY() + w.getHeight() - obj.getY());
                         }
                     }
                 }
            }
        }
        }
    /**
     * Checks if an object has moved partially inside an other object horizontally and bounces it back.
     * @param w object to bounce back
     */
    public void xBounce(final WorldObject w) {
        for (WorldObject obj : world.getObjects()) {
            if (obj != w && overlap(w, obj)) {
                if (overlapLeft(w, obj)) {
                    w.setX(w.getX() + obj.getX() - (w.getWidth() + w.getX()));
                } else {
                    if (overlapRight(obj, w)) {
                    w.setX(w.getX() + obj.getX() + obj.getWidth() -  w.getX());
                    }
                }
            }
        }
    }
    /**
     * Checks if two objects overlap.
     *
     * @param w1 First object
     * @param w2 Second object
     * @return True if the two objects overlap
     */
    public boolean overlap(final WorldObject w1, final WorldObject w2) {
        return w1.getX() + w1.getWidth() > w2.getX()
                && w1.getX()  < w2.getX() + w2.getWidth()
                && w1.getY() + w1.getHeight() > w2.getY()
                && w1.getY() < w2.getY() + w2.getHeight();
    }
    /**
     * Is true if the left of w1 overlaps with the right of w2.
     * @param w1 overlapping w2 from the it's left
     * @param w2 being overlapped by w1 from the right
     * @return boolean
     */
     public boolean overlapLeft(final WorldObject w1, final WorldObject w2) {
        // TODO: Base this on custom bounding boxes instead of texture size
        return w1.getX() + w1.getWidth() > w2.getX();

    }
     /**
     * Is true if the right of w1 overlaps with the left of w2.
     * @param w1 overlapping w2 from the it's right
     * @param w2 being overlapped by w1 from the left
     * @return boolean
     */
    public boolean overlapRight(final WorldObject w1, final WorldObject w2) {
        return w1.getX() < w2.getX() + w2.getWidth();
    }
    /**
     * Is true if the bottom of w1 overlaps the top of w2.
     * @param w1 overlapping w2 from the it's top
     * @param w2 being overlapped by w1 from the bottom
     * @return boolean
     */
    public boolean overlapTop(final WorldObject w1, final WorldObject w2) {
        return w1.getY() < w2.getY() + w2.getHeight();
    }
    /**
     * True if the top of w1 overlaps the bottom of w2.
     * @param w1 overlapping w2 from the it's bottom
     * @param w2 being overlapped by w1 from the top
     * @return boolean
     */
    public boolean overlapBottom(final WorldObject w1, final WorldObject w2) {
        return w1.getY() + w1.getHeight() > w2.getY();

    }


}
