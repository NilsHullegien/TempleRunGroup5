package com.group5.core.controllers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.WorldObject;

/**
 * GameSlice is a 2Dimensional rectangle in which objects are placed.
 */
public abstract class GameSlice {
    /**
     * Leftbottom corner of gs.
     */
    private Vector2 startPoint;
    /**
     * RightUp corner of gs.
     */
    private Vector2 endPoint;
    /**
     * Elements in the GameSlice.
     */
    private Collection<WorldObject> elems;
    /**
     * status if onscreen.
     */
    private boolean onscreen;
    /**
     * status if player is on GameSlice.
     */
    private boolean playerison;
    /**
     * Constructor without previous gameslice.
     * @param sP startpoint
     * @param eP endpoint
     */
    public GameSlice(final Vector2 sP, final Vector2 eP) {
        this.startPoint = sP;
        this.endPoint = eP;
        setElems(new LinkedList<WorldObject>());
    }
    /**
     * Constructor wit previous gameslice.
     * @param before Gameslice before this one.
     * @param sP startpoint
     * @param eP endpoint
     */
    public GameSlice(final GameSlice before, final Vector2 sP, final Vector2 eP) {
        this.startPoint = new Vector2(before.getEndPoint().x + sP.x, sP.y);
        this.endPoint = new Vector2(before.getEndPoint().x + eP.x, eP.y);
        setElems(new LinkedList<WorldObject>());
    }
    /**
     * Gets status has Player.
     * @return hasplayer.
     */
    public boolean hasPlayer() {
        return playerison;
    }
    /**
     * Check if the slice is on(also partially) the screen.
     * @param playerpos Playerposition
     * @param camerapos Cameraposition
     * @return boolean
     */
    private boolean checkonScreen(final Vector2 playerpos, final Vector2 camerapos) {
        float leftscreenbound = playerpos.x * 50 - camerapos.x;
        float rightscreenbound = playerpos.x * 50 + 1.5f * Gdx.graphics.getWidth()  - camerapos.x;
        return leftscreenbound <= endPoint.x && rightscreenbound >= startPoint.x;
    }
    /**
     * Check if slice has the player.
     * @param playerpos Position of player.
     * @return if the player is on this slice.
     */
    private boolean checkhasPlayer(final Vector2 playerpos) {
        if (startPoint.x <= playerpos.x * 50 && endPoint.x >= playerpos.x * 50) {
            playerison = true;
        } else {
            playerison = false;
        }
        return playerison;
    }
    /**
     * Return startPoint of this slice.
     * @return Startpoint
     */
    public Vector2 getStartPoint() {
        return startPoint;
    }
    /**
     * Give status is on screen.
     * @return is on screen.
     */
    public boolean isonScreen() {
        return onscreen;
    }
    /**
     * Returns rightupcorner.
     * @return endPoint GameSlice.
     */
    public Vector2 getEndPoint() {
        return endPoint;
    }
    /**
     * Return all elements the GameSlice has.
     * @return Worldobjects iterator.
     */
    public Iterator<WorldObject> getAll() {
        return getElems().iterator();
    }
    /**
     * Update the Slice.
     * @param playerpos PlayerPostion
     * @param camerapos cameraPostition
     */
    public void update(final Vector2 playerpos,  final Vector2 camerapos) {
        this.onscreen = checkonScreen(playerpos, camerapos);
        this.playerison = checkhasPlayer(playerpos);
    }
    /**
     * Get the elements of this GameSlice.
     * @return elements
     */
    public Collection<WorldObject> getElems() {
        return elems;
    }
    /**
     * Set the elements of this GameSlice.
     * @param e elements
     */
    public void setElems(final Collection<WorldObject> e) {
        this.elems = e;
    }

}
