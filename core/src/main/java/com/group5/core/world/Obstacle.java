package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;

/**
 * Represents an obstacle for the player to avoid.
 */
public class Obstacle extends WorldObject {

    /**
     * Constructor of the obstacle.
     *
     * @param physicsWorld the physics world to create the obstacle's body in
     * @param coord        Initial position
     */
    public Obstacle(final World physicsWorld, final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("obstacle.png"), coord);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(coord);

        Body body = physicsWorld.createBody(def);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2f, getHeight() / 2f, new Vector2(getWidth() / 2f, getHeight() / 2f), 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = bodyShape;
        Fixture f = body.createFixture(fixDef);
        f.setUserData(this);

        bodyShape.dispose();
        setPhysicsBody(body);
    }

    /**
     * No implementation.
     */
    @Override
    public void update(final float delta, final WorldManager worldManager) {
    }
}
