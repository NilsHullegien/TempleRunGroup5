package com.group5.core;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

}