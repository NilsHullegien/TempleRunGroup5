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
 * Represents a game character controlled by the user.
 */
public class Player extends AnimatedWorldObject {
    /**
     * The current speed the player is moving at.
     */
    private Vector2 speed;

    /**
     * Whether or not the player is dead.
     */
    private boolean dead = false;

    /**
     * Constructs a new Player positioned at the given coordinates.
     *
     * @param physicsWorld
     *            the physics world to create the player's body in
     * @param coord
     *            coordinate
     * @param size
     *            size of player in pixels
     */
    public Player(final World physicsWorld, final Vector2 coord, final Vector2 size) {
        super(EndlessRunner.get().getTextureCache().load("player_running.png"), size, coord, 6, 1, 5);
        speed = new Vector2(5, 0);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(getX(), getY()));

        Body body = physicsWorld.createBody(def);
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2f, getHeight() / 2f, new Vector2(getWidth() / 2f, getHeight() / 2f), 0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = bodyShape;
        fixture.density = 1.0f;
        fixture.friction = 0.5f;
        fixture.restitution = 0.1f;

        Fixture f = body.createFixture(fixture);
        f.setUserData(this);

        bodyShape.dispose();
        setPhysicsBody(body);
    }

    /**
     * Checks whether the player is dead.
     *
     * @return whether the player is dead
     */
    public boolean isDead() {
        dead = dead || getY() < 0.f;
        return dead;
    }

    /**
     * Kills the player instantly.
     */
    public void kill() {
        Body b = getPhysicsBody();
        World.setVelocityThreshold(100000000);
        b.applyLinearImpulse(50, 400, b.getWorldCenter().x, b.getWorldCenter().y, true);
        dead = true;
    }

    @Override
    public void update(final float delta, final WorldManager worldManager) {
        // update the animation
        super.update(delta, worldManager);
        Body b = getPhysicsBody();
        double maxSpeed = 10;
        if (b.getLinearVelocity().y > 0.1f || b.getLinearVelocity().y < -0.1f) {
            this.setAnimation(EndlessRunner.get().getTextureCache().load("player_jumping.png"), 3, 1, 30);
        } else {
            this.setAnimation(EndlessRunner.get().getTextureCache().load("player_running.png"), 6, 1, 5);
        }
        if (dead || b.getLinearVelocity().x < maxSpeed) {
            b.applyLinearImpulse(speed.x, speed.y, b.getWorldCenter().x, b.getWorldCenter().y, true);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash * super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player that = (Player) obj;
        return super.equals(obj) && this.speed.equals(that.speed);
    }

    /**
     * The function that let the player jump up. (falling down is done by the
     * gravity). NOTE: the actual movement of the player is done in the
     * updateJumpPosition(float) method.
     *
     * @param jumpIntensity
     *            How hard the player jumps.
     */
    public void jump(final float jumpIntensity) {
        Body b = getPhysicsBody();
        if (b.getLinearVelocity().y > -0.6f && b.getLinearVelocity().y < 0.3f) {
            b.applyLinearImpulse(0,
                    -b.getWorld().getGravity().y + 20 + (10 * jumpIntensity),
                    b.getWorldCenter().x,
                    b.getWorldCenter().y,
                    true);
        }
    }

    /**
     * Return the speed of the player.
     *
     * @return speed of the player.
     */
    public Vector2 getSpeed() {
        return speed;
    }
}
