package com.group5.core.util;

import com.group5.core.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class ScoreContainerTest {


    @Before
    public void setUp() {
        ScoreContainer.setList(new ArrayList<ScoreItem>());
    }

    /**
     * Initialize the ScoreContainer with an empty list.
     */
    @Test
    public void initializeEmptyListTest() {
        ScoreContainer.initialize(new ArrayList<ScoreItem>());
        ;
        assertTrue(ScoreContainer.getList().isEmpty());
    }


    /**
     * Initialize the ScoreContainer with one element.
     */
    @Test
    public void initializeOneElement() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name", "date"));
        ScoreContainer.initialize(list);
        assertTrue(ScoreContainer.getList().size() == 1);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 2);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("name"));
    }

    /**
     * Initialize the ScoreContainer with multiple elements.
     */
    @Test
    public void initializeMultipleElements() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name1", "date"));
        list.add(new ScoreItem(3, 4, "name2", "date"));
        list.add(new ScoreItem(5, 6, "name3", "date"));
        ScoreContainer.initialize(list);
        assertTrue(ScoreContainer.getList().size() == 3);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 4);
        assertTrue(ScoreContainer.getList().get(2).getName().equals("name3"));

    }

    /**
     * Add a score to an empty list.
     */
    @Test
    public void addScoreEmptyListTest() {
        ScoreContainer.addScore(10, "placeholder", "date");
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    /**
     * Insert a new high score into a non-empty list.
     */
    @Test
    public void addScoreNewBestTest() {
        ScoreContainer.addScore(10, "placeholder", "date");
        ScoreContainer.addScore(11, "placeholder", "date");
        assertTrue(ScoreContainer.getList().get(1).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 11);
        assertTrue(ScoreContainer.getList().get(1).getName().equals("placeholder"));
        assertTrue(ScoreContainer.getList().get(0).getRank() == 2);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    /**
     * Insert a score that is not the best score.
     */
    @Test
    public void addScoreNewNotBestTest() {
        ScoreContainer.addScore(11, "placeholder", "date");
        ScoreContainer.addScore(10, "placeholder", "date");
        assertTrue(ScoreContainer.getList().get(1).getRank() == 2);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(1).getName().equals("placeholder"));
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 11);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    /**
     * Insert a new high score into a list that contains 10 items.
     */
    @Test
    public void addScoreNewBestTenElementsTest() {

        ScoreContainer.addScore(1, "placeholder", "date");
        ScoreContainer.addScore(2, "placeholder", "date");
        ScoreContainer.addScore(3, "placeholder", "date");
        ScoreContainer.addScore(4, "placeholder", "date");
        ScoreContainer.addScore(5, "placeholder", "date");
        ScoreContainer.addScore(6, "placeholder", "date");
        ScoreContainer.addScore(7, "placeholder", "date");
        ScoreContainer.addScore(8, "placeholder", "date");
        ScoreContainer.addScore(9, "placeholder", "date");
        ScoreContainer.addScore(10, "placeholder", "date");

        ScoreContainer.addScore(11, "placeholder", "date");
        int lowest = 11;
        for (int i = 0; i < ScoreContainer.getList().size(); i++) {
            if (ScoreContainer.getList().get(i).getScore() < lowest) {
                lowest = ScoreContainer.getList().get(i).getScore();
            }
        }
        assertTrue(lowest == 2);
    }

    /**
     * Insert an element into a list that has 10 element.
     * The new score is worse than the number 10 score and will not appear in the list.
     */
    @Test
    public void addScoreNewWorstTenElementsTest() {

        ScoreContainer.addScore(1, "placeholder", "date");
        ScoreContainer.addScore(2, "placeholder", "date");
        ScoreContainer.addScore(3, "placeholder", "date");
        ScoreContainer.addScore(4, "placeholder", "date");
        ScoreContainer.addScore(5, "placeholder", "date");
        ScoreContainer.addScore(6, "placeholder", "date");
        ScoreContainer.addScore(7, "placeholder", "date");
        ScoreContainer.addScore(8, "placeholder", "date");
        ScoreContainer.addScore(9, "placeholder", "date");
        ScoreContainer.addScore(10, "placeholder", "date");

        ScoreContainer.addScore(0, "placeholder", "date");
        int lowest = 11;
        for (int i = 0; i < ScoreContainer.getList().size(); i++) {
            if (ScoreContainer.getList().get(i).getScore() < lowest) {
                lowest = ScoreContainer.getList().get(i).getScore();
            }
        }
        assertTrue(lowest == 1);
    }

    /**
     * Check whether the method returns true when you ask for a value that is higher than the high score and false when it's lower.
     */
    @Test
    public void isHighScoreTest() {
        ScoreContainer.addScore(10, "placeholder", "date");
        ScoreContainer.addScore(15, "placeholder", "date");
        assertTrue(ScoreContainer.isHighScore(16));
        assertFalse(ScoreContainer.isHighScore(11));
        assertFalse(ScoreContainer.isHighScore(9));
    }

}
