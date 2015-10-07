package com.group5.core.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.group5.core.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ScoreReaderTest {


    @Before
    public void setUp() {
        File f = new File("test.properties");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
        ScoreContainer.setList(new ArrayList<ScoreItem>());
    }

    /**
     * Try to read a non-existing file, resulting in no errors and an empty list.
     */
    @Test
    public void NoFileTest() {
        ScoreReader.read("test.properties");
        assertTrue(ScoreContainer.getList().size() == 0);
    }

    /**
     * Read out a file that contains one element.
     */
    @Test
    public void OneElementInFileTest() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name"));
        ScoreWriter.writeScore(list, "test.properties");

        ScoreReader.read("test.properties");
        assertTrue(ScoreContainer.getList().size() == 1);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 2);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("name"));
    }

    /**
     * Read out a file that contains multiple elements.
     */
    @Test
    public void MultipleElementsInFileTest() {
        ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
        list.add(new ScoreItem(1, 2, "name1"));
        list.add(new ScoreItem(3, 4, "name2"));
        ScoreWriter.writeScore(list, "test.properties");

        ScoreReader.read("test.properties");
        assertTrue(ScoreContainer.getList().size() == 2);
        assertTrue(ScoreContainer.getList().get(0).getRank() == 1);
        assertTrue(ScoreContainer.getList().get(0).getScore() == 2);
        assertTrue(ScoreContainer.getList().get(0).getName().equals("name1"));
        assertTrue(ScoreContainer.getList().get(1).getRank() == 3);
        assertTrue(ScoreContainer.getList().get(1).getScore() == 4);
        assertTrue(ScoreContainer.getList().get(1).getName().equals("name2"));
    }
}
