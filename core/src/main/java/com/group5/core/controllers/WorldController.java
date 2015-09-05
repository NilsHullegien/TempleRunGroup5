package com.group5.core.controllers;

import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

/**
 * This class ensures that the model is always updated and responds correctly to user input
 * @author Marcel
 *
 */
public class WorldController {

	
	public static World w;
	
	/**
	 * Create a new empty world
	 */
	public static void createWorld(){
		w = new World();
	}
	
	
	/**
	 * Add an object to the world
	 * @param obj	Object that you want to add
	 */
	public static void add(WorldObject obj){
		w.add(obj);
	}
	
	
	public static World getWorld(){
		return w;
	}
	
	
	
	
}
