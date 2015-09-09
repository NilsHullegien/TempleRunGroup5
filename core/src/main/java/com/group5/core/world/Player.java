package com.group5.core.world;

import java.awt.Frame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.EndlessRunner;
import com.group5.core.util.KeyMap;

/**
 * Represents a game character controlled by the user.
 */
public class Player extends WorldObject {
    /**
     * The current speed the player is moving at.
     */
    private Vector2 speed;

    private Vector2 gravity;
    
    /**
     * Constructs a new Player positioned at the given coordinates.
     *
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     */
    public Player(final float x, final float y) {
        super(EndlessRunner.get().getTextureCache().load("playerBlock.png"), x, y);
        speed = new Vector2(250, 0);
        gravity = new Vector2(0, -150.f);
    }

    @Override
    public void update(final float delta, final World world) {
        /*if (Gdx.input.isKeyJustPressed(KeyMap.RIGHT)) {
            speed.add(200.f, 0.f);
        } else if (!Gdx.input.isKeyPressed(KeyMap.RIGHT) && speed.x > 0.f) {
            speed.x = 0.f;
        }
        if (Gdx.input.isKeyJustPressed(KeyMap.LEFT)) {
            speed.add(-200.f, 0.f);
        } else if (!Gdx.input.isKeyPressed(KeyMap.LEFT) && speed.x < 0.f) {
            speed.x = 0.f;
        }*/

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
     * The function that let the player jump up.
     * (falling down is done by the gravity).
     * NOTE: the actual movement of the player is done in the
     * updateJumpPosition(float) method.
     * @param jumpTime The time the player jumps 
     */
    public void jump(float jumpTime) {
    	//Basic speed is the starting speed of the player
    	//This it will go down to 0
    	double basicSpeed = jumpTime/20;
    	double heightIncr = basicSpeed;
    	double decr =  2;
    	System.out.println(basicSpeed);
    	while(heightIncr >= 0) {
    		System.out.println("Height increase: " + heightIncr);
    		heightIncr = basicSpeed - decr;
    		decr *= 1.2;
    		if(heightIncr < 0) break;
    		System.out.println("jumpPosition(" + heightIncr + ")");
    		updatePlayer((float) heightIncr);
    	}
    }
    
    /**
     * Updates the position of the player
     * @param y
     */
    public void updatePlayer(float y) {
        setY(y - this.getGravity().y);
    }
    
    public Vector2 getGravity() {
    	return gravity;
    }

}
