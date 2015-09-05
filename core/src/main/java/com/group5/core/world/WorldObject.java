package com.group5.core.world;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public abstract class WorldObject {
	//x coordinate of an object
	private float x;
	
	//y coordinate of an object
	private float y;

    private Texture texture;

	/**
	 * Create an object that can possibly appear on the screen (blocks, enemies, the player)
	 * @param x 	x coordinate of the object
	 * @param y		y coordinate of the object
	 */
	public WorldObject(FileHandle tex, float x, float y){
		this.setX(x);
		this.setY(y);
        this.texture = new Texture(tex);
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

    public Texture getTexture() { return this.texture; }
    public abstract void update(float delta, World world);
}
