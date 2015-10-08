package com.group5.core.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.util.Logger;

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
	 * @param queue in playerposition
	 */
    private int playerinqueue;
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
	
	//public void setPlayerinQueue(int i) {
	//    this.playerinqueue = i;
	//}
	
    // for now do without variables but iterate for this value,
    // because this way less overhead
	public int getPlayerinQueue() throws Exception {
    int count = 0;
    Iterator<GameSlice> it = slicequeue.iterator();
    boolean b = true;
    while (it.hasNext()) {
        if(!it.hasNext()){
            b = false;
        }
        GameSlice curr = it.next();
        if (curr.hasPlayer()){
            return count;
    }
        count++;
    }
    /*
    while(!it.next().hasPlayer()) {
        count++;
        if(!it.hasNext()){
            System.out.println("nah");
            throw new Exception("Player is not in a slice");
        }
    }
    */
    //return count;
    //throw new Exception("Player is not in a slice");
    return 2000;
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
	    Logger.get().info("GameSliceQueue", "Creating");
	}
	/**
	 * 
	 * @return
	 */
	public int length() {
	    if(slicequeue.isEmpty()) {
	        return 0;
	    }
	    int i = 1;
	    Iterator<GameSlice> it = slicequeue.iterator();
	    it.next();
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
	
    public Vector2 startPointslice(int slice) {
        if(slice>slices || slice < 0){
            new Exception("that slice does not exist");
        }
        Iterator<GameSlice> it = getSliceIterator();
        GameSlice curr = it.next();
        int count = 0;
        if (count ==0 && !it.hasNext() && length()==2){ 
            System.out.println("NO WAY");
        }
        while(!(count == slice)) {
                curr = it.next();
                count++;
            }
        return curr.startPoint;
        
        /*
        for(int i=1; i <= slice; i++){
            System.out.println(i);
            curr = it.next();
        }
        System.out.println(curr.startPoint.x+"man");
        */
        //return curr.getstartPoint();

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
        Iterator<GameSlice> it = getSliceIterator();
        while(it.hasNext()){
            it.next().update(playerpos, camerapos);
        }
    }
    public GameSlice getLast() {
        return slicequeue.getLast();
    }
}
