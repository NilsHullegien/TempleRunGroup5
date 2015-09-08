package com.group5.core.world;

import com.badlogic.gdx.Gdx;

/**
 * Represents an obstacle for the player to avoid.
 *
 */
public class Obstacle extends WorldObject{

	/**
	 * Constructor of the obstacle.
	 * @param x the x coordinate of the obstacle
	 * @param y the y coordinate of the obstacle
	 */
	public Obstacle(final float x, final float y) {
		super(Gdx.files.internal("obstacle.png"), x, y);
	}

	/**
	 * No implementation
	 */
	@Override
	public void update(float delta, World world) {

	}

	/**
	 * Method to check if two objects are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Obstacle){
			Obstacle that = (Obstacle) obj;
			return((Math.abs(that.getX() - this.getX()) < 0.01f) && (Math.abs(that.getY() - this.getY()) < 0.01f));
		}
		return false;
	}
	
}
