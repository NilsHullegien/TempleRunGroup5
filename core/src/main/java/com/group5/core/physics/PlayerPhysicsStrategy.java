package com.group5.core.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;

/**
 * Physics strategy for player objects.
 */
public class PlayerPhysicsStrategy extends PhysicsStrategy {

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
    public PlayerPhysicsStrategy(final World world,
                                 final Object userData,
                                 final Vector2 size,
                                 final Vector2 position,
                                 final float maxSpeed) {
        super(world);
        maximumSpeed = maxSpeed;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(position);

        Body body = world.createBody(def);
        body.setFixedRotation(!EndlessRunner.get().shouldTumble());
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(size.x / 2f, size.y / 2f, new Vector2(size.x / 2f, size.y / 2f), 0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = bodyShape;
        fixture.density = 1.0f;
        fixture.friction = 0.5f;
        fixture.restitution = 0.1f;

        Fixture f = body.createFixture(fixture);
        f.setUserData(userData);

        bodyShape.dispose();

        setBody(body);
    }

    @Override
    public void update(final float delta) {
        if (getBody().getLinearVelocity().x < maximumSpeed) {
            getBody().applyLinearImpulse(5, 0, getBody().getWorldCenter().x, getBody().getWorldCenter().y, true);
        }
    }

    /**
     * Exerts a jumping force on the relevant physics body.
     *
     * @param intensity the intensity of the jump
     */
    public void jump(final float intensity) {
        if (getBody().getLinearVelocity().y > -0.6f && getBody().getLinearVelocity().y < 0.3f) {
            getBody().applyLinearImpulse(0,
                    -getBody().getWorld().getGravity().y + 20 + (10 * intensity),
                    getBody().getWorldCenter().x,
                    getBody().getWorldCenter().y,
                    true);
        }
    }

    /**
     * Exerts a large amount of force on the physics body to make the player tumble heavily.
     */
    public void kill() {
        Body b = getBody();
        World.setVelocityThreshold(100000000);
        b.applyLinearImpulse(50, 400, b.getWorldCenter().x, b.getWorldCenter().y, true);
        maximumSpeed = Integer.MAX_VALUE;
    }
}
