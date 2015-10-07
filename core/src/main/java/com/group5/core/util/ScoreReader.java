package com.group5.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class reads scores from a properties file and feeds them to the ScoreContainer.
 * @author Marcel
 *
 */
public abstract class ScoreReader {

    /**
     * Read the scores from a properties file and feed them to the ScoreContainer.
     * @param infile File you that has to be looked into
     */
    public static void read(final String infile) {
        Properties prop = new Properties();

        InputStream input = null;

        ArrayList<ScoreItem> l = new ArrayList<ScoreItem>();

        File f = new File(infile);
        if (f.exists()) {
            try {

                input = new FileInputStream(infile);

                // load a properties file
                prop.load(input);

                for (int i = 0; i < Integer.parseInt(prop.getProperty("size")); i++) {
                    Integer rank = Integer.parseInt(prop.getProperty("rank" + Integer.toString(i)));
                    Integer score = Integer.parseInt(prop.getProperty("score" + Integer.toString(i)));
                    String name = prop.getProperty("name" + Integer.toString(i));
                    l.add(new ScoreItem(rank, score, name));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ScoreContainer.initialize(l);
    }
}
