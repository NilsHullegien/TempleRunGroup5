package com.group5.core.util;

import java.util.ArrayList;

/**
 * This class keeps of the 10 highest scores.
 * @author Marcel
 *
 */
public abstract class ScoreContainer {

    /**
     *TODO handle the file name differently.
     * The file that the scorewriter should write to.
     */
    private static String infile = "scores.properties";

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
    }

    /**
     * Check whether this score is in the top 10 of best scores. If it is, make place for it and add it to the list.
     * @param score Score you want to check for
     */
    public static void addScore(final int score) {

        //TODO implement a way to change names
        String name = "placeholder";
        int lowest = list.size() + 1;
        for (int i = 0; i < list.size(); i++) {
            if (score > list.get(i).getScore() && list.get(i).getRank() < lowest) {
                lowest = list.get(i).getRank();
            }
        }
        if (lowest <= 10) {
            shift(lowest);
            list.add(new ScoreItem(lowest, score, name));
            ScoreWriter.writeScore(list, infile);
        }
    }

    /**
     * Shift all scores starting at a given rank down by one. If its rank now is higher than 10, remove it from the list.
     * @param rank Shift all scores down by one starting at this rank
     */
    private static void shift(final int rank) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRank() >= rank) {
              list.get(i).setRank(list.get(i).getRank() + 1);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRank() > 10) {
                list.remove(i);
            }
        }

    }

    /**
     * Return the list with score items.
     * @return The list of score items
     */
    public static ArrayList<ScoreItem> getList() {
        return list;
    }

    /**
     * Update the list.
     * @param newList The new list
     */
    public static void setList(final ArrayList<ScoreItem> newList) {
        list = newList;
    }

}