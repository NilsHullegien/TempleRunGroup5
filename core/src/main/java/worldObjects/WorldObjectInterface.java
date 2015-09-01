package worldObjects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public interface WorldObjectInterface {
	
	public FileHandle getTexture();
	
	public void updatePos(Vector2 vec);
	
}
