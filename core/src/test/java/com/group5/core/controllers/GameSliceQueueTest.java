package com.group5.core.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameSliceQueueTest {

    private World world;

    private Vector2 playerPos;

    int currC = 1025;

    private Vector2 camerapos;


    private GameSliceQueue gsQueue;

    @Before
    public void setUp() {
        //putting cameraposition in half of a 400x400x screen
        camerapos = new Vector2(200,200);
        playerPos = new Vector2(0, 0);
        world = new World(playerPos, true);
        //Setting the default to 2, can be changed if needed.
        gsQueue = new GameSliceQueue(2);
    }

    @Test
    public void testConstructor() {
        GameSliceQueue q = new GameSliceQueue(5);
        assertEquals(q.isEmpty(), true);
    }

    /*@Test
    public void addGameSliceTest() {
        GameSlice gs1 = GameSliceCasting.cast(world);
        GameSlice gs2 = GameSliceCasting.cast(world);
        GameSlice gs3 = GameSliceCasting.cast(world);
        gsQueue.addGameSlice(gs1);
        gsQueue.addGameSlice(gs2);
        assertEquals(gsQueue.getFirst(), gs1);
        assertEquals(gsQueue.getLast(), gs2);
        assertTrue(gsQueue.full());
        //Now the Queue is full
        gsQueue.addGameSlice(gs3);
        assertTrue(gsQueue.full());
        assertEquals(gsQueue.getFirst(), gs2);
        assertEquals(gsQueue.getLast(), gs3);
    }*/

    //@Test
    /*public void getOnScreenSlicesTest() {
        GameSlice gs1 = GameSliceCasting.cast(world);
        GameSlice gs2 = GameSliceCasting.cast(world);
        gsQueue.addGameSlice(gs1);
        gsQueue.addGameSlice(gs2);
        Iterator<GameSlice> iterator = gsQueue.getOnScreenSlices();
        assertFalse(iterator.hasNext());
        assertFalse(gsQueue.getFirst().isonScreen());
        assertFalse(gsQueue.getLast().isonScreen());
        gsQueue.update(new Vector2(5, 5), camerapos);
        assertTrue(gsQueue.getFirst().isonScreen());
        assertTrue(gsQueue.getLast().isonScreen());
        iterator = gsQueue.getOnScreenSlices();
        assertTrue(iterator.hasNext());
    }*/
}
