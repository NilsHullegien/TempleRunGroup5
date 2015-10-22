package com.group5.core.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.GameSlice;

/**
 * This is the factory for the OnlyFloorLevel class.
 * @author Marcel
 *
 */
public class OnlyFloorLevelFactory extends LevelFactory {

    @Override
    public GameSlice produceLevel(final World world) {
        return new OnlyFloorLevel(world);
    }

    @Override
    public GameSlice produceLevel(final GameSlice before, final World world) {
        return new OnlyFloorLevel(before, world);
    }
}
