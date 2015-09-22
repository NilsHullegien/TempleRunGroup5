package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;

/**
 * Represents a tile of flooring.
 */
public class FloorTile extends WorldObject {

    /**
     * A floortile is a tile that the player can stand on and jump off of.
     *
     * @param coord position
     */
    public FloorTile(final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("floorTile.png"), coord);
    }

    @Override
    public void update(final float delta, final WorldManager w) {

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash * super.hashCode();
        return hash;
    }

    /**
     * Check whether an object is the same object as this instance.
     */
    @Override

    public boolean equals(final Object obj) {
        return obj instanceof FloorTile && super.equals(obj);
    }
}
