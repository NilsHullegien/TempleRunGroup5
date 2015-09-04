package worldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class FloorTile extends WorldObject{

	private  FileHandle tex;
	
	/**
	 * A floortile is a tile that the player can stand on and jump off of
	 * @param x		Starting x-coordinate
	 * @param y		Starting y-coordinate
	 */
	public FloorTile(float x, float y) {
		super(x, y);
		
		tex = Gdx.files.internal("floorTile.png");
	}

	/**
	 * Update the position of the block with respect to the game speed at a given moment
	 * @param vec		Direction in which to move
	 */
	public void updatePos(Vector2 vec) {
		setX(getX()- vec.x);
		setY(getY() - vec.y);
		
	}
	
	public FileHandle getTexture(){
		return tex;
	}
}
