package com.group5;

import static org.junit.Assert.*;

import org.junit.Test;

import com.group5.core.world.FloorTile;
import com.group5.core.world.World;

import com.group5.core.controllers.CollisionChecker;
import com.group5.core.controllers.WorldController;

public class CollisionCheckerTest {

	@Test
	public void overlapTest() {
		
		World w = new World();
		
		WorldController.add(new FloorTile(0,0));
		
		
		assertTrue(CollisionChecker.overlap(w.getObjects().get(0), w.getObjects().get(0)));
		
		/*WorldObject w2 = new FloorTile(0,0);
		assertTrue(CollisionChecker.overlap(w1, w2));
		
		w2 = new FloorTile(0,64);
		
		assertFalse(CollisionChecker.overlap(w1, w2));
		
		w2 = new FloorTile(1024,0);
		
		assertFalse(CollisionChecker.overlap(w1, w2));*/
		
		
	}

}
