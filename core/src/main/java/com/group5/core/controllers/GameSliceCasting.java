package com.group5.core.controllers;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.BlockChanceLevel;
import com.group5.core.levels.BlockLevel;
import com.group5.core.levels.HoleBlockLevel;
import com.group5.core.levels.HoleLevel;
import com.group5.core.levels.OnlyFloorLevel;
import com.group5.core.levels.RiseUpLevel;

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
        return new OnlyFloorLevel(world);
    }

    /**
     * Creates a GameSlice.
     *
     * @param score  the current score
     * @param before in the queue before this one.
     * @param world  physicsworld
     * @return GameSlice
     */
    public static GameSlice cast(final float score, final GameSlice before, final World world) {
        int withRndscore = (int) Math.round(score / 100 + Math.random() * 4);
        switch (withRndscore) {
            case 1:
                return new BlockChanceLevel(before, world);
            case 2:
                return new BlockLevel(before, world);
            case 3:
                return new HoleLevel(before, world);
            case 4:
                return new RiseUpLevel(before, world);
            case 5:
                return new HoleBlockLevel(before, world);
            default:
                return new HoleBlockLevel(before, world);
        }
    }
}
