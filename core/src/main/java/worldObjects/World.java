package worldObjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class World {

	
	//gravity of the world
	Vector2 gravity;
	
	//speed of the world (how fast blocks move from right to left
	Vector2 speed;
	
	ArrayList<WorldObject> arr;
	
	/**
	 * A world serves as a container of all object that have been spawned
	 */
	public World(){
		
		//set the gravity
		gravity = new Vector2(0,5);
		
		speed = new Vector2(5,0);
		
		
		arr = new ArrayList<WorldObject>();
	}
	
	public Vector2 getGravity(){
		return gravity;
	}
	
	/**
	 * Add an object to the world
	 * @param obj	Object you want to add to the world
	 */
	public void add(WorldObject obj){
		arr.add(obj);
	}
	
	/**
	 * Update the locations of all floortiles with respect to the game speed
	 */
	public void updatePosFloorTiles(){
		for (int i = 0; i < arr.size(); i++) {
			if(FloorTile.class.isInstance(arr.get(i))){
				arr.get(i).updatePos(speed);
			}
		}
	}
	
	/**
	 * Update the location of the player with respect to the gravity
	 */
	public void updatePosPlayer(){
		for (int i = 0; i < arr.size(); i++) {
			if(Player.class.isInstance(arr.get(i))){
				arr.get(i).updatePos(gravity);
			}
		}
	}
	
	
	public ArrayList<WorldObject> getObjects(){
		return arr;
	}
	
}
