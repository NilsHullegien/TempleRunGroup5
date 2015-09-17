package com.group5.core.controllers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.WorldObject;

public class Director {
	private State currentState;
	private Random random;
	private float obstaclesRNG = 0.6f;
	private float noGapFloorRNG = 0.5f;
	
	private int floorInterval = 150;
	private Spawner spawner;
	
	public enum State {
		EASY, MEDIUM, HARD
	}
	
	public Director(Spawner spawner){
		currentState = State.EASY;
		random = new Random();
		this.spawner = spawner;
	}
	
	public void setState(State newState) {
		switch(newState) {
		case EASY:	
			obstaclesRNG = 0.5f;
			noGapFloorRNG = 0.5f;
			break;
		case MEDIUM:
			obstaclesRNG = 0.7f;
			noGapFloorRNG = 0.7f;
			break;
		case HARD:
			obstaclesRNG = 0.9f;
			noGapFloorRNG = 0.9f;
			break;
		}
		currentState = newState;
	}
	
	public ArrayList<WorldObject> direct(){
		ArrayList<WorldObject> nextFloorList = new ArrayList<WorldObject>();
		float xFloor = 0;
		if(random.nextFloat() > noGapFloorRNG) {
			xFloor = random.nextInt(floorInterval - 100) + 100; 
		}
		nextFloorList.add(new FloorTile(new Vector2(xFloor + spawner.getLastFloor(), 0)));
		
		float xObstacle = 0;
		float floorWidth = spawner.getFloorSize();
		if(random.nextFloat() < obstaclesRNG) {
			int numObstacles = random.nextInt(2) + 1;
			System.out.println(numObstacles);
			for (int i = 0; i < numObstacles; i++) {
				xObstacle = spawner.getLastFloor() + xFloor + (floorWidth/numObstacles) * i; 
				nextFloorList.add(new Obstacle(new Vector2(xObstacle, 64)));
			}
		}
		return nextFloorList;
	}

}
