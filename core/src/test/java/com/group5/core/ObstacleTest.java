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

/**
 * Test class for the Obstacle class.
 */
public class ObstacleTest {
    private World physicsWorld;
    
    private Obstacle obstacle;

    private WorldManager world;
    
    @Before
    public void setUp() {
        world = new WorldManager();
        
        physicsWorld = world.getPhysicsWorld();
        
        obstacle = Mockito.mock(Obstacle.class);
    }

    /**
     * Test method to check the equals for the same object.
     */
    @Test
    public void testEqualsSame() {
        assertTrue(obstacle.equals(obstacle));
    }

    /**
     * Test method to check the equals for two different objects with different properties.
     */
    @Test
    public void testEqualsDif() {
        Obstacle dif = new Obstacle(physicsWorld, new Vector2(0, 10));
        assertFalse(obstacle.equals(dif));
    }

    /**
     * Test method to check the equals for the null check.
     */
    @Test
    public void testEqualsNull() {
        assertFalse(obstacle.equals(null));
    }
    
	@Test
	public void updateTest() {
	    //Verify nothing happens.
	    Mockito.verify(obstacle, Mockito.never()).update(0, world);
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
