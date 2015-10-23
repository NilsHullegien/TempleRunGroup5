package com.group5.core.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.GameSlice;

/**
 * Abstract factory for different types of levels.
 * @author Marcel
 *
 */
public abstract class LevelFactory {

    /**
     * Create a new game slice.
     *
     * @param world physicsworld
     * @return created level
     */
    public abstract GameSlice produceLevel(final World world);

    /**
     * Create a new game slice.
     *
     * @param before GameSlice
     * @param world  physicsworld
     * @return created level
     */
    public abstract GameSlice produceLevel(final GameSlice before, final World world);

}
