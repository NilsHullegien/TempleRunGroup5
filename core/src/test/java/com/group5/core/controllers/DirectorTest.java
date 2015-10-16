package com.group5.core.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;
import com.group5.core.world.WorldObject;

@RunWith(GdxTestRunner.class)
public class DirectorTest {

    private World world;

    int currC = 1025;

    private Vector2 pos;

    private Vector2 camerapos;

    private Director director;

    @Before
    public void setUp() {
        camerapos = new Vector2(200,200);
        pos = new Vector2(0, 0);
        world = new World(pos, true);
    }


    @After
    public void tearDown() {
        director = null;
    }

    @Test
    public void testConstructor2() {
        director = new Director(2, pos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        assertEquals(director.getQueue().length(), 2);
    }

    @Test
    public void testnoSlices() {
        director = new Director(0, pos, world, camerapos);
        assertEquals(director.getQueue().isEmpty(), true);
    }

    @Test
    public void create2Slices() throws Exception {
        Vector2 playerpos = new Vector2 (30,30);
        director = new Director(2, playerpos, world, camerapos);
        assertEquals(director.getQueue().length(),2);
        assertEquals(director.getQueue().startPointslice(0).x,0,10);
        assertEquals(director.getQueue().startPointslice(1).x,currC*1,10);
    }

    @Test
    public void testhasPlayer() throws Exception {
        Vector2 playerpos = new Vector2 (1,1);
        director = new Director(5, playerpos, world, camerapos);
        director.update(playerpos);
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }

    @Test
    public void testhasPlayer1() throws Exception {
        Vector2 playerpos = new Vector2 (1,1);
        director = new Director(1,0, playerpos, world, camerapos);
        GameSliceQueue q = director.getQueue();
        Iterator<GameSlice> it = q.getSliceIterator();
        assertEquals(it.next().getStartPoint().x, 0,10);
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }

    @Test
    public void testhasPlayer2() throws Exception {
        Vector2 playerpos = new Vector2 (30,0);
        director = new Director(2, playerpos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        director.update(playerpos);
        assertEquals(director.getQueue().length(),2);
        assertEquals(director.getQueue().startPointslice(0).x,0,10);
        Iterator<GameSlice> q = director.getQueue().getSliceIterator();
        assertEquals(q.next().getStartPoint().x,0,10);
        assertEquals(q.next().getStartPoint().x,currC,10);
        assertEquals(director.getQueue().getPlayerinQueue(),1);
    }

    @Test
    public void testStartpoints () {
        currC = 1025;
        director = new Director(5, pos, world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        while(it.hasNext()){
            GameSlice curr = it.next();
            assertEquals((int) curr.getStartPoint().x, currC*count);
            assertEquals((int) director.getQueue().startPointslice(count).x, currC*count);
            count++;
        }
    }

    @Test
    public void getObjectsNotOnScreenTest() {
        director = new Director(5, pos, world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(false);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while(iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize, 10);
    }

    @Test
    public void getObjectsOnScreenNoObjectsTest() {
        director = new Director(5, pos, world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(true);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while(iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize, 0);
    }

    @Test
    public void getObjectsOnScreenWithObjectsTest() {
        //By changing the playerposition, we have some objects that will be on screen.
        director = new Director(5, new Vector2(5, 5), world, camerapos);
        Iterator<WorldObject> iterator = director.getObjects(true);
        //TODO: Might be able to do this without while loop?
        int iteratorSize = 0;
        while(iterator.hasNext()) {
            iteratorSize++;
            iterator.next();
        }
        assertEquals(iteratorSize, 2);
    }

    @Test
    public void directQueueWithoutAddGameSliceTest() {
        director = new Director(1, new Vector2(0,0), world, camerapos);
        assertTrue(director.getQueue().getFirst().hasPlayer());
        GameSlice last = director.getQueue().getLast();
        director.directQueue(55);
        assertTrue(last.equals(director.getQueue().getLast()));
    }

    @Test
    public void directQueueAddGameSliceTest() {
        director = new Director(5, new Vector2(100, 100), world, camerapos);
        director.directQueue(1);
    }
}
