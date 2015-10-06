package com.group5.core.util;

import java.util.ArrayList;

/**
 * This class keeps of the 10 highest scores.
 * @author Marcel
 *
 */
public abstract class ScoreContainer {

    /**
     * List with score items.
     */
    private static ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();

    /**
     * Set up the starting scores, which are retrieved from the properties file.
     * @param scoreList List of scores that are retrieved from the properties file
     */
    public static void initialize(final ArrayList<ScoreItem> scoreList) {
        list.addAll(scoreList);
        System.out.println("gelukt!");
        System.out.println(list.size());
    }



}
