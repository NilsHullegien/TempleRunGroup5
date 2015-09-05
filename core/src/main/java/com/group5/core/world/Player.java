package com.group5.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import com.group5.core.controllers.CollisionChecker;
import com.group5.core.util.KeyMap;

public class Player extends WorldObject {
    private Vector2 speed;
	
	/**
	 * The player is the character that is directly controlled by the user
	 * @param x		Starting x-coordinate
	 * @param y		Starting y-coordinate
	 */
	public Player(float x, float y) {
		super(Gdx.files.internal("playerBlock.png"), x, y);
        speed = new Vector2(0, 0);
	}

    @Override
    public void update(float delta, World world) {
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
    }

}
