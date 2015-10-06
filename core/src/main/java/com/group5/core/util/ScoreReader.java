package com.group5.core.util;

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
     */
    public static void read() {
        Properties prop = new Properties();

        InputStream input = null;

        ArrayList<ScoreItem> l = new ArrayList<ScoreItem>();

        try {

            input = new FileInputStream("scores.properties");

            // load a properties file
            prop.load(input);


            System.out.println(prop.getProperty("size", "0"));





            for (int i = 0; i < 2; i++) {
                Integer rank = Integer.parseInt(prop.getProperty("rank" + Integer.toString(i)));
                Integer score = Integer.parseInt(prop.getProperty("score" + Integer.toString(i)));
                String name = prop.getProperty("name" + Integer.toString(i));

                System.out.println(rank);
                System.out.println(score);

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
        ScoreContainer.initialize(l);

    }

}
