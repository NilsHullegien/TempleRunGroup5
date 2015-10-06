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
     * Write some scores to a file.
     * @param args stuffs
     */
    public static void main(final String[] args) {
        writeScore();

    }



    /**
     * Write a set of score to a file.
     */
    public static void writeScore() {

        Properties prop = new Properties();

        OutputStream output = null;

        ScoreItem i1 = new ScoreItem(1, 10, "Nils");
        ScoreItem i2 = new ScoreItem(2, 8, "Marcel");
        ArrayList<ScoreItem> l = new ArrayList<ScoreItem>();
        l.add(i1);
        l.add(i2);

        System.out.println(System.getProperty("user.dir"));
        try {
            output = new FileOutputStream("src/main/java/com/group5/core/util/scores.properties");



            for (int i = 0; i < l.size(); i++) {
                prop.setProperty("rank" + Integer.toString(i), Integer.toString(l.get(i).getRank()));
                prop.setProperty("score" + Integer.toString(i), Integer.toString(l.get(i).getScore()));
                prop.setProperty("name" + Integer.toString(i), l.get(i).getName());
                System.out.println("hallo");
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
        System.out.println("hoi2");

    }


}
