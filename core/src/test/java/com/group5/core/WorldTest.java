package com.group5.core;

import com.group5.core.controllers.Spawner;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the world object.
 */
@RunWith(GdxTestRunner.class)
public class WorldTest {


    private WorldManager world;
    
    private World physicsWorld;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new WorldManager();
    }

    /**
     * Check whether you can correctly insert items into the world
     */
    @Test
    public void addTest() {
        world.add(new FloorTile(physicsWorld, new Vector2(0, 0)));
        assertTrue(world.getObjects().get(0).equals(new FloorTile(physicsWorld, new Vector2(0,0))));
        
        world.add(new FloorTile(physicsWorld, new Vector2(0,1)));
        assertTrue(world.getObjects().get(1).equals(new FloorTile(physicsWorld, new Vector2(0,1))));
        
        world.add(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20)));
        assertTrue(world.getObjects().get(2).equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20))));
    }

    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        world.setPlayer(new Player(physicsWorld, new Vector2(0,0), new Vector2(20, 20)));
        assertTrue(world.getPlayer().equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20, 20))));
        world.setPlayer(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20)));
        assertTrue(world.getPlayer().equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20))));
    }

    /**
     * Test that an object that is far left from the player disappears on update.
     */
    @Test
    public void testObjectDisappearsWhenTooFarLeft() {
        WorldManager w = new WorldManager();

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(0,0));
        w.add(disappearing);

        w.update(0);

        assertFalse(w.contains(disappearing));
    }

    /**
     * Test that an object that is close to the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenVisible() {
        WorldManager w = new WorldManager();

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(0,0));
        w.add(disappearing);

        w.update(0);

        assertTrue(w.contains(disappearing));
    }

    /**
     * Test that an object that is far right from the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenFarRight() {
        WorldManager w = new WorldManager();

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(10000.f, 0.f));
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
        assertTrue(world.getSpawner().equals(spawn));
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
        
        //By default, gameStatus is true.
        assertTrue(world.getGameStatus());
        
        //Player is now dead and gameStatus should be false.
        world.getPlayer().setY(-1.f);
        assertFalse(world.getGameStatus());
        
        
    }

}
