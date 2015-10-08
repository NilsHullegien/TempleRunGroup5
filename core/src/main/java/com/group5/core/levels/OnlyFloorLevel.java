package com.group5.core.levels;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.GameSlice;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.WorldObject;

public class OnlyFloorLevel extends GameSlice {
    public OnlyFloorLevel (World world) {
        super(new Vector2(0, 0), new Vector2(1025, 2000));
        createElems(world);
    }
    
    public OnlyFloorLevel (GameSlice before, World world) {
        super(before, new Vector2(0, 0), new Vector2(1025, 2000));
        createElems(world);
    }
    
    private void createElems(World world) {
        elems.add(new FloorTile(world, new Vector2(this.startPoint.x/50, 0)));
        elems.add(new Obstacle(world, new Vector2((this.endPoint.x/50), 1)));
    }
}
