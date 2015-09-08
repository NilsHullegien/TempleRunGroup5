package com.group5.core.world;

import com.badlogic.gdx.Gdx;

/**
 * Represents a tile of flooring.
 */
public class FloorTile extends WorldObject {

    /**
     * A floortile is a tile that the player can stand on and jump off of.
     *
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     */
    public FloorTile(final float x, final float y) {
        super(Gdx.files.internal("floorTile.png"), x, y);
    }

    @Override
    public void update(final float delta, final World w) {

    }
    
    /**
     * Check whether an object is the same object as this instance
     */
    @Override
	public boolean equals(Object obj) {
    	if(obj == null){
    		return false;
    	}
		if(!(obj instanceof FloorTile))
			return false;
		FloorTile that = (FloorTile)obj;
		return((Math.abs(that.getX() - this.getX()) < 0.01f) && (Math.abs(that.getY() - this.getY()) < 0.01f));
	}
    
}
