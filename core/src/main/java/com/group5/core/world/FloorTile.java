package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;
import com.group5.core.physics.StaticPhysicsStrategy;

/**
 * Represents a tile of flooring.
 */
public class FloorTile extends WorldObject {

    /**
     * A floortile is a tile that the player can stand on and jump off of.
     *
     * @param physicsWorld the physics world to create the floor tile's body in
     * @param coord        position
     */
    public FloorTile(final World physicsWorld, final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("floorTile.png"),
                new Vector2(EndlessRunner.get().getTextureCache().load("floorTile.png").getWidth() * WorldManager.PHYSICS_SCALE_FACTOR,
                        EndlessRunner.get().getTextureCache().load("floorTile.png").getHeight() * WorldManager.PHYSICS_SCALE_FACTOR)
        );
        setPhysicsStrategy(new StaticPhysicsStrategy(physicsWorld,
                this,
                new Vector2(getTexture().getWidth() * WorldManager.PHYSICS_SCALE_FACTOR,
                        getTexture().getHeight() * WorldManager.PHYSICS_SCALE_FACTOR),
                coord));
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
