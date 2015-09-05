package com.group5.core.world;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.controllers.CollisionChecker;

public class World {
    private CollisionChecker collider;
	private ArrayList<WorldObject> objects;
    private Vector2 gravity;
	
	/**
	 * A world serves as a container of all object that have been spawned
	 */
	public World() {
        collider = new CollisionChecker(this);
        gravity = new Vector2(0, -150.f);
        objects = new ArrayList<WorldObject>();
    }
	
	/**
	 * Add an object to the world
	 * @param obj	Object you want to add to the world
	 */
	public void add(WorldObject obj){
		objects.add(obj);
	}

    public CollisionChecker getCollider() {
        return collider;
    }

    public Vector2 getGravity() {
        return gravity;
    }

	public ArrayList<WorldObject> getObjects(){
		return objects;
	}

    public void update(float delta) {
        for (WorldObject w : objects) {
            w.update(delta, this);
        }
    }
	
}
