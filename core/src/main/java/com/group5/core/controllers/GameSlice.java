package com.group5.core.controllers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.Screen;
import com.group5.core.world.WorldObject;

/**
 * GameSlice Class, here all the RNG and all needed variables for the director
 * for different gameslices.
 */
public abstract class GameSlice {
        
    protected Vector2 startPoint;
    
    protected Vector2 endPoint;
    
    protected Collection<WorldObject> elems;
    
    private boolean onscreen;
    
    private boolean playerison;
    
    public GameSlice (Vector2 sP, Vector2 eP) {
        this.startPoint = sP;
        this.endPoint = eP;
    }
    
    public GameSlice (GameSlice before, Vector2 sP, Vector2 eP) {
        this.startPoint = new Vector2(before.getendPoint().x + sP.x, sP.y);
        this.endPoint = new Vector2(before.getendPoint().x + eP.x, eP.y);
    }
    
    public boolean hasPlayer() {
        return playerison;
    }
    
    public void adjust() throws Exception {
        
    }
    
    private boolean checkonScreen(Vector2 playerpos, Vector2 camerapos) {
        float leftscreenbound = playerpos.x - camerapos.x;
        float rightscreenbound = playerpos.x + Screen.getscreenX() - camerapos.x;
        if(leftscreenbound > endPoint.x) {
            return false;
        } else if (rightscreenbound < startPoint.x){
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkhasPlayer(Vector2 playerpos, Vector2 camerapos) {
        if(startPoint.x < playerpos.x && endPoint.x > playerpos.x) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isonScreen() {
        return onscreen;
    }
    
    public Vector2 getstartPoint() {
        return startPoint;
    }
    
    public Vector2 getendPoint() {
        return endPoint;
    }
    /*
    public Iterator<WorldObject> getAll() {
        LinkedList ll = new LinkedList<WorldObject>();
        for(WorldObject e: elems) {
            e.setX(e.getX()+absolutePosition.x);
            e.setY(e.getY()+absolutePosition.y);
        }
        return ll.iterator();
    }
    */
    
    public Iterator<WorldObject> getAll() {
        return elems.iterator();
    }
    
    public void update (Vector2 playerpos, Vector2 camerapos){
        this.onscreen = checkonScreen(playerpos, camerapos);
        this.playerison = checkhasPlayer(playerpos, camerapos);
    }

}
