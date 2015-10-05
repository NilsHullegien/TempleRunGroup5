package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import com.group5.core.world.WorldObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WorldManagerObjectTest {
    private WorldManager worldManager;
    
    private Player p1;
    
    @Before
    public void setup() {
        p1 = new Player(new World(new Vector2(0.f, 0.f), true), new Vector2(5, 10), new Vector2(20, 20));

        worldManager = new WorldManager();
        worldManager.add(p1);
    }

    /**
     * Test the constructor of a worldManager object by using a Player subclass
     */
    @Test
    public void WordObjectConstructorTest() {

        //create a new object and check whether the starting positions are correctly initialised
        assertTrue(worldManager.getObjects().get(0).getX() == p1.getX());
        assertTrue(worldManager.getObjects().get(0).getY() == p1.getY());
    }
    
    @Test
    public void getTextureTest() {
        assertFalse(worldManager.getObjects().get(0).getTexture().equals(null));
    }
    
    @Test
    public void setXTest() {
        assertTrue(p1.getX() == 5);
        assertTrue(worldManager.getObjects().get(0).getX() == p1.getX());
        p1.setX(500);

        assertTrue(p1.getX() == 500);
        assertTrue(worldManager.getObjects().get(0).getX() == p1.getX());
    }
    
    @Test
    public void equalsNullTest() {
        System.out.println("TEST");
        WorldManager w1 = new WorldManager();
        assertFalse(w1.equals(p1));
    }
}
