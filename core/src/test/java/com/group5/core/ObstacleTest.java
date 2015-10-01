package com.group5.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.Obstacle;
import com.group5.core.world.WorldManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Obstacle class.
 */
public class ObstacleTest {
    private World physicsWorld;

    @Before
    public void setUp() {
        physicsWorld = new World(new Vector2(0.f, 0.f), true);
    }

    /**
     * Test method to check the equals for the same object.
     */
    @Test
    public void testEqualsSame() {
        Obstacle obstacle = new Obstacle(physicsWorld, new Vector2(0, 0));
        assertTrue(obstacle.equals(obstacle));
    }

    /**
     * Test method to check the equals for two different objects with different properties.
     */
    @Test
    public void testEqualsDif() {
        Obstacle obstacle = new Obstacle(physicsWorld, new Vector2(0, 0));
        Obstacle dif = new Obstacle(physicsWorld, new Vector2(0, 10));
        assertFalse(obstacle.equals(dif));
    }

    /**
     * Test method to check the equals for the null check.
     */
    @Test
    public void testEqualsNull() {
        Obstacle obstacle = new Obstacle(physicsWorld, new Vector2(0, 0));
        assertFalse(obstacle.equals(null));
    }
    
	@Test
	public void updateTest() {
	    Obstacle obstacle = new Obstacle(physicsWorld, new Vector2(0,0));
	    
	    //Verify nothing happens.
	    assertTrue(obstacle.getX() == 0);
	    assertTrue(obstacle.getY() == 0);
	}
    /**
     * Test method to check two different instances with the same properties.
     */
    @Test
    public void testEqualsSameProperties() {
        Obstacle obstacle = new Obstacle(physicsWorld, new Vector2(0, 0));
        Obstacle sameProp = new Obstacle(physicsWorld, new Vector2(0, 0));
        assertTrue(obstacle.equals(sameProp));
    }

}
