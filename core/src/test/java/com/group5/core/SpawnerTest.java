package com.group5.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.Director;
import com.group5.core.controllers.Spawner;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.Player;
import com.group5.core.world.World;

/**
 * Spawner Test class. 
 *
 */
public class SpawnerTest {
	
	private World world;
	
	private Spawner spawner;
	
	private Player player;
	
	private Director director;
	
	/**
	 * Initialize the variables for the tests.
	 */
	@Before
	public void setup() {
		world = new World();
		spawner = new Spawner(world);
		player = new Player(new Vector2(0, 0), new Vector2(0, 0));
		world.setPlayer(player);
		director = spawner.getDirector();
		director.setState(0);
	}

	/**
	 * Test the player position.
	 */
	@Test
	public void testGetPlayerPos() {
		assertTrue(spawner.getPlayerPosition() == 0);
	}
	
	/**
	 * Test if the last floor is null it returns 0.
	 */
	@Test
	public void testGetLastFloorNull() {
		assertTrue(spawner.getLastFloor() == 0);
	}
	
	/**
	 * Test the last floor method for one floortile.
	 */
	@Test
	public void testGetLastFloor() {
		world.add(new FloorTile(new Vector2(100, 100)));
		assertTrue(spawner.getLastFloor() == 1124);
	}
	
	/**
	 * Test the last floor method for multiple floortiles.
	 */
	@Test
	public void testGetLastFloorMultiple() {
		world.add(new FloorTile(new Vector2(0, 100)));
		world.add(new FloorTile(new Vector2(50, 100)));
		world.add(new FloorTile(new Vector2(150, 100)));
		assertTrue(spawner.getLastFloor() == 1174);
	}
	
	/**
	 * Test the last floor method for multiple floortiles unordered.
	 */
	@Test
	public void testGetLastFloorMultipleUnordered() {
		world.add(new FloorTile(new Vector2(150, 100)));
		world.add(new FloorTile(new Vector2(0, 100)));
		world.add(new FloorTile(new Vector2(50, 100)));
		assertTrue(spawner.getLastFloor() == 1174);
	}
	
	/**
	 * Test the getFloor method to get the size of the floor.
	 */
	@Test
	public void testGetFloorSize() {
		world.add(new FloorTile(new Vector2(100, 200)));
		assertTrue(spawner.getFloorSize() == 1024);
	}
	
	/**
	 * Test the last obstacle method for one obstacle.
	 */
	@Test
	public void testGetLastObstacle() {
		world.add(new Obstacle(new Vector2(0, 100)));
		assertTrue(spawner.getLastObstacle() == 100);
	}
	
	/**
	 * Test the last obstacle method for multiple obstacles.
	 */
	@Test
	public void testGetLastObstacleMultiple() {
		world.add(new Obstacle(new Vector2(0, 100)));
		world.add(new Obstacle(new Vector2(100, 100)));
		world.add(new Obstacle(new Vector2(200, 100)));
		assertTrue(spawner.getLastObstacle() == 300);
	}
	
	/**
	 * Test the last obstacle method for multiple obstacles unordered.
	 */
	@Test
	public void testGetLastObstacleMultipleUnordered() {
		world.add(new Obstacle(new Vector2(100, 100)));
		world.add(new Obstacle(new Vector2(200, 100)));
		world.add(new Obstacle(new Vector2(0, 100)));
		assertTrue(spawner.getLastObstacle() == 300);
	}
	
	/**
	 * Test the getMostRightPos method for the floortile being most right.
	 */
	@Test
	public void testGetMostRightPos() {
		world.add(new FloorTile(new Vector2(100, 100)));
		world.add(new Obstacle(new Vector2(200, 200)));
		assertTrue(spawner.getMostRightPos() == 1124);
	}
	
	/**
	 * Test the getMostRightPos method for the floortile being most 
	 * right while on an ObstacleCourse.
	 */
	@Test
	public void testGetMostRightPosObstacleCourse() {
		director.setState(1);
		world.add(new FloorTile(new Vector2(100, 100)));
		world.add(new Obstacle(new Vector2(200, 200)));
		assertTrue(spawner.getMostRightPos() == 1124);
	}
	
	/**
	 * Test the getMostRightPos method for the obstacle being most 
	 * right while on an ObstacleCourse.
	 */
	@Test
	public void testGetMostRightPosObstacleCourseFurthest() {
		director.setState(1);
		world.add(new FloorTile(new Vector2(100, 100)));
		world.add(new Obstacle(new Vector2(2000, 200)));
		assertTrue(spawner.getMostRightPos() == 2100);
	}
	

}
