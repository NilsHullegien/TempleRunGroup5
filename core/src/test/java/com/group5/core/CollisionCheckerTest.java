package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    /**
     * Test whether the overlap method correctly recognises whether two objects overlap or not
     */
    @Test
    public void overlapTest() {
        world.add(new FloorTile(new Vector2(0, 0)));

        //an object overlaps with itself
        assertTrue(checker.overlap(world.getObjects().get(0), world.getObjects().get(0)));

        //two objects with the same coordinates overlap
        WorldObject w1 = new FloorTile(new Vector2(0, 0));
        WorldObject w2 = new FloorTile(new Vector2(0, 0));
        assertTrue(checker.overlap(w1, w2));

        w2.setY(64);
        //the boundaries touch on the bottom side, but they do not overlap
        assertFalse(checker.overlap(w1, w2));

        w2.setX(1024);
        w2.setY(0);

        // same thing for the right side
        assertFalse(checker.overlap(w1, w2));
        checker.yBounce(w2, 1000);

    }

    /**
     * Test whether the checkCollision method correctly recognises when an object collides with another object
     */
    @Test
    public void checkCollisionTest(){
    	world.add(new FloorTile(new Vector2(0, 0)));
    	//only one object, so no collision
    	assertFalse(checker.checkCollision(world.getObjects().get(0)));
 
    	//two objects, but they don't touch
    	world.add(new FloorTile(new Vector2(0, 64)));
       	assertFalse(checker.checkCollision(world.getObjects().get(0)));
       	assertFalse(checker.checkCollision(world.getObjects().get(1)));
        
    	world.add(new FloorTile(new Vector2(0, 0)));
    	//two objects that overlap, so there will be a collision
    	assertTrue(checker.checkCollision(world.getObjects().get(0)));
    	assertTrue(checker.checkCollision(world.getObjects().get(2)));

        checker.xBounce(new FloorTile(new Vector2(0, 0)));
    }


}
