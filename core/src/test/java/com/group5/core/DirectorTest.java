package com.group5.core;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.Director;
import com.group5.core.controllers.GameSlice;
import com.group5.core.controllers.Spawner;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;

public class DirectorTest {

    private Director dir;
    private GameSlice slice;
    private Spawner sp;
    private WorldManager worldManager;
    private Player p1;

    @Before
    public void setup() {
        p1 = new Player(new World(new Vector2(25, 0), true), new Vector2(0,0), new Vector2(20, 20));
        worldManager = new WorldManager();
        worldManager.setPlayer(p1);
        sp = new Spawner(worldManager);
        dir = new Director(sp);
        slice = dir.getState();
        
        
    }
    
    /**
     * This test allowes the rnd values used in this method to be negated.
     */
    @Test
    public void directRndFixTest() {
        slice.testingOnlysetFloorRNG(100);
        slice.testingOnlysetNoGapFloorRNG(-100);
        slice.testingOnlysetObstaclesRNG(100);
        slice.testingOnlysetNoGapObstacleRNG(-100);
        dir.direct();
    }
}
