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
     * @param infile file to write to
     */
    public static void writeScore(final ArrayList<ScoreItem> list, final String infile) {

        Properties prop = new Properties();

        OutputStream output = null;
        prop.setProperty("size", Integer.toString(list.size()));

        try {
            output = new FileOutputStream(infile);



            for (int i = 0; i < list.size(); i++) {
                prop.setProperty("rank" + Integer.toString(i), Integer.toString(list.get(i).getRank()));
                prop.setProperty("score" + Integer.toString(i), Integer.toString(list.get(i).getScore()));
                prop.setProperty("name" + Integer.toString(i), list.get(i).getName());
                prop.setProperty("date" + Integer.toString(i), list.get(i).getDate());
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
