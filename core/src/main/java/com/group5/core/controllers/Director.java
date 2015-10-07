package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.OnlyFloorLevel;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.WorldObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


/**
 * Director class which will direct what will appear in the world.
 */
public class Director {
    
    private GameSliceQueue queue;
    
    private Vector2 playerPosition;
    
    private Vector2 cameraPosition;
    
    public Director(int amount, Vector2 playerpos, World world) {
        this.playerPosition = playerpos;
        this.queue = initiateQueue(amount, world);
        addGameSlice(world);
    }
    
    public Iterator<WorldObject> getObjects(boolean onlyonScreen) {
        Iterator<GameSlice> ig;
        if (onlyonScreen){
            ig = queue.getOnScreenSlices();
        } else {
            ig = queue.getSliceIterator();
        }
        LinkedList<WorldObject> ll = new LinkedList<WorldObject>();
        while(ig.hasNext()){
            Iterator<WorldObject> iw = ig.next().getAll();
            while(iw.hasNext()) {
                ll.add(iw.next());
            }
        }
        return ll.iterator();
    }
    
    private GameSliceQueue initiateQueue(int amount, World world) {
        GameSliceQueue GsQ = new GameSliceQueue(amount);
        GsQ.addGameSlice(GameSliceCasting.cast(world));
        for(int i=0; i < amount; i++){
           GsQ.addGameSlice(GameSliceCasting.cast(GsQ.getLast(), world));
        }
        return GsQ;
    }
    
    private void addGameSlice(World world) {
        GameSlice g = GameSliceCasting.cast(world);
        queue.addGameSlice(g);
    }
    
    public void update(Vector2 playerpos){
        this.playerPosition = playerpos;
        queue.update(playerpos, cameraPosition);
    }
}
