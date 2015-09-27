package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import com.group5.core.world.WorldObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WorldManagerObjectTest {
    private WorldManager worldManager;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed worldManager here
        this.worldManager = new WorldManager();
    }

    /**
     * Test the constructor of a worldManager object by using a Player subclass
     */
    @Test
    public void WordObjectConstructorTest() {
        worldManager.add(new Player(new World(new Vector2(0.f, 0.f), true), new Vector2(5, 10), new Vector2(20, 20)));
        WorldObject obj = worldManager.getObjects().get(0);

        //create a new object and check whether the starting positions are correctly initialised
        assertTrue(obj.getX() == 5);
        assertTrue(obj.getY() == 10);
    }

}
