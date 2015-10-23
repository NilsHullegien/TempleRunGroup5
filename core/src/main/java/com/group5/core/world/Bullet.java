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
 * Class for the bullet that is shot by the ShootingObstacle.
 * @author Nils.
 */
public class Bullet extends Obstacle {

    /**
     * Constructor of a Bullet.
     * @param physicsWorld the world the bullet is in.
     * @param coord the coordinates of the bullet.
     */
    public Bullet(final World physicsWorld, final Vector2 coord) {
        super(physicsWorld, coord, EndlessRunner.get().getTextureCache().load("shots.png"));
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(getX(), getY()));

        Body body = physicsWorld.createBody(def);
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 10f, getHeight() / 10f + 0.5f, new Vector2(getWidth() / 10f, getHeight() / 10f), 0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = bodyShape;
        fixture.density = 0.f;
        fixture.friction = 0.f;
        fixture.restitution = 0.f;

        Fixture f = body.createFixture(fixture);
        f.setUserData(this);

        bodyShape.dispose();
        setPhysicsBody(body);
    }

    /**
     * Call function to render a WorldManager Object.
     *
     * @param batch The batch the object should draw in
     */
    @Override
    public void update(final float delta, final WorldManager manager) {
        Body b = getPhysicsBody();
        b.applyLinearImpulse(-0.15f, 0,
                b.getWorldCenter().x,
                b.getWorldCenter().y,
                true);
    }
}
