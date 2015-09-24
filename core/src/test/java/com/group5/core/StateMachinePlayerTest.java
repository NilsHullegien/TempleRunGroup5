package com.group5.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.DirPlayerTextures;
import com.group5.core.world.Player;
import com.group5.core.world.StateMachinePlayer;
import com.group5.core.world.World;

public class StateMachinePlayerTest {

    private World w;
    
    private Player p;
    
    private StateMachinePlayer stateMachine;
    
    private DirPlayerTextures directory;
    
    @Before
    public void setup() {
        w = new World();
        p = new Player(new Vector2(0,0), 0, 0);
        w.setPlayer(p);
        stateMachine = new StateMachinePlayer(p);
        directory = new DirPlayerTextures();
    }
    
    @Test
    public void checkPlayerStateRunningTest() {
        //By default the player's texture is set to the running texture.
        assertTrue(p.getTexture().equals(directory.getTexture(1)));
        //Since the player doesn't collide with anything,
        //The texture should switch to the flying texture.
        stateMachine.checkPlayerState(w);
        assertTrue(p.getTexture().equals(directory.getTexture(0)));
    }
}
