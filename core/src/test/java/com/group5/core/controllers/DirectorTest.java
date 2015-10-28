package com.group5.core.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.GdxTestRunner;
import com.group5.core.levels.BlockChanceLevel;
import com.group5.core.levels.BlockLevel;
import com.group5.core.levels.HoleBlockLevel;
import com.group5.core.levels.HoleLevel;
import com.group5.core.levels.RiseUpLevel;
import com.group5.core.util.RandomValue;
import com.group5.core.world.WorldObject;

@RunWith(GdxTestRunner.class)
public class DirectorTest {

    int currC = 1025;
    private World world;
    private Vector2 pos;

    private Vector2 camerapos;

    /**
     * Director created in different methods to test different functionalities.
     */
    private Director director;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        Vector2 playerpos = new Vector2(30, 30);
        director = new Director(2, 0, playerpos, world, camerapos);
        assertEquals(director.getQueue().length(), 2);
    }

    @Test
    public void testhasPlayer() throws Exception {
        Vector2 playerpos = new Vector2(1, 1);
        director = new Director(5, 0, playerpos, world, camerapos);
        director.update(playerpos, 0);
        assertEquals(director.getQueue().getPlayerinQueue(), 0);
    }

    @Test
    public void testhasPlayer1() throws Exception {
        Vector2 playerpos = new Vector2(1, 1);
        director = new Director(1, 0, playerpos, world, camerapos);
        assertEquals(director.getQueue().getPlayerinQueue(), 0);
    }

    @Test
    public void testhasPlayer2() throws Exception {
        Vector2 playerpos = new Vector2(30, 0);
        director = new Director(2, 0, playerpos, world, camerapos);
        assertEquals(director.getQueue().full(), true);
        playerpos.set(((director.getQueue().getFirst().getEndPoint().x + 10) / 50), 0);
        director.update(playerpos, 0);
        assertEquals(director.getQueue().length(), 2);
    }

    @Test
    public void testStartpoints() {
        currC = 1025;
        director = new Director(5, 0, pos, world, camerapos);
        Iterator<GameSlice> it = director.getQueue().getSliceIterator();
        GameSlice curr = it.next();
        assertEquals(curr.getStartPoint().x == 0, true);
    }

    //TODO: Value might need to be tweaked again.
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
        assertEquals(iteratorSize, 3);
    }

    @Test
    public void directQueueWithoutAddGameSliceTest() {
        director = new Director(1, 0, new Vector2(0, 0), world, camerapos);
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

    @Test
    public void addGameSliceExceptionTriggerTest() {
        thrown.expect(Exception.class);

        director = new Director(0, 0, new Vector2(100, 100), world, camerapos);
        director.directQueue(Integer.MAX_VALUE);
    }

    @Test
    public void directQueueExceptionTest() throws Exception {
        thrown.expect(Exception.class);
        director = new Director(2, 0, new Vector2(100, 100), world, camerapos);
        int minimal = 1;
        director.directQueue(minimal);
        assertTrue(director.getQueue().getPlayerinQueue() > minimal);
    }

    @Test
    public void GameSliceCastSwitch1Test() {
        RandomValue.get().setSeed(46L);
        director = new Director(2, 0, pos, world, camerapos);
        assertTrue(director.getQueue().getLast().getClass().equals(BlockChanceLevel.class));
    }

    @Test
    public void GameSliceCastSwitch2Test() {
        RandomValue.get().setSeed(50L);
        director = new Director(2, 0, pos, world, camerapos);
        assertTrue(director.getQueue().getLast().getClass().equals(BlockLevel.class));
    }

    @Test
    public void GameSliceCastSwitch3Test() {
        RandomValue.get().setSeed(51L);
        director = new Director(2, 0, pos, world, camerapos);
        assertTrue(director.getQueue().getLast().getClass().equals(HoleLevel.class));
    }

    @Test
    public void GameSliceCastSwitch4Test() {
        RandomValue.get().setSeed(48L);
        director = new Director(2, 0, pos, world, camerapos);
        assertTrue(director.getQueue().getLast().getClass().equals(RiseUpLevel.class));
    }

    @Test
    public void GameSliceCastSwitchDefaultTest() {
        RandomValue.get().setSeed(49L);
        director = new Director(2, 0, pos, world, camerapos);
        assertTrue(director.getQueue().getLast().getClass().equals(HoleBlockLevel.class));
    }
}
