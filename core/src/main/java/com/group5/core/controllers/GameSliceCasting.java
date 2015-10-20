package com.group5.core.controllers;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.EmptyLevel;
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
     * @param score the current score
     * @return GameSlice
     */
    public static GameSlice cast(final float score, final World world) {
        int withRndscore = Math.min((int) (score * Math.random() * 2), 4);
        switch (withRndscore) {
        case 1: return new OnlyFloorLevel(world);
        case 2: return new EmptyLevel(world);
        case 3: return new OnlyFloorLevel(world);
        case 4: return new OnlyFloorLevel(world);
        default: return new OnlyFloorLevel(world);
        }
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
