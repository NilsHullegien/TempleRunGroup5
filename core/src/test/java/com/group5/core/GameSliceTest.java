package com.group5.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.group5.core.controllers.GameSlice;

public class GameSliceTest {

    private GameSlice slice;
    
    /**
     * SetUp a prepared game slice with random values for testing.
     */
    @Before
    public void setup() {
        slice = new GameSlice(null, 10, 20, 30, 40, 50, 60);
    }
    
    /**
     * Test whether the getNoGapObstacleRNG method works.
     */
    @Test
    public void getNoGapObstacleRNGTest() {
        //It should be the 4th parameter in the constructor of GameSlice.
        assertTrue(slice.getNoGapObstacleRNG() == 40);
    }
    
    /**
     * Test whether the getYPosObstacles method works.
     */
    @Test
    public void getYPosObstaclesTest() {
        //It should be the 5th parameter in the constructor of GameSlice.
        assertTrue(slice.getYPosObstacles() == 50);
    }
}
