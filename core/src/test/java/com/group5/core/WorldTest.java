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
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the world object.
 */
@RunWith(GdxTestRunner.class)
public class WorldTest {


    private WorldManager world;
    
    private World physicsWorld;
    
    private Player p1;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new WorldManager();
        physicsWorld = world.getPhysicsWorld();
        p1 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        
        world.setPlayer(p1);
    }

    /**
     * Check whether you can correctly insert items into the world
     */
    @Test
    public void addTest() {
        assertTrue(world.getObjects().get(0).equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20))));

        world.add(new FloorTile(physicsWorld, new Vector2(0, 0)));
        assertTrue(world.getObjects().get(1).equals(new FloorTile(physicsWorld, new Vector2(0,0))));
        
        world.add(new FloorTile(physicsWorld, new Vector2(0,1)));
        assertTrue(world.getObjects().get(2).equals(new FloorTile(physicsWorld, new Vector2(0,1))));
        
        
    }

    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        //Since p1 is in the objects by default, we remove it and overwrite it with the same player again. 
        world.getObjects().set(0, null);
        world.setPlayer(p1);
        assertTrue(world.getPlayer().equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20, 20))));
        
        world.setPlayer(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20)));
        assertTrue(world.getPlayer().equals(new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20))));
    }

    /**
     * Test that an object that is close to the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenVisible() {
        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(0,0));
        world.add(disappearing);

        world.update(0);

        assertTrue(world.contains(disappearing));
    }

    /**
     * Test that an object that is far right from the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenFarRight() {

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(10000.f, 0.f));
        world.add(disappearing);

        world.update(0);

        assertTrue(world.contains(disappearing));
    }
    
    /**
     * Test whether getSpawner and setSpawner work the way they should.
     */
    @Test
    public void getSpawnerTest() {
        Spawner spawn = new Spawner(world);
        assertFalse(world.getSpawner().equals(spawn));
    }
    
    /**
     * Test whether getInputProcessor works properly.
     */
    @Test
    public void getInputProcessorTest() {
        InputProcessor ip = Mockito.mock(InputProcessor.class);
        assertFalse(world.getInputProcessor().equals(null));
    }
    
    /**
     * Test whether getGameStatus works properly.
     */
    @Test
    public void getGameStatusTest() {
        //Set player is already tested.
        world.setPlayer(p1);
        //By default, gameStatus is true.
        assertTrue(world.getGameStatus());
        
        //Player is now dead and gameStatus should be false.
        world.getPlayer().setY(-1.f);
        assertFalse(world.getGameStatus());
    }

}
