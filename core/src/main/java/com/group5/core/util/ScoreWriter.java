package com.group5.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class writes a list of score to a properties.
 * @author Marcel
 *
 */
public abstract class ScoreWriter {

    /**
     * Write a set of scores to a file.
     * @param list List of score items to write to a file
     */
    public static void writeScore(final ArrayList<ScoreItem> list) {

        Properties prop = new Properties();

        OutputStream output = null;

        ScoreItem i1 = new ScoreItem(1, 10, "Nils");
        ScoreItem i2 = new ScoreItem(2, 8, "Marcel");
        ArrayList<ScoreItem> l = new ArrayList<ScoreItem>();
        l.add(i1);
        l.add(i2);
        prop.setProperty("size", Integer.toString(list.size()));

        try {
            output = new FileOutputStream("scores.properties");



            for (int i = 0; i < list.size(); i++) {
                prop.setProperty("rank" + Integer.toString(i), Integer.toString(list.get(i).getRank()));
                prop.setProperty("score" + Integer.toString(i), Integer.toString(list.get(i).getScore()));
                prop.setProperty("name" + Integer.toString(i), list.get(i).getName());
            }




            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
