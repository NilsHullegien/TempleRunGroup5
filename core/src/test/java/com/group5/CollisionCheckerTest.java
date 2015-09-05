package com.group5;

import com.group5.core.controllers.CollisionChecker;
import com.group5.core.controllers.WorldController;
import com.group5.core.world.FloorTile;
import com.group5.core.world.WorldObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(GdxTestRunner.class)
public class CollisionCheckerTest {

	@Test
	public void overlapTest() {

        WorldController.createWorld();
		WorldController.add(new FloorTile(0, 0));

		assertTrue(CollisionChecker.overlap(WorldController.w.getObjects().get(0), WorldController.w.getObjects().get(0)));

        WorldObject w1 = new FloorTile(0,0);
		WorldObject w2 = new FloorTile(0,0);
		assertTrue(CollisionChecker.overlap(w1, w2));

		w2 = new FloorTile(0,64);

		assertFalse(CollisionChecker.overlap(w1, w2));

		w2 = new FloorTile(1024,0);

		assertFalse(CollisionChecker.overlap(w1, w2));
		
		
	}

}
