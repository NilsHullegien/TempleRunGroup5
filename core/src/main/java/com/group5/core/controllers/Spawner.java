package com.group5.core.controllers;

import java.util.ArrayList;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

/**
 * Spawns objects into a world.
 */
public class Spawner {

    /**
     * World variable in which new objects will be added.
     */
    private World world;

    /**
     * Director class for the spawner.
     */
    private Director director;

    /**
     * Constructor of the spawner for the world.
     * @param w the world the spawner is created for.
     */
    public Spawner(final World w) {
        world = w;
        director = new Director(this);
    }

    /**
     * Method for the spawner to find the last floor position.
     * @return 0 or the right most floor x coordinate of the last added floor.
     */
    public float getLastFloor() {
        FloorTile floor = null;
        for (WorldObject w : world.getObjects()) {
            if (w instanceof FloorTile && (floor == null || w.getX() > floor.getX())) {
                floor = (FloorTile) w;
            }
        }
        if (floor == null) {
            return 0;
        }
        return floor.getX() + floor.getTexture().getWidth();
    }

    /**
     * Method to get the player position.
     * @return the x coordinate of the player.
     */
    public float getPlayerPosition() {
        return world.getPlayer().getX();
    }

    /**
     * Method to get the size of the FloorTile.
     * @return 0 or the size of the FloorTile as a float.
     */
    public float getFloorSize() {
        FloorTile floor = null;
        for (WorldObject w : world.getObjects()) {
            if (w instanceof FloorTile) {
                floor = (FloorTile) w;
                return floor.getTexture().getWidth();
            }
        }
        return 0.f;
    }

    /**
     * Method to get the size of the Player.
     * @return the size of the Player as a float.
     */
    public float getPlayerSize() {
        return world.getPlayer().getWidth();
    }

    /**
     * Method for the spawner to find the last obstacle position.
     * @return 0 or the most right position of the last obstacle.
     */
    public float getLastObstacle() {
        Obstacle obstacle = null;
        for (WorldObject w : world.getObjects()) {
            if (w instanceof Obstacle && (obstacle == null || w.getX() > obstacle.getX())) {
                obstacle = (Obstacle) w;
            }
        }
        if (obstacle == null) {
            return 0;
        }
        return obstacle.getX() + obstacle.getTexture().getWidth();
    }

    /**
     * Method to get the rightmost position that is of interest for the spawner.
     * In case that the director state is in the ObstacleCourse state this
     * method can also return the rightmost position of the last obstacle.
     * @return 0 or the rightmost position of the last obstacle or floor.
     */
    public float getMostRightPos() {
        float lastObst = getLastObstacle();
        float lastFloor = getLastFloor();
        if (director.getState().getSlice().equals("ObstacleCourse")) {
            if (lastObst > lastFloor) {
                return lastObst;
            }
        }
        return lastFloor;
    }

    /**
     * Method to get the director class of this spawner.
     * @return the director of this spawner.
     */
    public Director getDirector() {
        return director;
    }

    /**
     * Method to spawn new objects into the world. Objects will be added within
     * a certain range. The objects that will be added are selected by the
     * director.
     */
    public void spawnBlocks() {
        if (getMostRightPos() - getPlayerPosition() < 700) {
            ArrayList<WorldObject> listToAdd = director.direct();
            for (WorldObject w : listToAdd) {
                world.add(w);
            }
        }
    }
}
