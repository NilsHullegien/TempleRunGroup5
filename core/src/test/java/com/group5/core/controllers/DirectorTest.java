package com.group5.core.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;
import com.group5.core.world.Player;

@RunWith(GdxTestRunner.class)
public class DirectorTest {
    
    
    private Player player;
    
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
    @Test
    public void testConstructor() {
        System.out.println(1);
        director = new Director(5, pos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        assertEquals(director.getQueue().length(), 5);
    }
    /*
    @Test
    public void testConstructor2() {
        System.out.println(2);
        director = new Director(2, pos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        assertEquals(director.getQueue().length(), 2);
    }
    
    @Test
    public void testnoSlices() {
        System.out.println(3);
        director = new Director(0, pos, world, camerapos);
        assertEquals(director.getQueue().isEmpty(), true);
    }
    
    @Test 
    public void create2Slices() throws Exception {
        System.out.println(4);
        Vector2 playerpos = new Vector2 (30,30);
        director = new Director(2, playerpos, world, camerapos);
        assertEquals(director.getQueue().length(),2);
        assertEquals(director.getQueue().startPointslice(0).x,0,10);
        assertEquals(director.getQueue().startPointslice(1).x,currC*1,10);
    }

    @Test
    public void testhasPlayer() throws Exception {
        System.out.println(5);
        Vector2 playerpos = new Vector2 (1,1);
        director = new Director(5, playerpos, world, camerapos);
        director.update(playerpos);
        GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }
    
    @Test
    public void testhasPlayer1() throws Exception {
        System.out.println(6);
        Vector2 playerpos = new Vector2 (1,1);
        director = new Director(1,0, playerpos, world, camerapos);
        GameSliceQueue q = director.getQueue();
        Iterator<GameSlice> it = q.getSliceIterator();
        assertEquals(it.next().getStartPoint().x, 0,10);
        assertEquals(director.getQueue().getPlayerinQueue(),0);
    }
    @Test
    public void testhasPlayer2() throws Exception {
        System.out.println(7);
        Vector2 playerpos = new Vector2 (30,0);
        director = new Director(2, playerpos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        director.update(playerpos);
        assertEquals(director.getQueue().length(),2);
        assertEquals(director.getQueue().getPlayerinQueue(),1);
    }
    
    public void testdirectQueue() {
        System.out.println(8);
        Vector2 playerpos = new Vector2 (0,0);
        director = new Director(3, 1, playerpos, world, camerapos);
        director.update(new Vector2(90,0));
        // shifted one slice up, the first0 slice must be popped
        assertEquals(director.getQueue().startPointslice(0).x, currC, 10);
    }
    @Test
    public void testhasPlayerBIGQUEUE() throws Exception {
        System.out.println(9);
        Vector2 playerpos = new Vector2 (currC*2+1,currC*2+1);
        director = new Director(150, playerpos, world, camerapos);
        director.update(playerpos);
        GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().getPlayerinQueue(),99);
    }

    @Test
    public void testTransitionhasPlayer() throws Exception {
        System.out.println(10);
        Vector2 playerpos = new Vector2 (0,0);
        director = new Director(5, playerpos, world, camerapos);
        director.update(playerpos);
        director.getQueue().getPlayerinQueue();
        director.update(new Vector2 (60,0));
        GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().getPlayerinQueue(),2);
    }
    
    @Test
    public void testStartpoints () {
        System.out.println(11);
        currC = 1025;
        director = new Director(3, pos, world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        it.next();
        it.next();
        assertEquals((int) it.next().getStartPoint().x, currC*2);
    }
    */
}
