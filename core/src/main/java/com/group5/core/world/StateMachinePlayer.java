package com.group5.core.world;

/**
 * Class that checks the state of the player in the game. 
 */
public class StateMachinePlayer {
    
    /**
     * True if player is jumping
     */
    private static boolean isJumping = false;

    /**
     * True if player is running
     */
    private static boolean isRunning = false;
    
    
    public static void checkPlayerState(final World w) {
        Player p = w.getPlayer();
        resetStates();
        System.out.println(p.getY());
        //If the player doesn't collide with anything, it must be jumping
        if(w.getCollider().checkCollision(p)) {
            System.out.println("RUNNING");
            isRunning = true;
        }
        //Else the player must be running
        else {
            System.out.println("JUMPING");
            isJumping = true;
        }
    }
    
    /**
     * Sets the state of jumping and running to false. 
     */
    public static void resetStates() {
        isJumping = false;
        isRunning = false;
    }
}
