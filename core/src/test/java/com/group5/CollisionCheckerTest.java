package com.group5;

import com.group5.core.controllers.CollisionChecker;
import com.group5.core.world.FloorTile;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(GdxTestRunner.class)
public class CollisionCheckerTest {
    private World world;
    private CollisionChecker checker;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new World();
        this.checker = world.getCollider();
    }

	@Test
	public void overlapTest() {
		world.add(new FloorTile(0, 0));

		assertTrue(checker.overlap(world.getObjects().get(0), world.getObjects().get(0)));

        WorldObject w1 = new FloorTile(0,0);
		WorldObject w2 = new FloorTile(0,0);
		assertTrue(checker.overlap(w1, w2));

        w2.setY(64);

		assertFalse(checker.overlap(w1, w2));

        w2.setX(1024);
        w2.setY(0);

		assertFalse(checker.overlap(w1, w2));

	}

}
