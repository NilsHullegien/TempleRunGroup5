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
public class GameSliceQueueTest {
    
    private Player player;
    
    private World world;
    
    private Vector2 pos;
    
    int currC = 1025;
    
    private Vector2 camerapos;
    
    private Director director;
    
    @Before
    public void setUp() {
        //putting cameraposition in half of a 400x400x screen
        camerapos = new Vector2(200,200);
        pos = new Vector2(0, 0);
        world = new World(pos, true);
    }
    
    public void testConstructor() {
        GameSliceQueue q = new GameSliceQueue(5);
        assertEquals(q.isEmpty(), true);
    }
    
    @Test
    public void testgetPlayerinQueue() throws Exception{
        director = new Director(1, new Vector2(0,0), world, camerapos);
        director.update( new Vector2(1,1));
        assertEquals(director.getQueue().getPlayerinQueue(), 0);
    }
    
    @Test
    public void testgetPlayerinQueue2() throws Exception{
        director = new Director(2, new Vector2(30,30), world, camerapos);
        director.update( new Vector2(30,30));
        assertEquals(director.getQueue().getPlayerinQueue(), 1);
    }
    
    @Test
    public void testStartPoint() throws Exception{
        director = new Director(5, new Vector2(30,30), world, camerapos);
        assertEquals(director.getQueue().startPointslice(0).x== 0, true);
    }
    
    @Test
    public void testStartPoint2() throws Exception{
        director = new Director(5, new Vector2(30,30), world, camerapos);
        assertEquals(director.getQueue().startPointslice(3).x == currC*3, true);
    }
    
    @Test
    public void testIterator() throws Exception{
        director = new Director(10, new Vector2(0,0), world, camerapos);
        int count = 0;
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        while(it.hasNext()){
            it.next();
            count++;
        }
        assertEquals(count, 10);
    }
    
    

}
