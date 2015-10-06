package com.group5.core.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.group5.core.GdxTestRunner;

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
        ScoreContainer.initialize(new ArrayList<ScoreItem>());;
        assertTrue(ScoreContainer.getList().isEmpty());
    }



    @Test
    public void initializeOneElement() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name"));
        ScoreContainer.initialize(list);
        assertTrue(ScoreContainer.getList().size() == 1);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 2);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("name"));
    }

    @Test
    public void initializeMultipleElements() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name1"));
        list.add(new ScoreItem(3, 4, "name2"));
        list.add(new ScoreItem(5, 6, "name3"));
        ScoreContainer.initialize(list);
        assertTrue(ScoreContainer.getList().size() == 3);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 4);
        assertTrue(ScoreContainer.getList().get(2).getName().equals("name3"));

    }

    @Test
    public void addScoreEmptyListTest() {
        ScoreContainer.addScore(10);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    @Test
    public void addScoreNewBestTest() {
        ScoreContainer.addScore(10);
        ScoreContainer.addScore(11);
        assertTrue(ScoreContainer.getList().get(1).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 11);
        assertTrue(ScoreContainer.getList().get(1).getName().equals("placeholder"));
        assertTrue(ScoreContainer.getList().get(0).getRank() == 2);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    @Test
    public void addScoreNewNotBestTest() {
        ScoreContainer.addScore(11);
        ScoreContainer.addScore(10);
        assertTrue(ScoreContainer.getList().get(1).getRank() == 2);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 10);
        assertTrue(ScoreContainer.getList().get(1).getName().equals("placeholder"));
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 11);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("placeholder"));
    }

    @Test
    public void addScoreNewBestTenElementsTest() {

        ScoreContainer.addScore(1);
        ScoreContainer.addScore(2);
        ScoreContainer.addScore(3);
        ScoreContainer.addScore(4);
        ScoreContainer.addScore(5);
        ScoreContainer.addScore(6);
        ScoreContainer.addScore(7);
        ScoreContainer.addScore(8);
        ScoreContainer.addScore(9);
        ScoreContainer.addScore(10);

        ScoreContainer.addScore(11);
        int lowest =11;
        for (int i = 0; i < ScoreContainer.getList().size(); i++) {
            if (ScoreContainer.getList().get(i).getScore() < lowest) {
                lowest = ScoreContainer.getList().get(i).getScore();
            }
        }
        assertTrue(lowest == 2);
    }

    @Test
    public void addScoreNewWorstTenElementsTest() {

        ScoreContainer.addScore(1);
        ScoreContainer.addScore(2);
        ScoreContainer.addScore(3);
        ScoreContainer.addScore(4);
        ScoreContainer.addScore(5);
        ScoreContainer.addScore(6);
        ScoreContainer.addScore(7);
        ScoreContainer.addScore(8);
        ScoreContainer.addScore(9);
        ScoreContainer.addScore(10);

        ScoreContainer.addScore(0);
        int lowest =11;
        for (int i = 0; i < ScoreContainer.getList().size(); i++) {
            if (ScoreContainer.getList().get(i).getScore() < lowest) {
                lowest = ScoreContainer.getList().get(i).getScore();
            }
        }
        assertTrue(lowest == 1);
    }

}
