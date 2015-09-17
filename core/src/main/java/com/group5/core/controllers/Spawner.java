package com.group5.core.controllers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
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
	 * Max distance in pixels two neighbouring floortiles can be.
	 */
	private float interval;

	/**
	 * The width of the floor texture.
	 */
	private float floorWidth;

	private Director director;

	/**
	 * Constructor of the spawner for the world.
	 * 
	 * @param w
	 *            the world the spawner is created for.
	 */
	public Spawner(final World w) {
		world = w;
		interval = 200;
		floorWidth = 1024;
		director = new Director(this);
	}

	/**
	 * Method for the spawner to find the last floor position.
	 * 
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
	 * 
	 * @return the x coordinate of the player.
	 */
	public float getPlayerPosition() {
		return world.getPlayer().getX();
	}

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
	 * Method to spawn new objects into the world. Objects will be added within
	 * a certain range. The number of obstacles on the added floortiles are
	 * random. As well as the distance between two floortiles.
	 */
	public void spawnBlocks() {
		if (getLastFloor() - getPlayerPosition() < 700) {
			ArrayList<WorldObject> listToAdd = director.direct();
			for(WorldObject w : listToAdd) {
				world.add(w);
			}
		}
	}
}
