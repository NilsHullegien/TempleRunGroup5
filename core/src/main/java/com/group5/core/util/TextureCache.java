package com.group5.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * A cache that lazily fetches textures when first loaded.
 */
public class TextureCache {

    /**
     * Cached textures.
     */
    private HashMap<String, Texture> textures;

    /**
     * Constructs a new, empty texture cache.
     */
    public TextureCache() {
        textures = new HashMap<String, Texture>();
    }

    /**
     * Returns the texture from the passed in file name.
     * If the Texture has already been loaded, it will be retrieved from cache.
     * If the Texture has not yet been loaded, it will be retrieved from disk.
     *
     * @param textureFile the file path at which the texture is found.
     * @return the requested Texture
     */
    public Texture load(final String textureFile) {
        if (textures.containsKey(textureFile)) {
            return textures.get(textureFile);
        } else {
            Gdx.app.log("TextureCache", "Loading texture: " + textureFile);
            Texture t = new Texture(Gdx.files.internal(textureFile));
            textures.put(textureFile, t);
            return t;
        }
    }

    /**
     * Clears all cached textures from memory.
     */
    public void clear() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
        textures.clear();
    }

    /**
     * Uncaches the given texture file if already loaded.
     *
     * @param textureFile the texture to be uncached.
     */
    public void uncache(final String textureFile) {
        if (textures.containsKey(textureFile)) {
            Texture t = textures.get(textureFile);
            t.dispose();
            textures.remove(textureFile);
        }
    }

    /**
     * Disposes of all textures in the cache safely.
     */
    public void dispose() {
        clear();
    }

}
