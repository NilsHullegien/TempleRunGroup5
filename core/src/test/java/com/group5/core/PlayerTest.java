package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.World;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    
    private World world;
    
    private Player p1;
    
    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed world here
        World world = Mockito.mock(World.class);
        this.world = world;
        
        p1 = new Player(new Vector2(0,0), 0, 0);
    }
    
    
    /**
     * Test whether the equals method works properly.
     */
    @Test
    public void equalsTest() {
        //a player is the same as itself
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
    
    /**
     * Test whether the hashCode method works properly.
     */
    @Test
    public void hashCodeTest() {
        Player p2 = new Player(new Vector2(0,0), 0, 0);
        assertTrue(p1.hashCode() == p2.hashCode());
    }
    
    /**
     * Test whether the getSpeed method works properly.
     */
    @Test
    public void getSpeedTest() {
        //The speed the player has should be the same every time.
        //Independant of the parameters the player is spawned.
        Vector2 speed = new Vector2(500,0);
        
        assertTrue(p1.getSpeed().equals(speed));
        
        //Now use all random variables for the player to verify
        //The indenpendance of the parameter to the speed
        Player p2 = new Player(new Vector2(100,500), 5, 8);
        assertTrue(p2.getSpeed().equals(speed));
    }
    
    /**
     * Test whether the setJump method works properly.
     */
    @Test
    public void setJumpTest() {
        assertTrue(p1.getJumpSpeed() == 0);
        assertFalse(p1.getJumping());
        
        p1.setjump(500);
        assertTrue(p1.getJumpSpeed() == 500);
        assertTrue(p1.getJumping());
    }
    /**
     * Test whether the getJumpSpeed method works properly.
     */
    @Test
    public void getJumpSpeedTest() {
        //Default jumpSpeed when player is created
        assertTrue(p1.getJumpSpeed() == 0);
        
        //This should set the jumpSpeed to the parameter.
        p1.setjump(500);
        assertTrue(p1.getJumpSpeed() == 500);
    }
    
    /**
     * Test whether the getJumping method works properly.
     */
    @Test
    public void getJumpingTest() {
        //Default jumping (=false) when player is created
        assertFalse(p1.getJumping());
        
        //This method should set the jumping variable to true.
        p1.setjump(200);
        assertTrue(p1.getJumping());
    }
    
    @Test
    public void updateJumpingAndSpeedS0Test() {
        //Set jumping to true
        //p1.setjump(10);
        //p1.update(50, world);
        //assertFalse(p1.getJumping());
    }
    

}
