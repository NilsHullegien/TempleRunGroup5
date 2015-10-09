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
        //putting cameraposition in half of a 400x400x screen
        camerapos = new Vector2(200,200);
        pos = new Vector2(0, 0);
        world = new World(pos, true);
        //world = Mockito.mock(World.class);
        //player = new Player(world, v2, v2);
        //director = new Director(5, v2, world, v2);
    }
    @Test
    public void testConstructor() {
        director = new Director(5, pos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        assertEquals(director.getQueue().length(), 5);
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
        GameSliceQueue q = director.getQueue();
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
        //GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().length(),2);
        assertEquals(director.getQueue().startPointslice(0).x,0,10);
        Iterator<GameSlice> q = director.getQueue().getSliceIterator();
        assertEquals(q.next().getStartPoint().x,0,10);
        assertEquals(q.next().getStartPoint().x,currC,10);
        //assertEquals(director.getQueue().startPointslice(1).x,currC,10);
        assertEquals(director.getQueue().getPlayerinQueue(),1);
    }
    
    @Test
    public void testhasPlayer3() throws Exception {
        Vector2 playerpos = new Vector2 (60,00);
        director = new Director(5, playerpos, world, camerapos);
        director.update(playerpos);
        GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().getPlayerinQueue(),2);
    }
    
    public void testdirectQueue() {
        Vector2 playerpos = new Vector2 (0,0);
        director = new Director(3, 1, playerpos, world, camerapos);
        director.update(new Vector2(90,90));
        // shifted one slice up, the first slice must be popped
        assertEquals(director.getQueue().startPointslice(0).x, currC, 10);
    }

    @Test
    public void testhasPlayerBIGQUEUE() throws Exception {
        Vector2 playerpos = new Vector2 (currC*2,currC*2);
        director = new Director(150, playerpos, world, camerapos);
        director.update(playerpos);
        GameSliceQueue q = director.getQueue();
        assertEquals(director.getQueue().getPlayerinQueue(),98);
    }
    
    @Test
    public void testTransitionhasPlayer() throws Exception {
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
        currC = 1025;
        director = new Director(5, pos, world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        while(it.hasNext()){
            GameSlice curr = it.next();
            System.out.println(curr.getStartPoint().x);
            assertEquals((int) curr.getStartPoint().x, currC*count);
            assertEquals((int) director.getQueue().startPointslice(count).x, currC*count);
            count++;
        }
    }
    
    @Test
    public void testStartpoints2 () {
        currC = 1025;
        director = new Director(2, pos, world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        while(it.hasNext()){
            GameSlice curr = it.next();
            System.out.println(curr.getStartPoint().x);
            assertEquals((int) curr.getStartPoint().x, currC*count);
            assertEquals((int) director.getQueue().startPointslice(count).x, currC*count);
            count++;
        }
        assertEquals(count, 2);
    }
    
    

}
