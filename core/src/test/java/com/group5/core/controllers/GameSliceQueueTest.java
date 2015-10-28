package com.group5.core.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameSliceQueueTest {

    int currC = 1025;
    private World world;
    private Vector2 playerPos;
    private Vector2 camerapos;
    private GameSliceQueue gsQueue;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        //putting cameraposition in half of a 400x400x screen
        camerapos = new Vector2(200, 200);
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

    @Test
    public void addGameSliceTest() {
        GameSlice gs1 = GameSliceCasting.cast(0, world);
        GameSlice gs2 = GameSliceCasting.cast(0, world);
        GameSlice gs3 = GameSliceCasting.cast(0, world);
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
    }

    @Test
    public void getOnScreenSlicesTest() {
        GameSlice gs1 = GameSliceCasting.cast(0, world);
        GameSlice gs2 = GameSliceCasting.cast(0, world);
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
    }

    @Test
    public void addGameSliceExceptionTest() throws Exception {
        thrown.expect(IllegalStateException.class);
        //Recreate the gsQueue, now containing only 1 GameSlice.
        gsQueue = new GameSliceQueue(1);
        GameSlice gs1 = GameSliceCasting.cast(0, world);
        gsQueue.addGameSlice(gs1);
        //Update gsQueue to bring the GameSlice.
        //This means that the player will be on it and the slice shouldn't be able to be deleted.
        gsQueue.update(new Vector2(5, 5), camerapos);
        //This should trigger the Exception, since the player will be on this GameSlice.
        gsQueue.addGameSlice(gs1);
    }

    @Test
    public void startPointSliceTest() {
        GameSlice gs1 = GameSliceCasting.cast(0, world);
        GameSlice gs2 = GameSliceCasting.cast(1, world);
        gsQueue.addGameSlice(gs1);
        gsQueue.addGameSlice(gs2);
        //Slices private value should be 2, so index must be 2-1 = 1.
        assertTrue(gs2.getStartPoint() == gsQueue.startPointslice(1));
    }

    @Test
    public void startPointSliceExceptionTest() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("that slice does not exist");

        GameSlice gs1 = GameSliceCasting.cast(0, world);
        gsQueue.addGameSlice(gs1);

        //There are less then 6 slices in the gsQueue, so this should throw an exception.
        gsQueue.startPointslice(5);
    }
}
