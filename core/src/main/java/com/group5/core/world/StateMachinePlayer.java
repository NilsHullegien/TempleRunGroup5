package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class that checks the state of the player in the game.
 * @author Nils.
 */
public class StateMachinePlayer {

    /**
     * Directory in which the textures for the player are stored.
     */
    private DirPlayerTextures directory;

    /**
     * A player, in constructor will be changed to the actual player.
     */
    private Player player;

    /**
     * Running animation, created in the constructor.
     */
    private Animation runningAnim;

    /**
     * Jumping animation, created in the constructor.
     */
    private Animation jumpingAnim;

    /**
     * Constructor for the StateMachine player.
     * @param p The player of which the state must be determined.
     */
    public StateMachinePlayer(final Player p) {
        //TODO: There are probably way more efficient ways to code the creation
        //TODO: Of the animations.
        directory = new DirPlayerTextures();
        player = p;
        Texture runTex = directory.getTexture(1);
        Texture jumpTex = directory.getTexture(0);
        int framecols = 6;
        int framerows = 5;

        //Creation of the runningAnim
        TextureRegion[][] tmp1 = TextureRegion.split(runTex,
                runTex.getWidth() / framecols,
                runTex.getHeight() / framerows);
        TextureRegion[] frames1 = new TextureRegion[player.getAmountFrames()];
        int index1 = 0;
        for (int i = 0; i < framerows; i++) {
            for (int j = 0; j < framecols; j++) {
                frames1[index1++] = tmp1 [i][j];
            }
        }
        runningAnim = new Animation(player.getAnimationDuration() / player.getAmountFrames(), frames1);

      //Creation of the runningAnim
        TextureRegion[][] tmp2 = TextureRegion.split(jumpTex,
                jumpTex.getWidth() / framecols,
                jumpTex.getHeight() / framerows);
        TextureRegion[] frames2 = new TextureRegion[player.getAmountFrames()];
        int index2 = 0;
        for (int i = 0; i < framerows; i++) {
            for (int j = 0; j < framecols; j++) {
                frames2[index2++] = tmp2 [i][j];
            }
        }
        jumpingAnim = new Animation(player.getAnimationDuration() / player.getAmountFrames(), frames2);
    }

    /**
     * This method checks the state of the player.
     * Currently, the states can be running or jumping.
     * In this method, the animations and the texture are set as well.
     * @param w the world in which the player is.
     */
    public void checkPlayerState(final World w) {
        Player p = w.getPlayer();
        //If the player doesn't collide with anything, it must be jumping
        if (w.getCollider().checkCollision(p)) {
            p.setTexture(directory.getTexture(1));
            p.setAnimation(runningAnim);
        } else {
          //Else the player must be running
            p.setTexture(directory.getTexture(0));
            p.setAnimation(jumpingAnim);
        }
    }
}
