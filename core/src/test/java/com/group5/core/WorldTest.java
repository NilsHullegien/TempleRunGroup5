package com.group5.core;

import com.group5.core.controllers.Spawner;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the world object.
 */
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
     * Test that an object that is far left from the player disappears on update.
     */
    @Test
    public void testObjectDisappearsWhenTooFarLeft() {
        World w = new World();
        w.setPlayer(new Player(new Vector2(10000.f, 0.f),100,100));

        FloorTile disappearing = new FloorTile(new Vector2(0,0));
        w.add(disappearing);

        w.update(0);

        assertFalse(w.contains(disappearing));
    }

    /**
     * Test that an object that is close to the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenVisible() {
        World w = new World();
        w.setPlayer(new Player(new Vector2(0,0),100,100));

        FloorTile disappearing = new FloorTile(new Vector2(0,0));
        w.add(disappearing);

        w.update(0);

        assertTrue(w.contains(disappearing));
    }

    /**
     * Test that an object that is far right from the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenFarRight() {
        World w = new World();
        w.setPlayer(new Player(new Vector2(0,0),100,100));

        FloorTile disappearing = new FloorTile(new Vector2(10000.f, 0.f));
        w.add(disappearing);

        w.update(0);

        assertTrue(w.contains(disappearing));
    }
    
    /**
     * Test whether getSpawner and setSpawner work the way they should.
     */
    @Test
    public void getAndSetSpawnerTest() {
        Spawner spawn = new Spawner(world);
        assertFalse(world.getSpawner().equals(spawn));
        world.setSpawner(spawn);
        assertTrue(world.getSpawner().equals(spawn));
    }
    
    /**
     * Test whether getJumpTime and SetJumpTime work the way they should.
     */
    @Test
    public void getAndSetJumpTimeTest() {
        //Default value
        assertTrue(world.getJumpTime() == 0L);
        //Change value and verify it actually changed.
        world.setJumpTime(500L);
        assertTrue(world.getJumpTime() == 500L);
    }
    
    /**
     * Test whether getInputProcessor works properly.
     */
    @Test
    public void getInputProcessorTest() {
        InputProcessor ip = Mockito.mock(InputProcessor.class);
        assertFalse(world.getInputProcessor().toString() == null);
    }
    
    /**
     * Test whether getGameStatus works properly.
     */
    @Test
    public void getGameStatusTest() {
        //Set player is already tested.
        world.setPlayer(new Player(new Vector2(0,0), 0, 0));
        
        //By default, gameStatus is true.
        assertTrue(world.getGameStatus());
        
        //Player is now dead and gameStatus should be false.
        world.getPlayer().setY(-1.f);
        assertFalse(world.getGameStatus());
        
        
    }

}
