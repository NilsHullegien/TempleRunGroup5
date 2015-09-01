package controller;

import worldObjects.World;
import worldObjects.WorldObject;

/**
 * This class ensures that the model is always updated and responds correctly to user input
 * @author Marcel
 *
 */
public class WorldController {

	
	private static World w;
	
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
