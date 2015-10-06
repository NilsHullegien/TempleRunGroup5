package com.group5.core;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
     * Check whether you can correctly insert items into the worldManager
     */
    @Test
    public void addTest() {
        worldManager.add(new FloorTile(physicsWorld, new Vector2(0, 0)));
        assertTrue(worldManager.getObjects().get(0).equals(new FloorTile(physicsWorld, new Vector2(0, 0))));

        worldManager.add(new FloorTile(physicsWorld, new Vector2(0, 1)));
        assertTrue(worldManager.getObjects().get(1).equals(new FloorTile(physicsWorld, new Vector2(0, 1))));

        worldManager.add(new Player(physicsWorld, new Vector2(0, 1), new Vector2(20, 20)));
        assertTrue(worldManager.getObjects().get(2).equals(new Player(physicsWorld, new Vector2(0, 1), new Vector2(20, 20))));
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
     * Test that an object that is far left from the player disappears on update.
     */
    @Test
    public void testObjectDisappearsWhenTooFarLeft() {
        worldManager.setPlayer(new Player(physicsWorld, new Vector2(10000.f, 0.f), new Vector2(100, 100)));

        FloorTile disappearing = new FloorTile(worldManager.getPhysicsWorld(), new Vector2(0, 0));
        worldManager.add(disappearing);

        worldManager.update(0);

        assertFalse(worldManager.contains(disappearing));
    }

    /**
     * Test that an object that is close to the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenVisible() {
        worldManager.setPlayer(new Player(physicsWorld, new Vector2(0, 0), new Vector2(100, 100)));

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(0, 0));
        worldManager.add(disappearing);

        worldManager.update(0);

        assertTrue(worldManager.contains(disappearing));
    }

    /**
     * Test that an object that is far right from the player does not disappear on update.
     */
    @Test
    public void testObjectDoesNotDisappearWhenFarRight() {
        worldManager.setPlayer(new Player(physicsWorld, new Vector2(0, 0), new Vector2(100, 100)));

        FloorTile disappearing = new FloorTile(physicsWorld, new Vector2(10000.f, 0.f));
        worldManager.add(disappearing);

        worldManager.update(0);

        assertTrue(worldManager.contains(disappearing));
    }
    
    @Test
    public void inputProcessorKeyDownNotJumpButtonTest() {
        inputpr.keyDown(Keys.K);
        //Input of a random key will not trigger the timer to start.
        assertTrue(worldManager.testingOnlygetTimerStart() == 0);
    }
    
    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorKeyDownJumpButtonTest() {
        inputpr.keyDown(Keys.W);
        assertFalse(worldManager.testingOnlygetTimerStart() == 0);
    }
    
    @Test
    public void inputProcessorKeyUpNotJumpButtonTest() {
        inputpr.keyUp(Keys.K);
        //Input of a random key will not trigger jump time to be modified.
        assertTrue(worldManager.testingOnlygetJumpTime() == 0);
    }
    
    /**
     * Value of the jump button is still hard coded here...
     */
    @Test
    public void inputProcessorKeyUpJumpButtonTest() {
        worldManager.setPlayer(p1);
        inputpr.keyUp(Keys.W);
        assertFalse(worldManager.testingOnlygetJumpTime() == 0);
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
}
