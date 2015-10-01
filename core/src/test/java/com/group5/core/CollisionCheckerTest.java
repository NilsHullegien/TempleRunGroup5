//package com.group5.core;
//
//import com.badlogic.gdx.math.Vector2;
//import com.group5.core.controllers.CollisionChecker;
//import com.group5.core.world.FloorTile;
//import com.group5.core.world.Player;
//import com.group5.core.world.WorldManager;
//import com.group5.core.world.WorldObject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(GdxTestRunner.class)
//public class CollisionCheckerTest {
//    private WorldManager world;
//    private CollisionChecker checker;
//
//    @Before
//    public void setUp() {
//        // TODO: Probably should use a stubbed world here
//        this.world = new WorldManager();
//        this.checker = world.getCollider();
//    }
//
//    /**
//     * Test whether the overlap method correctly recognises whether two objects overlap or not
//     */
//    @Test
//    public void overlapTest() {
//        world.add(new FloorTile(new Vector2(0, 0)));
//
//        //an object overlaps with itself
//        assertTrue(checker.overlap(world.getObjects().get(0), world.getObjects().get(0)));
//
//        //two objects with the same coordinates overlap
//        WorldObject w1 = new FloorTile(new Vector2(0, 0));
//        WorldObject w2 = new FloorTile(new Vector2(1, 0));
//        assertTrue(checker.overlap(w1, w2));
//
//        w2.setY(64);
//        //the boundaries touch on the bottom side, but they do not overlap
//        assertFalse(checker.overlap(w1, w2));
//
//        w2.setX(1024);
//        w2.setY(0);
//
//        // same thing for the right side
//        assertFalse(checker.overlap(w1, w2));
//        checker.yBounce(w2, 1000);
//
//    }
//
//    /**
//     * Test whether the checkCollision method correctly recognises when an object collides with another object
//     */
//    @Test
//    public void checkCollisionTest(){
//    	world.add(new FloorTile(new Vector2(0, 0)));
//    	//only one object, so no collision
//    	assertFalse(checker.checkCollision(world.getObjects().get(0)));
// 
//    	//two objects, but they don't touch
//    	world.add(new FloorTile(new Vector2(0, 64)));
//       	assertFalse(checker.checkCollision(world.getObjects().get(0)));
//       	assertFalse(checker.checkCollision(world.getObjects().get(1)));
//        
//    	world.add(new FloorTile(new Vector2(0, 0)));
//    	//two objects that overlap, so there will be a collision
//    	assertTrue(checker.checkCollision(world.getObjects().get(0)));
//    	assertTrue(checker.checkCollision(world.getObjects().get(2)));
//
//        checker.xBounce(new FloorTile(new Vector2(0, 0)));
//    }
//    
//    /**
//     * Test whether overLapLeft works in case of true and false.
//     */
//    @Test
//    public void overLapLeftTFTest() {
//        WorldObject w1 = new FloorTile(new Vector2(0, 0));
//        WorldObject w2 = new FloorTile(new Vector2(1, 0));
//        WorldObject w3 = new FloorTile(new Vector2(2000, 0));
//
//        //W1 should overlap itself
//        assertTrue(checker.overlapLeft(w1, w1));
//        
//        //W1 should overlap W2
//        assertTrue(checker.overlapLeft(w1, w2));
//        
//        //W1 shouldn't overlap W3
//        assertFalse(checker.overlapLeft(w1, w3));
//    }
//    
//    /**
//     * Test whether overLapTop works in case of true and false.
//     */
//    @Test
//    public void overLapTopTFTest() {
//        WorldObject w1 = new FloorTile(new Vector2(0, 0));
//        WorldObject w2 = new FloorTile(new Vector2(0, 1));
//        WorldObject w3 = new FloorTile(new Vector2(0, -2000));
//        
//        //W1 should overlap itself
//        assertTrue(checker.overlapTop(w1, w1));
//        
//        //W1 should overlap W2
//        assertTrue(checker.overlapTop(w1, w2));
//        
//        //W1 shouldn't overlap W3
//        assertFalse(checker.overlapTop(w1, w3));
//    }
//    
//    /**
//     * Test whether overLapRight works in case of true and false.
//     */
//    @Test
//    public void overLapRightTFTest() {
//        WorldObject w1 = new FloorTile(new Vector2(0, 0));
//        WorldObject w2 = new FloorTile(new Vector2(1, 0));
//        WorldObject w3 = new FloorTile(new Vector2(-2000, 0));
//
//        //W1 should overlap itself
//        assertTrue(checker.overlapRight(w1, w1));
//        
//        //W1 should overlap W2
//        assertTrue(checker.overlapRight(w1, w2));
//        
//        //W1 shouldn't overlap W3
//        assertFalse(checker.overlapRight(w1, w3));
//    }
//    
//    /**
//     * Test whether overLapTop works in case of true and false.
//     */
//    @Test
//    public void overLapBottomTFTest() {
//        WorldObject w1 = new FloorTile(new Vector2(0, 0));
//        WorldObject w2 = new FloorTile(new Vector2(0, 1));
//        WorldObject w3 = new FloorTile(new Vector2(0, 2000));
//        
//        //W1 should overlap itself
//        assertTrue(checker.overlapBottom(w1, w1));
//        
//        //W1 should overlap W2
//        assertTrue(checker.overlapBottom(w1, w2));
//        
//        //W1 shouldn't overlap W3
//        assertFalse(checker.overlapBottom(w1, w3));
//    }
//    
//    /**
//     * Test for xbounce method
//     * Needs to be done with bounding boxes
//     */
//    @Test
//    public void xBounceTest() {
//        WorldObject w1Default = new FloorTile(new Vector2(0, 0));
//        WorldObject w1OverlapLeft = new FloorTile(new Vector2(100, 0));
//        WorldObject w6 = new FloorTile(new Vector2(0, 2000));
//        
//        world.getObjects().add(w1Default);
//        world.getObjects().add(w1OverlapLeft);
//        world.getObjects().add(w6);
//        
//        assertTrue(world.getObjects().get(1).getX() == 100.f);
//        assertTrue(world.getObjects().get(2).getX() == 0.f);
//        
//        checker.xBounce(w1Default);
//        
//        //Unchanged
//        assertTrue(world.getObjects().get(1).getX() == 100.f);
//        assertTrue(world.getObjects().get(2).getX() == 0.f);
//      
//    }
//
//}
