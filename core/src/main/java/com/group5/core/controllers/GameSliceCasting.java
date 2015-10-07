package com.group5.core.controllers;

import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.levels.OneBlockLevel;
import com.group5.core.levels.OnlyFloorLevel;

public abstract class GameSliceCasting {

    public static GameSlice cast(World world) {
        return new OnlyFloorLevel(world);
    }
    
    public static GameSlice cast(GameSlice before, World world) {
        return new OnlyFloorLevel(before, world);
    }
}
