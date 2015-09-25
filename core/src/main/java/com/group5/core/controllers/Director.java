package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.WorldObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Director class which will direct what will appear in the world.
 */
public class Director {
    /**
     * The current GameSlice of the director.
     */
    private GameSlice state;
    /**
     * The list with all the available GameSlices.
     */
    private ArrayList<GameSlice> sliceList;
    /**
     * Random class for randomization of objects.
     */
    private Random random;
    /**
     * The max interval between two floors as an integer.
     */
    private int floorInterval = 4;
    /**
     * The max interval between two obstacles as an integer.
     */
    private int obstacleInterval = 5;
    /**
     * The spawner for which the director will direct.
     */
    private Spawner spawner;

    /**
     * Constructor of the Director class.
     *
     * @param spawn spawner which will spawn the objects for the director.
     */
    public Director(final Spawner spawn) {
        sliceList = new ArrayList<GameSlice>();
        sliceList.add(new GameSlice("Regular", 1, 0, 0.5f, 0, 64f / 50f, 0));
        sliceList.add(new GameSlice("ObstacleCourse", 0, 0, 1, 0, 0, 0));
        state = sliceList.get(0);
        random = new Random();
        this.spawner = spawn;
    }

    /**
     * Method to get the current GameSlice.
     *
     * @return the current GameSlice state.
     */
    public GameSlice getState() {
        return state;
    }

    /**
     * Method to set the current GameSlice.
     *
     * @param index the new index of the GameSlice list.
     */
    public void setState(final int index) {
        if (index < sliceList.size()) {
            state = sliceList.get(index);
        }
    }

    /**
     * Method to direct which objects the spawner needs to spawn. Every direct
     * List can only have one floor and one obstacle at most. There is no
     * randomness yet for the number of obstacles or the place of the obstacles.
     *
     * @return an ArrayList<WorldObject> containing the objects the spawner needs to spawn.
     */
    public ArrayList<WorldObject> direct() {
        ArrayList<WorldObject> nextFloorList = new ArrayList<WorldObject>();
        float xFloor = 0;
        int playerSize = Math.round(spawner.getPlayerSize());
        if (random.nextFloat() < state.getFloorRNG()) {
            if (random.nextFloat() > state.getNoGapFloorRNG()) {
                xFloor = random.nextInt(floorInterval) + playerSize;
            }
            nextFloorList.add(new FloorTile(spawner.getWorldManager().getPhysicsWorld(),
                    new Vector2(xFloor + spawner.getMostRightPos(), state.getYPosFloor())));
        }

        float xObstacle = 0;
        float gapObstacle = 0;
        if (random.nextFloat() < state.getObstaclesRNG()) {
            if (random.nextFloat() > state.getNoGapObstacleRNG()) {
                gapObstacle = obstacleInterval;
            }
            xObstacle = spawner.getMostRightPos() + xFloor + gapObstacle;
            nextFloorList.add(new Obstacle(spawner.getWorldManager().getPhysicsWorld(),
                    new Vector2(xObstacle, state.getYPosObstacles())));
        }
        return nextFloorList;
    }

}
