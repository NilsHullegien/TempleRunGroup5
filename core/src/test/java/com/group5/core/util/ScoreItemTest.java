package com.group5.core.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.group5.core.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ScoreItemTest {

    @Test
    public void scoreItemTest() {
        ScoreItem s = new ScoreItem(1,2, "name");
        assertTrue(s.getRank() == 1);
        assertTrue(s.getScore() == 2);
        assertTrue(s.getName().equals("name"));
    }

}
