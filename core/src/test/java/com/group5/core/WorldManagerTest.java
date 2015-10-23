package com.group5.core;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.Director;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Tests for the worldManager object.
 */
@RunWith(GdxTestRunner.class)
public class WorldManagerTest {


    private WorldManager worldManager;
    private World physicsWorld;
    private InputProcessor inputpr;
    private Player p1;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed worldManager here
        this.worldManager = new WorldManager();
        this.physicsWorld = worldManager.getPhysicsWorld();
        inputpr = worldManager.getInputProcessor();
        p1 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        worldManager.setPlayer(p1);
    }

    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        worldManager.setPlayer(p1);
        assertEquals(worldManager.getPlayer(), new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20)));
        worldManager.setPlayer(new Player(physicsWorld, new Vector2(1, 0), new Vector2(20, 20)));
        assertEquals(worldManager.getPlayer(), new Player(physicsWorld, new Vector2(1, 0), new Vector2(20, 20)));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorKeyTypedJumpButtonTest() {
        assertFalse(inputpr.keyTyped((char) Keys.K));
        assertFalse(inputpr.keyTyped((char) Keys.W));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorTouchUpTest() {
        assertFalse(inputpr.touchUp(0, 0, 0, 0));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorTouchDraggedTest() {
        assertFalse(inputpr.touchDragged(0, 0, 0));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorTouchDownTest() {
        assertFalse(inputpr.touchDown(0, 0, 0, 0));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorScrolledTest() {
        assertFalse(inputpr.scrolled(0));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorMouseMovedTest() {
        assertFalse(inputpr.mouseMoved(0, 0));
    }

    @Test
    public void inputProcessorkeyDownTest() {
        assertEquals(worldManager.getTimerStart(), 0);
        inputpr.keyDown(Keys.W);
        assertFalse(worldManager.getTimerStart() == 0);
    }

    @Test
    public void inputProcessorkeyUpTest() {
        assertEquals(worldManager.getJumpTime(), 0);
        inputpr.keyUp(Keys.W);
        assertFalse(worldManager.getJumpTime() == 0);
    }

    @Test
    public void updateTest() {
        worldManager.setDirector(new Director(5, 0, new Vector2(5, 5), physicsWorld, new Vector2(200, 200)));
        Vector2 oldPos = worldManager.getPlayer().getPosition();
        assertTrue(worldManager.getDirector().getQueue().getFirst().hasPlayer());
        worldManager.update(5, 0);
        assertTrue(worldManager.getDirector().getQueue().getFirst().hasPlayer());
        //Since the game hasn't started yet, the player should still be in the same position.
        assertTrue(oldPos.equals(worldManager.getPlayer().getPosition()));
    }

    @Test
    public void getGameStatusTest() {
        //True by default.
        assertTrue(worldManager.getGameStatus());
        worldManager.getPlayer().kill();
        assertFalse(worldManager.getGameStatus());
    }
}