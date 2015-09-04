package controller;

import com.badlogic.gdx.graphics.Texture;

import worldObjects.WorldObject;

public class CollisionChecker {

	/**
	 * Checks if an object collides with any object in the game
	 * @param w		Object which needs to be checked
	 * @return		True when the object is colliding with another object
	 */
	public static boolean checkCollision(WorldObject w){
		for(WorldObject obj: WorldController.getWorld().getObjects()){
			if(!(obj.equals(w)) && overlap(w,obj)){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Check if two objects overlap
	 * @param w1	First object
	 * @param w2	Second object
	 * @return		True if the two objects overlap
	 */
	public static boolean overlap(WorldObject w1, WorldObject w2){
		Texture t1 = new Texture(w1.getTexture());
		Texture t2 = new Texture(w2.getTexture());
		if (w1.getX() < w2.getX() + t2.getWidth() && w1.getY() < w2.getY() + t2.getHeight() && w1.getY() + t1.getHeight() > w2.getY())
            return true;
        else
            return false;
	}
	
	
}
