package com.group5.core.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.group5.core.EndlessRunner;
import com.group5.core.util.Logger;

/**
 * Represents a tile of flooring.
 */
public class FloorTile extends WorldObject {

    /**
     * A floortile is a tile that the player can stand on and jump off of.
     *
     * @param physicsWorld the physics world to create the floor tile's body in
     * @param coord        position
     */
    public FloorTile(final World physicsWorld, final Vector2 coord) {
        super(EndlessRunner.get().getTextureCache().load("floorTile.png"), coord);
        Logger.get().info("WorldObject", "Creating FloorTile");
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

    @Override
    public void update(final float delta, final WorldManager w) {

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash * super.hashCode();
        return hash;
    }

    /**
     * Check whether an object is the same object as this instance.
     */
    @Override

    public boolean equals(final Object obj) {
        return obj instanceof FloorTile && super.equals(obj);
    }
}
