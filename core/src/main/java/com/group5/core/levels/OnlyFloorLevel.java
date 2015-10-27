package com.group5.core.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.controllers.GameSlice;
import com.group5.core.util.RandomValue;
import com.group5.core.world.FloorTile;
import com.group5.core.world.ShootingObstacle;

/**
 * This is just a temporary class to check if the
 * implementation works, we'll use decorators to
 * make the procedural levels much easier to create.
 */
public class OnlyFloorLevel extends GameSlice {
    /**
     * constructor.
     *
     * @param world physicsworld
     * @author Levi
     */
    public OnlyFloorLevel(final World world) {
        super(new Vector2(0, 0), new Vector2(1025, 2000));
        createElems(world);
    }

    /**
     * constructor with prevrious GameSlice.
     *
     * @param before GameSlice
     * @param world  physicsworld
     */
    public OnlyFloorLevel(final GameSlice before, final World world) {
        super(before, new Vector2(0, 0), new Vector2(1025, 2000));
        createElems(world);
    }

    /**
     * Create elements of this levelelement.
     *
     * @param world physicsworld
     */
    private void createElems(final World world) {
        getElems().add(new FloorTile(world, new Vector2(this.getStartPoint().x / 50, 0)));
        float range = (this.getEndPoint().x - this.getStartPoint().x) / 50;
        float xPosObstacle = (float) (this.getStartPoint().x / 50 + range * RandomValue.get().nextDouble());
        ShootingObstacle obstacle = new ShootingObstacle(world, new Vector2(xPosObstacle, 1.2f));
        getElems().add(obstacle);
        getElems().add(obstacle.getBullet());
    }
}
