package com.group5.core.controllers;

import com.badlogic.gdx.math.Vector2;
import com.group5.core.world.WorldObject;
import java.util.LinkedList;

/**
 * GameSlice is a 2Dimensional rectangle in which objects are placed.
 */
public abstract class StartGameSlice extends SequencedGameSlice {
    /**
     * Constructor without previous gameslice.
     *
     * @param sP startpoint
     * @param eP endpoint
     */
    public StartGameSlice(final Vector2 sP, final Vector2 eP) {
        super(null, sP, eP);
        this.setStartPoint(sP);
        this.setEndPoint(eP);
        setElems(new LinkedList<WorldObject>());
    }
}
