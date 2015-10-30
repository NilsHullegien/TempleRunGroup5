package com.group5.core.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.SequencedGameSlice;
import com.group5.core.world.BlockObstacle;
import com.group5.core.world.FloorTile;

/**
 * This is just a temporary class to check if the
 * implementation works, we'll use decorators to
 * make the procedural levels much easier to create.
 */
public class BlockLevel extends SequencedGameSlice {
    /**
     * constructor with previous GameSlice.
     *
     * @param before GameSlice
     * @param world  physicsworld
     */
    public BlockLevel(final SequencedGameSlice before, final World world) {
        super(before, new Vector2(0, 0), new Vector2(1025, 2000));
        createElems(world);
    }

    /**
     * Create elements of this levelelement.
     *
     * @param world physicsworld
     */
    private void createElems(final World world) {
        getElems().add(new FloorTile(world, new Vector2(this.getStartPoint().x / 50, 0)));
        float range = (this.getEndPoint().x - 200 - this.getStartPoint().x) / 50;
        getElems().add(new BlockObstacle(world, new Vector2((float) ((this.getStartPoint().x) / 50 + range * Math.random()), 1.2f)));
    }
}
