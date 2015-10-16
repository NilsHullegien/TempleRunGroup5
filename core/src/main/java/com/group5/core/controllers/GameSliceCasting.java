package com.group5.core.controllers;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.OnlyFloorLevel;

/**
 * Handles assigning the right gameslice to the current status of gameplay.
 *
 * @author Levi
 */
public abstract class GameSliceCasting {
/*
 * For now it's all very basic, we'll be using decorators to create levels in
 * the near future.
 */

    /**
     * Creates a GameSlice.
     *
     * @param world physicsworld
     * @return GameSlice
     */
    public static GameSlice cast(final World world) {
        return new OnlyFloorLevel(world);
    }

    /**
     * Creates a GameSlice.
     *
     * @param before in the queue before this one.
     * @param world  physicsworld
     * @return GameSlice
     */
    public static GameSlice cast(final GameSlice before, final World world) {
        return new OnlyFloorLevel(before, world);
    }
}
