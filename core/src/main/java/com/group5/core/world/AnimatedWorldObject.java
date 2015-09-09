package com.group5.core.world;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents an object that has a presence in a World.
 */
public abstract class AnimatedWorldObject extends WorldObject {
	
	private int frame_cols;
	private int frame_rows;
	private int amount_frames;
	private float currenttime = 0;
	private Animation animation;
	private TextureRegion currentFrame;
	private float animationduration;
	
	private int framestate=0;
	
    public AnimatedWorldObject(final Texture tex,
                       final float xCoord,
                       final float yCoord,
                       final int frame_cols,
                       final int frame_rows,
                       float animationduration) {
        super(tex, xCoord, yCoord);
        this.frame_cols = frame_cols;
        this.frame_rows = frame_rows;
        this.amount_frames = frame_cols*frame_rows;
        this.animationduration = animationduration;
        this.animation = createFrames(tex,frame_cols, frame_rows);
            }
    
     public Animation createFrames(Texture tex,int frame_cols, int frame_rows){
    	TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth()/frame_cols, tex.getHeight()/frame_rows);
    	TextureRegion[] frames = new TextureRegion[amount_frames];
    	int index =0;
    	for (int i = 0; i<frame_rows; i++){
    		for (int j =0; j < frame_cols; j++) {
    			System.out.println("i="+i+", j="+j);
    			frames[index++] = tmp [i][j];
    		}
    	}
    	return new Animation(animationduration/amount_frames, frames);
    }
    
    public void doRender(SpriteBatch batch){
    	System.out.println(animation.getKeyFrameIndex(currenttime));
       	batch.draw(animation.getKeyFrame(currenttime, true), x,y);
    }
    
    public void update(final float delta, World world){
    	currenttime += delta;
    }
    
    
   
    
}
