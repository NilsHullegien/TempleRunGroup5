package com.group5.core.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Physics strategy for bullet objects.
 */
public class BulletPhysicsStrategy extends PhysicsStrategy {

    /**
     * The maximum speed at which the player can move.
     */
    private float maximumSpeed;

    /**
     * Creates a new player physics strategy.
     *
     * @param world    the world to act in
     * @param userData the data that should be stored in the fixtures
     * @param size     the physical size of the player
     * @param position the position in the world of the player
     * @param maxSpeed the maximum speed of the player
     */
    public BulletPhysicsStrategy(final World world,
                                 final Object userData,
                                 final Vector2 size,
                                 final Vector2 position,
                                 final float maxSpeed) {
        super(world);
        maximumSpeed = maxSpeed;


        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(position.x, position.y));

        Body body = world.createBody(def);
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(size.x / 2f, size.y / 2f, new Vector2(size.x / 2f, size.y / 2f), 0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = bodyShape;
        fixture.density = 0.f;
        fixture.friction = 0.f;
        fixture.restitution = 0.f;

        Fixture f = body.createFixture(fixture);
        f.setUserData(userData);

        bodyShape.dispose();
        body.setGravityScale(0);

        setBody(body);
    }

    @Override
    public void update(final float delta) {
        if (getBody().getLinearVelocity().x < maximumSpeed) {
            getBody().applyLinearImpulse(-1f, 0, getBody().getWorldCenter().x, getBody().getWorldCenter().y, true);
        }
    }
}

