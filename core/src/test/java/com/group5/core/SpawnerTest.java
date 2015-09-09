package com.group5.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.group5.core.controllers.Spawner;
import com.group5.core.world.FloorTile;
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
	
	/**
	 * Initialize the variables for the tests.
	 */
	@Before
	public void setup() {
		world = new World();
		spawner = new Spawner(world);
		player = new Player(0, 0);
		world.add(player);
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
		world.add(new FloorTile(100, 100));
		assertTrue(spawner.getLastFloor() == 1124);
	}
	
	/**
	 * Test the last floor method for multiple floortiles.
	 */
	@Test
	public void testGetLastFloorMultiple() {
		world.add(new FloorTile(0, 100));
		world.add(new FloorTile(50, 100));
		world.add(new FloorTile(150, 100));
		assertTrue(spawner.getLastFloor() == 1174);
	}
	
	/**
	 * Test the last floor method for multiple floortiles unordered.
	 */
	@Test
	public void testGetLastFloorMultipleUnordered() {
		world.add(new FloorTile(150, 100));
		world.add(new FloorTile(0, 100));
		world.add(new FloorTile(50, 100));
		assertTrue(spawner.getLastFloor() == 1174);
	}

}
