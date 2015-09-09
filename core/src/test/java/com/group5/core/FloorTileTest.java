package com.group5.core;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class FloorTileTest {

    /**
     * Test whether the equals method works properly
     */
    @Test
    public void equalsTest() {

        //a floortile is the same as itself
        FloorTile f1 = new FloorTile(0, 0);
        assertTrue(f1.equals(f1));

        //a floortile is the same as another floortile with the same properties
        FloorTile f2 = new FloorTile(0, 0);
        assertTrue(f1.equals(f2));

        //a floortile is not the same as a floortile with different properties
        FloorTile f3 = new FloorTile(1, 0);
        assertFalse(f1.equals(f3));
        FloorTile f4 = new FloorTile(0, 1);
        assertFalse(f1.equals(f4));

        //a floortile is never the same this as an object of a different type
        Player p = new Player(0, 0);
        assertFalse(f1.equals(p));

    }

}
