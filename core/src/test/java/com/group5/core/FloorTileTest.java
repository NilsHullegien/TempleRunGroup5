package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class FloorTileTest {
    private World physicsWorld;

    @Before
    public void setUp() {
        physicsWorld = new World(new Vector2(0.f, 0.f), true);
    }

    /**
     * Test whether the equals method works properly
     */
    @Test
    public void equalsTest() {

        //a floortile is the same as itself
        FloorTile f1 = new FloorTile(physicsWorld, new Vector2(0, 0));
        assertTrue(f1.equals(f1));

        //a floortile is the same as another floortile with the same properties
        FloorTile f2 = new FloorTile(physicsWorld, new Vector2(0, 0));
        assertTrue(f1.equals(f2));

        //a floortile is not the same as a floortile with different properties
        FloorTile f3 = new FloorTile(physicsWorld, new Vector2(1, 0));
        assertFalse(f1.equals(f3));
        FloorTile f4 = new FloorTile(physicsWorld, new Vector2(0, 1));
        assertFalse(f1.equals(f4));

        //a floortile is never the same this as an object of a different type
        Player p = new Player(physicsWorld, new Vector2(20, 20), new Vector2(20, 20));
        assertFalse(f1.equals(p));

    }
    
    @Test
    public void hashCodeTest() {
        FloorTile f1 = new FloorTile(physicsWorld, new Vector2(0,0));
        FloorTile f2 = new FloorTile(physicsWorld, new Vector2(0,0));
        
        assertTrue(f1.hashCode() == f2.hashCode());
    }

}
