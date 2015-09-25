package com.group5.core;

import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.group5.core.world.DirPlayerTextures;

public class DirPlayerTexturesTest {

    private DirPlayerTextures directory;
    
    private Texture jumpingTex;
    
    private Texture runningTex;
    
    /**
     * Setup for this test class.
     */
    @Before
    public void setup() {
        directory = new DirPlayerTextures();
        jumpingTex = EndlessRunner.get().getTextureCache().load("chickentimejumptest.png");
        runningTex = EndlessRunner.get().getTextureCache().load("chickentime.png");
    }
    
    /**
     * Test to see if the getTexture method works.
     * 0 for jumpingTexture.
     * 1 for runningTexture.
     */
    @Test
    public void getTextureTest() {
        //Default textures should be added in the constructor, let's test that.
        assertTrue(directory.getTexture(0).equals(jumpingTex));
        assertTrue(directory.getTexture(1).equals(runningTex));
    }
}
