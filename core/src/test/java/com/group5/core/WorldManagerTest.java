package com.group5.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;

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
        p1 = new Player(physicsWorld, new Vector2(0,0), new Vector2(20,20));
    }

    /**
     * Check whether you can correctly set and get the current player
     */
    @Test
    public void setPlayerTest() {
        worldManager.setPlayer(p1);
        assertTrue(worldManager.getPlayer().equals(new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20))));
        worldManager.setPlayer(new Player(physicsWorld, new Vector2(1, 0), new Vector2(20, 20)));
        assertTrue(worldManager.getPlayer().equals(new Player(physicsWorld, new Vector2(1, 0), new Vector2(20, 20))));
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
        assertFalse(inputpr.touchDragged(0,  0,  0));
    }

    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorTouchDownTest() {
        assertFalse(inputpr.touchDown(0, 0,  0,  0));
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
    public void inputProcessorkeyUpTest() {

    }
}