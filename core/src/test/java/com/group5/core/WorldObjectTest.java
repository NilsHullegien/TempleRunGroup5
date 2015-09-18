package com.group5.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.Player;
import com.group5.core.world.World;
import com.group5.core.world.WorldObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WorldObjectTest {
    private World world;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new World();
        world.add(new Player(new Vector2(5, 10),0 ,0));
    }

    /**
     * Test the constructor of a world object by using a Player subclass
     */
    @Test
    public void WordObjectConstructorTest() {
        WorldObject obj = world.getObjects().get(0);

        //create a new object and check whether the starting positions are correctly initialised
        assertTrue(obj.getX() == 5);
        assertTrue(obj.getY() == 10);
    }
    
//    @Test
//    public void doRenderTest() {
//        SpriteBatch batch = new SpriteBatch();
//        WorldObject wo = Mockito.mock(WorldObject.class);
//        Texture t = Mockito.mock(Texture.class);
//        
//        wo.doRender(batch);
//        Mockito.verify(batch.draw(t, 0,0));
//    }

}
