package com.group5.core.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A physics strategy for static, immovable physics.
 */
public class StaticPhysicsStrategy extends PhysicsStrategy {

    /**
     * Creates a new static object physics strategy.
     *
     * @param world    the world to act in
     * @param userData the data that should be stored in the fixture
     * @param size     the physical size of the object
     * @param position the position in the world
     */
    public StaticPhysicsStrategy(final World world,
                                 final Object userData,
                                 final Vector2 size,
                                 final Vector2 position) {
        super(world);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(position);

        Body body = world.createBody(def);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(size.x / 2f, size.y / 2f, new Vector2(size.x / 2f, size.y / 2f), 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = bodyShape;
        Fixture f = body.createFixture(fixDef);
        f.setUserData(userData);

        bodyShape.dispose();

        setBody(body);
    }

    @Override
    public void update(final float delta) {

    }
}
