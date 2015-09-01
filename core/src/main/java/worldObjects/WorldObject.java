package worldObjects;

import com.badlogic.gdx.math.Vector2;

import controller.CollisionChecker;

public abstract class WorldObject implements WorldObjectInterface{

	
	//x coordinate of an object
	private float x;
	
	//y coordinate of an object
	private float y;
	
	
	
	/**
	 * Create an object that can possibly appear on the screen (blocks, enemies, the player)
	 * @param x 	x coordinate of the object
	 * @param y		y coordinate of the object
	 */
	public WorldObject(float x, float y){
		this.setX(x);
		this.setY(y);
	}
	
	
	
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
		
	}
}
