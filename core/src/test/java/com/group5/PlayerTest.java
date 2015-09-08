package com.group5;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

	/**
	 * Test whether the equals method works properly
	 */
	@Test
	public void equalsTest() {
		
		//a player is the same as itself
		Player p1 = new Player(0,0);
		assertTrue(p1.equals(p1));
		
		//a player is the same as another player with the same properties
		Player p2 = new Player(0,0);
		assertTrue(p1.equals(p2));
		
		//a player is not the same as a player with different player
		Player p3 = new Player(1,0);
		assertFalse(p1.equals(p3));
		Player p4 = new Player(0,1);
		assertFalse(p1.equals(p4));
		
		//a player is never the same this as an object of a different type
		FloorTile f = new FloorTile(0,0);
		assertFalse(p1.equals(f));
		
	}

}
