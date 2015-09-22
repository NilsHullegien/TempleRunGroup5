package com.group5.core;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.Obstacle;
import com.group5.core.world.World;

/**
 * Test class for the Obstacle class. 
 *
 */
public class ObstacleTest {

    private World world = Mockito.mock(World.class);
    
	/**
	 * Test method to check the equals for the same object.
	 */
	@Test
	public void testEqualsSame() {
		Obstacle obstacle = new Obstacle(new Vector2(0, 0));
		assertTrue(obstacle.equals(obstacle));
	}
	
	/**
	 * Test method to check the equals for two different objects with different properties.
	 */
	@Test
	public void testEqualsDif() {
		Obstacle obstacle = new Obstacle(new Vector2(0, 0));
		Obstacle dif = new Obstacle(new Vector2(0, 10));
		assertFalse(obstacle.equals(dif));
	}
	
	/**
	 * Test method to check the equals for the null check.
	 */
	@Test
	public void testEqualsNull() {
		Obstacle obstacle = new Obstacle(new Vector2(0, 0));
		assertFalse(obstacle.equals(null));
	}
	
	/**
	 * Test method to check two different instances with the same properties.
	 */
	@Test
	public void testEqualsSameProperties() {
		Obstacle obstacle = new Obstacle(new Vector2(0, 0));
		Obstacle sameProp = new Obstacle(new Vector2(0, 0));
		assertTrue(obstacle.equals(sameProp));
	}
	
	@Test
	public void updateTest() {
	    Obstacle obstacle = new Obstacle(new Vector2(0,0));
	    obstacle.update(500, world);
	    
	    //Verify nothing happens.
	    assertTrue(obstacle.getX() == 0);
	    assertTrue(obstacle.getY() == 0);
	}

}
