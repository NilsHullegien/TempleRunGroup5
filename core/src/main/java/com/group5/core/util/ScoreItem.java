package com.group5.core.util;

/**
 * This class creates a score item, that consists of a rank, a score and a name.
 * @author Marcel
 *
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
     * Create a score item that consists of a rank, a score and a name.
     * @param rankValue the rank
     * @param scoreValue the score
     * @param nameValue the name
     */
    public ScoreItem(final int rankValue, final int scoreValue, final String nameValue) {
        rank = rankValue;
        score = scoreValue;
        name = nameValue;
    }

    /**
     * Return the rank.
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Return the score.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Return the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new rank value for this score item.
     * @param rankValue the new rank value
     */
    public void setRank(final int rankValue) {
        rank = rankValue;
    }
}

