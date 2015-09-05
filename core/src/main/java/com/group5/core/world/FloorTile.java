package com.group5.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class FloorTile extends WorldObject{
	
	/**
	 * A floortile is a tile that the player can stand on and jump off of
	 * @param x		Starting x-coordinate
	 * @param y		Starting y-coordinate
	 */
	public FloorTile(float x, float y) {
		super(Gdx.files.internal("floorTile.png"), x, y);
	}

    @Override
    public void update(float delta, World w) {

    }
}
