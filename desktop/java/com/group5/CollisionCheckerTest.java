package com.group5;

import com.group5.core.controllers.CollisionChecker;
import com.group5.core.world.FloorTile;
import com.group5.core.world.WorldManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionCheckerTest {

    @Test
    public void overlapTest() {

        WorldManager w = new WorldManager();

        WorldController.add(new FloorTile(0, 0));


        assertTrue(CollisionChecker.overlap(w.getObjects().get(0), w.getObjects().get(0)));

		/*WorldObject w2 = new FloorTile(0,0);
        assertTrue(CollisionChecker.overlap(w1, w2));
		
		w2 = new FloorTile(0,64);
		
		assertFalse(CollisionChecker.overlap(w1, w2));
		
		w2 = new FloorTile(1024,0);
		
		assertFalse(CollisionChecker.overlap(w1, w2));*/


    }

}
