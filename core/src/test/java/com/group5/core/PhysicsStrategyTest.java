package com.group5.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.physics.StaticPhysicsStrategy;

@RunWith(GdxTestRunner.class)
public class PhysicsStrategyTest {

    private StaticPhysicsStrategy strat;
    private World world;

    @Before
    public void setup() {
        world = new World(new Vector2(5,0), true);
        strat = new StaticPhysicsStrategy(world, null, new Vector2(0,0), new Vector2(0,0));
    }

    @Test
    public void getWorldTest() {
        assertTrue(world.equals(strat.getWorld()));
    }
}
