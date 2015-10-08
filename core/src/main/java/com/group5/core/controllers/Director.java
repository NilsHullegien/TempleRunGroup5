package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.OnlyFloorLevel;
import com.group5.core.util.Logger;
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
    
    private final World world;
    
    private int minfront =2 ;
    
    public Director(int amount, Vector2 playerpos, World world, Vector2 camerapos) {
        this.playerPosition = playerpos;
        this.cameraPosition = camerapos;
        this.world = world;
        this.queue = initiateQueue(amount, world);
    }
    
    public Director(int amount, int minfront, Vector2 playerpos, World world, Vector2 camerapos) {
        this.playerPosition = playerpos;
        this.queue = initiateQueue(amount, world);
        this.cameraPosition = camerapos;
        this.world = world;
        this.minfront = minfront;
        initiateQueue(amount, world);
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
        if (amount <= 0) {
            return GsQ;
        }
        GsQ.addGameSlice(GameSliceCasting.cast(world));
        System.out.println(GsQ.length());
        while(GsQ.length()<amount) {
           GsQ.addGameSlice(GameSliceCasting.cast(GsQ.getLast(), world));
        }
        return GsQ;
    }
    
    private void addGameSlice(World world) {
        Logger.get().info("Director", "Adding GameSlice");
        GameSlice g;
        if (queue.isEmpty()){
            g = GameSliceCasting.cast(world);
        } else {
            g = GameSliceCasting.cast(queue.getLast(), world);
        }
        queue.addGameSlice(g);
    }
    
    public void directQueue(int minimal) {
        try {
            System.out.println(queue.getPlayerinQueue());
            if (queue.getPlayerinQueue() > minimal) {
                System.out.println(queue.getPlayerinQueue());
                addGameSlice(world);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addGameSlice(world);
        }
    }
    
    public GameSliceQueue getQueue() {
        return queue;
    }
    
    public void update(Vector2 playerpos){
        this.playerPosition = playerpos;
        directQueue(minfront);
        queue.update(playerpos, cameraPosition);
    }
}
