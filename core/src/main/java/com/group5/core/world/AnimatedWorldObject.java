package com.group5.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents an object that has a presence in a WorldManager.
 */
public abstract class AnimatedWorldObject extends WorldObject {
    /**
     * Number that is the framecount.
     */
    private int amountframes;
    /**
     * Time corresponding to frame.
     */
    private float currenttime = 0;
    /**
     * Contains all the frames and is callable through time parameter.
     */
    private Animation animation;
    /**
     * The total duration of a animationcycle.
     */
    private float animationduration;

    /**
     * Object in the world that shows a animated sprite.
     * @param tex Texture
     * @param size Screen size of the object
     * @param coord Initial position
     * @param framecols Amount of horizontal slices of texture
     * @param framerows Amount of vertical slices of texture
     * @param duration Total duration of animation
     */
    public AnimatedWorldObject(final Texture tex,
                               final Vector2 size,
                               final Vector2 coord,
                               final int framecols,
                               final int framerows,
                               final float duration) {
        super(tex, size, coord);
        this.amountframes = framecols * framerows;
        this.animationduration = duration;
        this.animation = createFrames(tex, framecols, framerows);
    }

    /**
     * Creates an Animation.
     * @param tex Texture used
     * @param framecols framecols Amount of horizontal slices of texture
     * @param framerows framerows Amount of vertical slices of texture
     * @return Animation
     */
     public Animation createFrames(final Texture tex,
             final int framecols,
             final int framerows) {
        TextureRegion[][] tmp = TextureRegion.split(tex,
                tex.getWidth() / framecols,
                tex.getHeight() / framerows);
        TextureRegion[] frames = new TextureRegion[amountframes];
        int index = 0;
        for (int i = 0; i < framerows; i++) {
            for (int j = 0; j < framecols; j++) {
                frames[index++] = tmp [i][j];
            }
        }
        return new Animation(animationduration / amountframes, frames);
    }

     /**
      * Render the animation.
      * @param batch The batch in which the animatedsprite should be rendered.
      */
    public void doRender(final SpriteBatch batch) {
           batch.draw(animation.getKeyFrame(currenttime, true), this.getX(), this.getY(), getWidth(), getHeight());
    }
    /**
     * Update the animationframe, should be called per frame.
     * @param delta The difference in time since the last update
     * @param worldManager The worldManager the object is in
     */
    public void update(final float delta, final WorldManager worldManager) {
        currenttime += delta;
    }
}
