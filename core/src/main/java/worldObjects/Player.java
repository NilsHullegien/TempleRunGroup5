package worldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import controller.CollisionChecker;

public class Player extends WorldObject {

	
	private  FileHandle tex;
	
	/**
	 * The player is the character that is directly controlled by the user
	 * @param x		Starting x-coordinate
	 * @param y		Starting y-coordinate
	 */
	public Player(float x, float y) {
		super(x, y);
		tex = Gdx.files.internal("playerBlock.png");
	}

	
	/**
	 * Update the location of the player with respect a given vector, unless you are colliding with an object
	 * @param vec		Given vector
	 */
	public void updatePos(Vector2 vec){
		if(!(CollisionChecker.checkCollision(this))){
			setX(getX()- vec.x);
			setY(getY() - vec.y);
			
		}
		
	}
	
	public FileHandle getTexture(){
		return tex;
	}
	
	
	
	
}
