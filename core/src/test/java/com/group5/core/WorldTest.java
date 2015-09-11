package com.group5.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;

@RunWith(GdxTestRunner.class)
public class WorldTest {
    private World world;
    
    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new World();
    }

    /**
     * Check whether you can correctly insert items into the world
     */
    @Test
    public void addTest() {
        world.add(new FloorTile(new Vector2(0, 0)));
        assertTrue(world.getObjects().get(0).equals(new FloorTile(new Vector2(0,0))));
        
        world.add(new FloorTile(new Vector2(0,1)));
        assertTrue(world.getObjects().get(1).equals(new FloorTile(new Vector2(0,1))));
        
        world.add(new Player(new Vector2(0,1),0,0));
        assertTrue(world.getObjects().get(2).equals(new Player(new Vector2(0, 1), 0, 0)));
        
    }
    
    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        world.setPlayer(new Player(new Vector2(0,0),0,0));
        assertTrue(world.getPlayer().equals(new Player(new Vector2(0,0),0,0)));
        world.setPlayer(new Player(new Vector2(1,0), 0, 0));
        assertTrue(world.getPlayer().equals(new Player(new Vector2(1,0),0,0)));
    }
    
    /**
     * Check whether the update function of the player is correctly executed
     */
    @Test
    public void updateTest(){
        
        world.setPlayer(new Player(new Vector2(0,1),100 ,100));
        world.update(1f);
        assertTrue(Math.abs(world.getPlayer().getY()-1f) < 0.02);
        
    }

}
