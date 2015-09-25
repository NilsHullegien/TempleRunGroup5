package com.group5.core.controllers;

/**
 * GameSlice Class, here all the RNG and all needed variables for the director
 * for different gameslices.
 */
public class GameSlice {

    /**
     * The name of the GameSlice.
     */
    private String state;

    /**
     * The probability that there will spawn a floor.
     */
    private float floorRNG;

    /**
     * The probability that there will be no gap between floors.
     */
    private float noGapFloorRNG;

    /**
     * The probability that there will spawn an obstacle.
     */
    private float obstaclesRNG;

    /**
     * The probability that there will be a gap between obstacles.
     */
    private float noGapObstacleRNG;

    /**
     * The y Position of the obstacles.
     */
    private float yPosObstacle;

    /**
     * The y Position of the floors.
     */
    private float yPosFloor;

    /**
     * Constructor of the GameSlice class.
     *
     * @param slice               String of the kind of slice.
     * @param floorNumber         float that gives chance for if a floor spawns.
     * @param noGapFloorNumber    float that gives chance for if there is a gap between floors.
     * @param obstaclesNumber     float that gives chance for if an obstacle spawns.
     * @param noGapObstacleNumber float that gives chance for if there is a space between obstacles or floors.
     * @param yPositionObstacle   float that gives the y Position of the Obstacle.
     * @param yPositionFloor      float that gives the y Position of the Floor.
     */
    public GameSlice(final String slice, final float floorNumber, final float noGapFloorNumber, final float obstaclesNumber,
                     final float noGapObstacleNumber, final float yPositionObstacle, final float yPositionFloor) {
        this.state = slice;
        this.floorRNG = floorNumber;
        this.noGapFloorRNG = noGapFloorNumber;
        this.obstaclesRNG = obstaclesNumber;
        this.noGapObstacleRNG = noGapObstacleNumber;
        this.yPosObstacle = yPositionObstacle;
        this.yPosFloor = yPositionFloor;
    }

    /**
     * Method to get the slice.
     *
     * @return the String of the slice.
     */
    public String getSlice() {
        return state;
    }

    /**
     * Method to get the floorRNG.
     *
     * @return the floorRNG as a float.
     */
    public float getFloorRNG() {
        return floorRNG;
    }

    /**
     * Method to get the noGapfloorRNG.
     *
     * @return the noGapFloorRNG as a float.
     */
    public float getNoGapFloorRNG() {
        return noGapFloorRNG;
    }

    /**
     * Method to get the obstaclesRNG.
     *
     * @return the obstaclesRNG as a float.
     */
    public float getObstaclesRNG() {
        return obstaclesRNG;
    }

    /**
     * Method to get the noGapobstacleRNG.
     *
     * @return the noGapObstacleRNG as a float.
     */
    public float getNoGapObstacleRNG() {
        return noGapObstacleRNG;
    }

    /**
     * Method to get the y position of the obstacle.
     *
     * @return the y position of the obstacle as a float.
     */
    public float getYPosObstacles() {
        return yPosObstacle;
    }

    /**
     * Method to get the y position of the floor.
     *
     * @return the y position of the floor as a float.
     */
    public float getYPosFloor() {
        return yPosFloor;
    }
}
