package com.group5.core;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import com.group5.core.world.WorldObject;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    private World physicsWorld;
    private WorldManager worldManager;
    private Player player;
    private Body body;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed worldManager here
        this.worldManager = new WorldManager();
        this.physicsWorld = worldManager.getPhysicsWorld();

        player = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        worldManager.setPlayer(player);
        body = Mockito.mock(Body.class);
        Mockito.when(body.getWorldCenter()).thenReturn(new Vector2(0, 0));
        Mockito.when(body.getLinearVelocity()).thenReturn(new Vector2(3, 0));
        player.setPhysicsBody(body);
    }


    /**
     * Test whether the equals method works properly.
     */
    @Test
    public void equalsTest() {
        //a player is the same as itself
        Player p1 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        assertTrue(p1.equals(p1));

        //a player is the same as another player with the same properties
        Player p2 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        assertTrue(p1.equals(p2));

        //a player is not the same as a player with different player
        Player p3 = new Player(physicsWorld, new Vector2(1, 0), new Vector2(20, 20));
        assertFalse(p1.equals(p3));
        Player p4 = new Player(physicsWorld, new Vector2(0, 1), new Vector2(20, 20));
        assertFalse(p1.equals(p4));

        //a player is never the same this as an object of a different type
        FloorTile f = new FloorTile(physicsWorld, new Vector2(0, 0));
        assertFalse(p1.equals(f));
    }
    
    /**
     * Test whether the hashCode method works properly.
     */
    @Test
    public void hashCodeTest() {
        Player p1 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        Player p2 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        assertTrue(p1.hashCode() == p2.hashCode());
    }

    @Test
    public void jumpTest() {
        player.jump(1.0f);
        Mockito.verify(body).applyLinearImpulse(0, 60, 0, 0, true);
    }

    @Test
    public void killTest() {
        assertFalse(player.isDead());
        player.kill();
        assertTrue(player.isDead());
    }
}
