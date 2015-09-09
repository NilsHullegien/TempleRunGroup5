package com.group5.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;
import com.group5.core.util.KeyMap;

/**
 * Represents a game character controlled by the user.
 */
public class Player extends AnimatedWorldObject {
    /**
     * The current speed the player is moving at.
     */
    private Vector2 speed;

    /**
     * Constructs a new Player positioned at the given coordinates.
     *
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     */
    public Player(final float x, final float y) {
        super(EndlessRunner.get().getTextureCache().load("chickentime.png")
        , x, y, 6, 5, 2);
        speed = new Vector2(250, 0);
        speed = new Vector2(0, 0);
    }

    @Override	
    public void update(final float delta, final World world) {
        if (Gdx.input.isKeyJustPressed(KeyMap.RIGHT)) {
            speed.add(200.f, 0.f);
        } else if (!Gdx.input.isKeyPressed(KeyMap.RIGHT) && speed.x > 0.f) {
            speed.x = 0.f;
        }
        if (Gdx.input.isKeyJustPressed(KeyMap.LEFT)) {
            speed.add(-200.f, 0.f);
        } else if (!Gdx.input.isKeyPressed(KeyMap.LEFT) && speed.x < 0.f) {
            speed.x = 0.f;
        }

        // TODO: There's a cleaner way to handle this
        float oldX = getX();
        setX(oldX + speed.x * delta + world.getGravity().x * delta);
        if (world.getCollider().checkCollision(this)) {
            setX(oldX);
        }

        float oldY = getY();
        setY(oldY + speed.y * delta + world.getGravity().y * delta);
        if (world.getCollider().checkCollision(this)) {
            setY(oldY);
        }
        //update the animation
        super.update(delta, world);
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
   

}
