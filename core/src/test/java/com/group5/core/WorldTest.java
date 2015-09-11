package com.group5.core;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the world object.
 */
public class WorldTest {

    /**
     * Test that an object that is far left from the player disappears on update.
     */
    @Test
    public void testObjectDisappearsWhenTooFarLeft() {
        World w = new World();
        w.setPlayer(new Player(10000.f, 0.f));

        FloorTile disappearing = new FloorTile(0.f, 0.f);
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
        w.setPlayer(new Player(0.f, 0.f));

        FloorTile disappearing = new FloorTile(0.f, 0.f);
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
        w.setPlayer(new Player(0.f, 0.f));

        FloorTile disappearing = new FloorTile(10000.f, 0.f);
        w.add(disappearing);

        w.update(0);

        assertTrue(w.contains(disappearing));
    }

}
