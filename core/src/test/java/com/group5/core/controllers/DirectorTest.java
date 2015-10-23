package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;
import com.group5.core.world.WorldObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class DirectorTest {

    int currC = 1025;
    private World world;
    private Vector2 pos;

    private Vector2 camerapos;

    private Director director;

    @Before
    public void setUp() {
        camerapos = new Vector2(200, 200);
        pos = new Vector2(0, 0);
        world = new World(pos, true);
    }


    @After
    public void tearDown() {
        director = null;
    }

    @Test
    public void testConstructor2() {
        director = new Director(2, 0, pos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        assertEquals(director.getQueue().length(), 2);
    }

    @Test
    public void testnoSlices() {
        director = new Director(0, 0, pos, world, camerapos);
        assertEquals(director.getQueue().isEmpty(), true);
    }

    @Test
    public void create2Slices() throws Exception {
        Vector2 playerpos = new Vector2 (30,30);
        director = new Director(2, 0, playerpos, world, camerapos);
        assertEquals(director.getQueue().length(),2);
    }

    @Test
    public void testhasPlayer() throws Exception {
        Vector2 playerpos = new Vector2 (1,1);
        director = new Director(5, 0, playerpos, world, camerapos);
        director.update(playerpos, 0);
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }

    @Test
    public void testhasPlayer1() throws Exception {
        Vector2 playerpos = new Vector2(1, 1);
        director = new Director(1, 0, playerpos, world, camerapos);
        GameSliceQueue q = director.getQueue();
        Iterator<GameSlice> it = q.getSliceIterator();
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }

    @Test
    public void testhasPlayer2() throws Exception {
        Vector2 playerpos = new Vector2 (30,0);
        director = new Director(2, 0, playerpos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        playerpos.set(((director.getQueue().getFirst().getEndPoint().x + 10) / 50), 0);
        director.update(playerpos, 0);
        assertEquals(director.getQueue().length(),2);
    }

    @Test
    public void testStartpoints() {
        currC = 1025;
        director = new Director(5, 0, pos, world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        GameSlice curr = it.next();
        assertEquals(curr.getStartPoint().x == 0, true);
    }

    @Test
    public void getObjectsNotOnScreenTest() {
        director = new Director(5, 0, pos, world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(false);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while (iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize > 1, true);
    }

    @Test
    public void getObjectsOnScreenNoObjectsTest() {
        director = new Director(5, 0, pos, world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(true);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while (iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize, 0);
    }

    @Test
    public void getObjectsOnScreenWithObjectsTest() {
        //By changing the playerposition, we have some objects that will be on screen.
        director = new Director(5, 0, new Vector2(5, 5), world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(true);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while (iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize, 2);
    }

    @Test
    public void directQueueWithoutAddGameSliceTest() {
        director = new Director(1, 0, new Vector2(0,0), world, camerapos);
        assertTrue(director.getQueue().getFirst().hasPlayer());
        GameSlice last = director.getQueue().getLast();
        director.directQueue(55);
        assertTrue(last.equals(director.getQueue().getLast()));
    }

    @Test
    public void directQueueAddGameSliceTest() {
        director = new Director(5, 0, new Vector2(100, 100), world, camerapos);
        director.directQueue(1);
    }
}
