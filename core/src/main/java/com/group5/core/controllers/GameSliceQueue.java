package com.group5.core.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

public class GameSliceQueue {
    /**
    ** The GameSliceQueue container.
    */
	private LinkedList<GameSlice> slicequeue;
	/**
	 * Number of slices in the queue.
	 */
	private int slices;
	/**
	 * @param num number of slices in the queue.
	 */
	public GameSliceQueue(final int num) {
		this.slicequeue = new LinkedList<GameSlice>();
		this.slices = num;
	}
	/**
	 * 
	 * @throws Exception
	 */
	/**
	public void fill() throws Exception {
		if (!isEmpty()) {
			throw new Exception("GameSliceQueue is not empty");
		}
		for(int i = 0; i < slices; i++) {
	          addGameSlice(new GameSlice());
		}
	}
	*/
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return slicequeue.isEmpty();
	}
	/**
	 * 
	 * @param param
	 */
	/**
	public void addGameSlice(Object variables) throws IllegalStateException{
	    addGameSlice(new GameSlice(variables));
	}
	*/
	
	public void addGameSlice(GameSlice g) throws IllegalStateException {
	    if (full()) {
	        if (slicequeue.peekFirst().hasPlayer()) {
	            throw new IllegalStateException("Cannot remove slice that has player");
	        }
	        slicequeue.poll();
	    }
	    slicequeue.offer(g);
	}
	/**
	 * 
	 * @return
	 */
	public int length() {
	    int i = 0;
	    Iterator<GameSlice> it = slicequeue.iterator();
	    while (it.hasNext()) {
	        i++;
	        it.next();
	    }
	    return i;
	}
	/**
	 * 
	 * @return
	 */
	public boolean full() {
	    return length() >= slices;
	}
	
	public Iterator getOnScreenSlices() {
        LinkedList<GameSlice> ll = new LinkedList<GameSlice>();
        for (GameSlice g : slicequeue) {
            if(g.isonScreen()) {
                ll.add(g);
            }
        }
        return ll.iterator();
	}
	public Iterator getSliceIterator() {
	    return slicequeue.iterator();
	}
    public void update(Vector2 playerpos, Vector2 camerapos) {
        GameSlice curr = slicequeue.peek();
        while(curr.isonScreen()) {
            curr.update(playerpos, camerapos);
        }
    }
    public GameSlice getLast() {
        return slicequeue.getLast();
    }
}
