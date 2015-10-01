package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.Director;
import com.group5.core.controllers.Spawner;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Obstacle;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Spawner Test class.
 */
public class SpawnerTest {

    private WorldManager worldManager;

    private World physicsWorld;

    private Spawner spawner;

    private Player player;

    private Director director;

    /**
     * Initialize the variables for the tests.
     */
    @Before
    public void setup() {
        worldManager = new WorldManager();
        physicsWorld = worldManager.getPhysicsWorld();
        spawner = new Spawner(worldManager);
        player = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        worldManager.setPlayer(player);
        director = spawner.getDirector();
        director.setState(0);
    }

    /**
     * Test the player position.
     */
    @Test
    public void testGetPlayerPos() {
        assertTrue(spawner.getPlayerPosition() == 0);
    }

    /**
     * Test if the last floor is null it returns 0.
     */
    @Test
    public void testGetLastFloorNull() {
        assertTrue(spawner.getLastFloor() == 0);
    }

    /**
     * Test the last floor method for one floortile.
     */
    @Test
    public void testGetLastFloor() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(1, 0)));
        assertTrue(spawner.getLastFloor() == 1 + 1024.f / 50.f);
    }

    /**
     * Test the last floor method for multiple floortiles.
     */
    @Test
    public void testGetLastFloorMultiple() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(0, 0)));
        worldManager.add(new FloorTile(physicsWorld, new Vector2(1, 0)));
        worldManager.add(new FloorTile(physicsWorld, new Vector2(2, 0)));
        assertTrue(spawner.getLastFloor() == 2 + 1024 / 50.f);
    }

    /**
     * Test the last floor method for multiple floortiles unordered.
     */
    @Test
    public void testGetLastFloorMultipleUnordered() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(3, 0)));
        worldManager.add(new FloorTile(physicsWorld, new Vector2(0, 0)));
        worldManager.add(new FloorTile(physicsWorld, new Vector2(2, 0)));
        assertTrue(spawner.getLastFloor() == 3 + 1024 / 50.f);
    }

    /**
     * Test the getFloor method to get the size of the floor.
     */
    @Test
    public void testGetFloorSize() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(100, 200)));
        assertTrue(spawner.getFloorSize() == 1024 / 50.f);
    }

    /**
     * Test the last obstacle method for one obstacle.
     */
    @Test
    public void testGetLastObstacle() {
        worldManager.add(new Obstacle(physicsWorld, new Vector2(0, 0)));
        assertTrue(spawner.getLastObstacle() == 100 / 50.f);
    }

    /**
     * Test the last obstacle method for multiple obstacles.
     */
    @Test
    public void testGetLastObstacleMultiple() {
        worldManager.add(new Obstacle(physicsWorld, new Vector2(0, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(1, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(2, 0)));
        assertTrue(spawner.getLastObstacle() == (2 + 100 / 50.f));
    }

    /**
     * Test the last obstacle method for multiple obstacles unordered.
     */
    @Test
    public void testGetLastObstacleMultipleUnordered() {
        worldManager.add(new Obstacle(physicsWorld, new Vector2(1, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(2, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(0, 0)));
        assertTrue(spawner.getLastObstacle() == 2 + 100 / 50.f);
    }

    /**
     * Test the getMostRightPos method for the floortile being most right.
     */
    @Test
    public void testGetMostRightPos() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(1, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(2, 0)));
        assertTrue(spawner.getMostRightPos() == 1 + 1024 / 50.f);
    }

    /**
     * Test the getMostRightPos method for the floortile being most
     * right while on an ObstacleCourse.
     */
    @Test
    public void testGetMostRightPosObstacleCourse() {
        director.setState(1);
        worldManager.add(new FloorTile(physicsWorld, new Vector2(1, 0)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(2, 0)));
        assertTrue(spawner.getMostRightPos() == 1 + 1024 / 50.f);
    }

    /**
     * Test the getMostRightPos method for the obstacle being most
     * right while on an ObstacleCourse.
     */
    @Test
    public void testGetMostRightPosObstacleCourseFurthest() {
        director.setState(1);
        worldManager.add(new FloorTile(physicsWorld, new Vector2(1, 100)));
        worldManager.add(new Obstacle(physicsWorld, new Vector2(20, 200)));
        assertTrue(spawner.getMostRightPos() == 20 + 100 / 50.f);
    }
}
