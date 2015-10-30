package com.group5.core.util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.group5.core.GdxTestRunner;

/**
 * Tests for the TextureCache.
 */
@RunWith(GdxTestRunner.class)
public class TextureCacheTest {
    private TextureCache cache;

    /**
     * Sets up a texture cache for testing use.
     */
    @Before
    public void setUp() {
        cache = new TextureCache();
    }

    /**
     * Disposes of the testing cache.
     */
    @After
    public void tearDown() {
        cache.dispose();
        cache = null;
    }

    /**
     * Tests that an existing texture is correctly loaded by the cache.
     */
    @Test
    public void testCacheLoadsTexture() {
        Texture t = cache.load("floorTile.png");

        assertNotNull(t);
    }

    /**
     * Tests that an exception is thrown if a nonexistent texture is loaded.
     */
    @Test(expected = GdxRuntimeException.class)
    public void testLoadNonExistentThrows() {
        cache.load("this.neverexists");
    }

    /**
     * Tests that consecutively loading a texture returns the same texture twice (by reference).
     */
    @Test
    public void testCacheReturnsSameTextureOnLoadTwice() {
        Texture t1 = cache.load("floorTile.png");
        Texture t2 = cache.load("floorTile.png");

        assertSame(t1, t2);
    }

    /**
     * Tests that loading different texture files returns different textures.
     */
    @Test
    public void testCacheReturnsDifferentTexturesForDifferentFiles() {
        Texture t1 = cache.load("floorTile.png");
        Texture t2 = cache.load("playerBlock.png");

        assertNotEquals(t1, t2);
    }

    /**
     * Tests that different texture caches contain different textures for the same file.
     */
    @Test
    public void testTextureCachesAreIndependent() {
        TextureCache newCache = new TextureCache();

        Texture t1 = cache.load("floorTile.png");
        Texture t2 = newCache.load("floorTile.png");

        assertNotEquals(t1, t2);
    }

    /**
     * Tests that a new texture is loaded after an old one has been uncached.
     */
    @Test
    public void testCacheReturnsDifferentTextureAfterUncache() {
        Texture t1 = cache.load("floorTile.png");
        cache.uncache("floorTile.png");
        Texture t2 = cache.load("floorTile.png");

        assertNotEquals(t1, t2);
    }

    /**
     * Tests that uncaching an unloaded texture does not fail.
     */
    @Test
    public void testUncacheUnloadedSucceeds() {
        cache.uncache("floorTile.png");
    }

    /**
     * Tests that a new texture is loaded after the cache has been cleared.
     */
    @Test
    public void testClearedCacheReturnsDifferentTexture() {
        Texture t1 = cache.load("floorTile.png");
        cache.clear();
        Texture t2 = cache.load("floorTile.png");

        assertNotEquals(t1, t2);
    }
}
