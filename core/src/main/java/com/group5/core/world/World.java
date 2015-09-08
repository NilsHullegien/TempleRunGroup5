package com.group5.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Functions as a container for spawned items in the game.
 */
public class World {
    /**
     * The collision checker that is used to verify this world's state.
     */
    private CollisionChecker collider;

    /**
     * The objects that are present in this world.
     */
    private ArrayList<WorldObject> objects;

    /**
     * The gravity this world is operating in.
     */
    private Vector2 gravity;


    /**
     * The current player in the game.
     * A game can only have one player at any given time.
     */
    private Player player;

    /**
     * The button pressed for jumping
     */
    private char jumpButton = Keys.W;
    
    /**
     * Timer start and stop for determining 
     * how long the player jumps
     */
    private long timerStart = 0L;
    
    /**
     * Constructs a new, empty world with a default gravity.
     */
    public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, -150.f);
        objects = new ArrayList<WorldObject>();
        Gdx.input.setInputProcessor(ip);
    }

    /**
     * Adds an object to the world.
     *
     * @param object Object you want to add to the world
     */
    public void add(final WorldObject object) {
        objects.add(object);
    }

    /**
     * Returns the world's collider.
     * @return the world's collider.
     */
    public CollisionChecker getCollider() {
        return collider;
    }

    /**
     * Returns the world's operating gravity.
     * @return the world's operating gravity.
     */
    public Vector2 getGravity() {
        return gravity;
    }

    /**
     * Returns the objects contained in the world.
     * @return the objects contained in the world.
     */
    public List<WorldObject> getObjects() {
        return objects;
    }

    /**
     * Set the (new) current player.
     * @param p The new player.
     */
    public void setPlayer(final Player p) {
        objects.remove(player);
        player = p;
        objects.add(p);
    }

    /**
     * Return the current player.
     * @return the current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Updates all objects present in the world.
     * @param delta the time that has passed since the previous frame.
     */
    public void update(final float delta) {
        for (WorldObject w : objects) {
            w.update(delta, this);
        }
    }
    
InputProcessor ip = new InputProcessor() {
    	
    	@Override
		public boolean keyUp(int keycode) {
			if(keycode == jumpButton) {
				System.out.println("jumpButton is up!");
				long jumpTime = System.currentTimeMillis() - timerStart;
				System.out.println("Key held down for " + jumpTime + " ms.");
				if(jumpTime >= 1000L) {
					System.out.println("Held down for too long: (" + jumpTime + ").");
					jumpTime = 1000L;
				}
				System.out.println("Value going to player.jump(): " + jumpTime);
				getPlayer().jump(jumpTime);
			}
			return false;
		}
		
		@Override
		public boolean keyDown(int keycode) {
			if(keycode == jumpButton) {
				System.out.println("jumpButton pressed down");
				timerStart = System.currentTimeMillis();
			}
			return false;
		}
    	
    	
    	//////////////////
    	//UNUSED METHODS//
		//////////////////
		
		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}
		
	
		
		
		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}
		
		//////////////////////
		//END UNUSED METHODS//
		//////////////////////
	};

}
