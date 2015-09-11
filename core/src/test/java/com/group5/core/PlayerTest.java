package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    
    private World world;
    
    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        this.world = new World();
    }
    
    /**
     * Check whether the player position correctly updates when no collision takes place.
     */
    @Test
    public void updateTest() {
        Player p = new Player(new Vector2(0,0), 10, 10);
        
        p.update(1f, world);
        
        assertTrue(Math.abs(p.getY()+150f) < 0.01);
        System.out.println(Math.abs(p.getX()));
        assertTrue(Math.abs(p.getX() - 250f) < 0.02);
        
    }
    
    /**
     * Check that a player cannot move when he is on top of an obstacle.
     */
    @Test
    public void updateTestWithCollision() {
     
        Player p = new Player(new Vector2(0,0), 10, 10);
        FloorTile f = new FloorTile(new Vector2(0, -p.getTexture().getHeight()));
        
        world.add(p);
        world.add(f);
        
        p.update(0.1f, world);
        assertTrue(Math.abs(p.getY() - 0f) < 0.01);
        
    }
    
    
    /**
     * Test whether the equals method works properly
     */
    @Test
    public void equalsTest() {

        //a player is the same as itself
        Player p1 = new Player(new Vector2(0, 0), 0, 0);
        assertTrue(p1.equals(p1));

        //a player is the same as another player with the same properties
        Player p2 = new Player(new Vector2(0, 0), 0, 0);
        assertTrue(p1.equals(p2));

        //a player is not the same as a player with different player
        Player p3 = new Player(new Vector2(1, 0), 0, 0);
        assertFalse(p1.equals(p3));
        Player p4 = new Player(new Vector2(0, 1), 0, 0);
        assertFalse(p1.equals(p4));

        //a player is never the same this as an object of a different type
        FloorTile f = new FloorTile(new Vector2(0, 0));
        assertFalse(p1.equals(f));

    }

}
