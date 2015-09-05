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
}
