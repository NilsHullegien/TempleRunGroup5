package com.group5.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.physics.PlayerPhysicsStrategy;
import com.group5.core.world.FloorTile;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    private World physicsWorld;
    private WorldManager worldManager;
    private Player player;
    private PlayerPhysicsStrategy strategy;

    @Before
    public void setUp() {
        // TODO: Probably should use a stubbed worldManager here
        this.worldManager = new WorldManager();
        this.physicsWorld = worldManager.getPhysicsWorld();

        player = new Player(physicsWorld, new Vector2(0, 0), new Vector2(20, 20));
        worldManager.setPlayer(player);
        strategy = Mockito.mock(PlayerPhysicsStrategy.class);
        Body b = Mockito.mock(Body.class);
        Mockito.when(b.getPosition()).thenReturn(new Vector2(0, 0));
        Mockito.when(b.getLinearVelocity()).thenReturn(new Vector2(0, 0));
        Mockito.when(strategy.getBody()).thenReturn(b);

        player.setPhysicsStrategy(strategy);
    }


    /**
     * Test whether the equals method works properly
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

    @Test
    public void jumpTest() {
        player.jump(1.0f);
        Mockito.verify(strategy).jump(1.0f);
    }

    @Test
    public void killTest() {
        assertFalse(player.isDead());
        player.kill();
        assertTrue(player.isDead());
    }

    @Test
    public void hashCodeTest() {
        Player p2 = new Player(physicsWorld, new Vector2(0, 0), new Vector2(10, 20));
        assertFalse(player.equals(p2));
        assertTrue(player.hashCode() == p2.hashCode());
    }

    @Test
    public void updateTest() {
        player.update(5, worldManager);
        Mockito.verify(strategy).update(5);
    }
}
