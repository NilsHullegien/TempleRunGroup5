package com.group5.core;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the worldManager object.
 */
@RunWith(GdxTestRunner.class)
public class WorldManagerTest {


    private WorldManager worldManager;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed worldManager here
        this.worldManager = new WorldManager();
    }

    /**
     * Check whether you can correctly insert items into the worldManager
     */
    @Test
    public void addTest() {
        worldManager.add(new FloorTile(new Vector2(0, 0)));
        assertTrue(worldManager.getObjects().get(0).equals(new FloorTile(new Vector2(0,0))));
        
        worldManager.add(new FloorTile(new Vector2(0,1)));
        assertTrue(worldManager.getObjects().get(1).equals(new FloorTile(new Vector2(0,1))));

        worldManager.add(new Player(new Vector2(0,1), new Vector2(0, 0)));
        assertTrue(worldManager.getObjects().get(2).equals(new Player(new Vector2(0, 1), new Vector2(0, 0))));
    }

    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        worldManager.setPlayer(new Player(new Vector2(0, 0), new Vector2(0, 0)));
        assertTrue(worldManager.getPlayer().equals(new Player(new Vector2(0, 0), new Vector2(0, 0))));
        worldManager.setPlayer(new Player(new Vector2(1,0), new Vector2(0, 0)));
        assertTrue(worldManager.getPlayer().equals(new Player(new Vector2(1, 0), new Vector2(0, 0))));
    }

    /**
     * Test that an object that is far left from the player disappears on update.
     */
    @Test
    public void testObjectDisappearsWhenTooFarLeft() {
        WorldManager w = new WorldManager();
        w.setPlayer(new Player(new Vector2(10000.f, 0.f), new Vector2(100, 100)));

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
        WorldManager w = new WorldManager();
        w.setPlayer(new Player(new Vector2(0, 0), new Vector2(100, 100)));

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
        WorldManager w = new WorldManager();
        w.setPlayer(new Player(new Vector2(0, 0), new Vector2(100, 100)));

        FloorTile disappearing = new FloorTile(new Vector2(10000.f, 0.f));
        w.add(disappearing);

        w.update(0);

        assertTrue(w.contains(disappearing));
    }

}
