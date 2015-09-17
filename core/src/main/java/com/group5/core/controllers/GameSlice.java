package com.group5.core.controllers;
/**
 * GameSlice Class, here all the RNG and all needed variables 
 * for the director for different gameslices. 
 *
 */
public class GameSlice {
	private String state;
	private float floorRNG;
	private float noGapFloorRNG;
	private float obstaclesRNG;
	private float noGapObstacleRNG;
	private float yPosObstacle;
	private float yPosFloor;
	
	/**
	 * Constructor of the GameSlice class.
	 * @param state String of the kind of slice.
	 * @param floorRNG float that gives chance for if a floor spawns.
	 * @param noGapFloorRNG float that gives chance for if there is a gap between floors.
	 * @param obstaclesRNG float that gives chance for if an obstacle spawns.
	 * @param noGapObstacleRNG float that gives chance for if there is a space between obstacles or floors.
	 * @param yPosObstacle float that gives the y Position of the Obstacle.
	 * @param yPosFloor float that gives the y Position of the Floor.
	 */
	public GameSlice(String state, float floorRNG, float noGapFloorRNG, float obstaclesRNG, 
			float noGapObstacleRNG, float yPosObstacle, float yPosFloor) {
		this.state = state;
		this.floorRNG = floorRNG;
		this.noGapFloorRNG = noGapFloorRNG;
		this.obstaclesRNG = obstaclesRNG;
		this.noGapObstacleRNG = noGapObstacleRNG;
		this.yPosObstacle = yPosObstacle;
		this.yPosFloor = yPosFloor;
	}
	
	/**
	 * Method to get the slice.
	 * @return the String of the slice.
	 */
	public String getSlice() {
		return state;
	}
	
	/**
	 * Method to get the floorRNG.
	 * @return the floorRNG as a float.
	 */
	public float getFloorRNG() {
		return floorRNG;
	}
	
	/**
	 * Method to get the noGapfloorRNG.
	 * @return the noGapFloorRNG as a float.
	 */
	public float getNoGapFloorRNG() {
		return noGapFloorRNG;
	}
	
	/**
	 * Method to get the obstaclesRNG.
	 * @return the obstaclesRNG as a float.
	 */
	public float getObstaclesRNG() {
		return obstaclesRNG;
	}
	
	/**
	 * Method to get the noGapobstacleRNG.
	 * @return the noGapObstacleRNG as a float.
	 */
	public float getNoGapObstacleRNG() {
		return noGapObstacleRNG;
	}
	
	/**
	 * Method to get the y position of the obstacle.
	 * @return the y position of the obstacle as a float.
	 */
	public float getYPosObstacles() {
		return yPosObstacle;
	}
	
	/**
	 * Method to get the y position of the floor.
	 * @return the y position of the floor as a float.
	 */
	public float getYPosFloor() {
		return yPosFloor;
	}
}
