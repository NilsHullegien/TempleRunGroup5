package com.group5.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;

@RunWith(GdxTestRunner.class)
public class FloorTileTest {
    private World physicsWorld;

    private FloorTile f1;

    @Before
    public void setUp() {
        physicsWorld = new World(new Vector2(0.f, 0.f), true);
        f1 = new FloorTile(physicsWorld, new Vector2(0, 0));
    }

    @After
    public void tearDown() {
        f1 = null;
    }

    /**
     * Test whether the equals method works properly
     */
    @Test
    public void equalsTest() {

        //a floortile is the same as itself
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
        f1 = new FloorTile(physicsWorld, new Vector2(0, 0));
        FloorTile f2 = new FloorTile(physicsWorld, new Vector2(99, 99));
        assertFalse(f1.equals(f2));
        assertFalse(f1.hashCode() == f2.hashCode());
        assertTrue(f1.hashCode() == f1.hashCode()); //Obvious.
    }

    @Test
    public void getTextureTest() {
        Texture t1 = f1.getTexture();
        assertFalse(t1 == null);
    }

    @Test
    public void getPositionTest() {
        assertEquals(f1.getPosition(), new Vector2(0.0f, 0.0f));
    }

    @Test
    public void getWidthTest() {
        assertTrue(f1.getWidth() == 20.48f);
    }

    @Test
    public void getHeightTest() {
        assertTrue(f1.getHeight() == 1.28f);
    }
}
