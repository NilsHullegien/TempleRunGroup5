package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WorldObjectTest {
    private World world;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new World();
    }

    /**
     * Test the constructor of a world object by using a Player subclass
     */
    @Test
    public void WordObjectConstructorTest() {
        world.add(new Player(new Vector2(5, 10),0 ,0));
        WorldObject obj = world.getObjects().get(0);

        //create a new object and check whether the starting positions are correctly initialised
        assertTrue(obj.getX() == 5);
        assertTrue(obj.getY() == 10);
    }

}
