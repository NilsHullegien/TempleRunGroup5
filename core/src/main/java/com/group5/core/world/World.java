package com.group5.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;
import com.group5.core.controllers.Spawner;

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
     * Gets the time the player held down the jumpButton.
     * Getter and setter are provided.
     */
    private long jumpTime;

    /**
     * The current player in the game.
     * A game can only have one player at any given time.
     */
    private Player player;

    /**
     * The button pressed for jumping.
     */
    private char jumpButton = Keys.W;
    
    /**
     * Timer start and stop for determining how long the player jumps.
     */
    private long timerStart = 0L;
    /**
     * The spawner that spawns new objects into the world.
     */
    private Spawner spawner;
    
    /**
     * Constructs a new, empty world with a default gravity.
     */
    public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, -150.f);
        objects = new ArrayList<WorldObject>();
        Gdx.input.setInputProcessor(ip);
        spawner = new Spawner(this);
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
     *
     * @return the world's collider
     */
    public CollisionChecker getCollider() {
        return collider;
    }

    /**
     * Returns the world's operating gravity.
     *
     * @return the world's operating gravity
     */
    public Vector2 getGravity() {
        return gravity;
    }

    /**
     * Returns the objects contained in the world.
     *
     * @return the objects contained in the world.
     */
    public List<WorldObject> getObjects() {
        return objects;
    }

    /**
     * Returns the world's spawner.
     * @return Spawner which spawns new objects into the world.
     */
    public Spawner getSpawner() {
    	return spawner;
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
     *
     * @param delta the time that has passed since the previous frame.
     */
    public void update(final float delta) {
    	spawner.spawnBlocks();
        for (WorldObject w : objects) {
            w.update(delta, this);
        }
    }
    /**
     * Input processor used in LibGDX.
     * Registers when key is pressed/released
     */
    InputProcessor ip = new InputProcessor() {
    	
    	/**
    	 * Registers a released button.
    	 * @param keycode The integer representation of the button released.
    	 */
    	@Override
		public boolean keyUp(final int keycode) {
    		System.out.println("Player Y: " + player.getY());
			if (keycode == jumpButton) {
				jumpTime = System.currentTimeMillis() - timerStart;
				if (jumpTime >= 1000L) {
					jumpTime = 1000L;
				}
				player.setIsJumping(true);
			}
			return false;
		}
		
    	/**
    	 * Registers a pressed button.
    	 * @param keycode The integer representation of the button pressed.
    	 */
		@Override
		public boolean keyDown(final int keycode) {
			if (keycode == jumpButton && player.getY() <= 65) {
				System.out.println("Timer started");
				timerStart = System.currentTimeMillis();
			}
			return false;
		}
    	
    	
    	//////////////////
    	//UNUSED METHODS//
		//////////////////
		/**
		 * Unused.
		 */
		@Override
		public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
			return false;
		}
		
		/**
		 * Unused.
		 */
		@Override
		public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
			return false;
		}
		
		/**
		 * Unused.
		 */
		@Override
		public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
			return false;
		}
		
		/**
		 * Unused.
		 */
		@Override
		public boolean scrolled(final int amount) {
			return false;
		}
		
		/**
		 * Unused.
		 */
		@Override
		public boolean mouseMoved(final int screenX, final int screenY) {
			return false;
		}
		
		/**
		 * Unused.
		 */
		@Override
		public boolean keyTyped(final char character) {
			return false;
		}
		
		//////////////////////
		//END UNUSED METHODS//
		//////////////////////
	};
	
	/**
	 * Sets the jumpTime variable.
	 * @param newTime The time jumpTime needs to be set to.
	 */
	public void setJumpTime(final long newTime) {
		jumpTime = newTime;
	}
	
	
	/**
	 * Returns the jumpTime variable.
	 * @return the jumpTime variable.
	 */
	public long getJumpTime() {
		return jumpTime;
	}

}
