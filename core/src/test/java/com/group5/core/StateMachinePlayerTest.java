package com.group5.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.DirPlayerTextures;
import com.group5.core.world.Player;
import com.group5.core.world.StateMachinePlayer;
import com.group5.core.world.WorldManager;

public class StateMachinePlayerTest {

    private WorldManager w;
    
    private Player p;
    
    private StateMachinePlayer stateMachine;
    
    private DirPlayerTextures directory;
    
    @Before
    public void setup() {
        w = new WorldManager();
        p = new Player(w.getPhysicsWorld(), new Vector2(0,0), new Vector2(8, 8));
        w.setPlayer(p);
        stateMachine = new StateMachinePlayer(p);
        directory = new DirPlayerTextures();
    }
    
    @Test
    public void checkPlayerStateRunningTest() {
        //By default the player's texture is set to the running texture.
        assertTrue(p.getTexture().equals(directory.getTexture(1)));
        //Since the player has no ground to stand on and there is no gravity
        //The texture should switch to the flying texture.
        w.getPhysicsWorld().step(1 / 2f, 6, 2);
        stateMachine.checkPlayerState(w);
        assertTrue(p.getTexture().equals(directory.getTexture(0)));
    }
}
