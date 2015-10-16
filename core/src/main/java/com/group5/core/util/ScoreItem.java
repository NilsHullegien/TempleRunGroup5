package com.group5.core.util;

/**
 * This class creates a score item, that consists of a rank, a score and a name.
 *
 * @author Marcel
 */
public class ScoreItem {

    /**
     * The rank.
     */
    private int rank;

    /**
     * The score.
     */
    private int score;

    /**
     * The name.
     */
    private String name;

    /**
     * The date.
     */
    private String date;

    /**
     * Create a score item that consists of a rank, a score and a name.
     *
     * @param rankValue  the rank
     * @param scoreValue the score
     * @param nameValue  the name
     * @param dateValue  the date.
     */
    public ScoreItem(final int rankValue, final int scoreValue, final String nameValue, final String dateValue) {
        rank = rankValue;
        score = scoreValue;
        name = nameValue;
        date = dateValue;
    }

    /**
     * Return the rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Set a new rank value for this score item.
     *
     * @param rankValue the new rank value
     */
    public void setRank(final int rankValue) {
        rank = rankValue;
    }

    /**
     * Return the score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Return the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the date.
     *
     * @return the date.
     */
    public String getDate() {
        return date;
    }
}

