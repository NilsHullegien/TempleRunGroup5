package com.group5.core.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.group5.core.EndlessRunner;

/**
 * In this class we store the textures for the player.
 * @author Nils
 */
public class DirPlayerTextures {

    /**
     * List in which the player are stored.
     */
    private ArrayList<Texture> tlist;

    /**
     * Directory in which the textures for the player are stored.
     */
    public DirPlayerTextures() {
        tlist = new ArrayList<Texture>();
        addDefaultTextures();
    }

    /**
     * This methods adds the different textures in an arraylist<Texture>.
     */
    private void addDefaultTextures() {
        if (tlist.size() != 0) {
            throw new Error("Something went wrong, mr/ms programmer...");
        }
        Texture t1 = EndlessRunner.get().getTextureCache().load("chickentimejumptest.png");
        Texture t2 = EndlessRunner.get().getTextureCache().load("chickentime.png");
        tlist.add(t1);
        tlist.add(t2);
    }

    /**
     * Get the texture for the player.
     * @param i 0 for jumping, 1 for running.
     * @return the texture for the player.
     */
    public Texture getTexture(final int i) {
        return tlist.get(i);
    }
}
