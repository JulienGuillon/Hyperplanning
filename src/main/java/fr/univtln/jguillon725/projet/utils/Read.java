package fr.univtln.jguillon725.projet.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by julien on 15/10/15.
 */
public class Read {
    private String creation ="";

    public String readFile(String file) throws IOException {
        String filename = this.getClass().getResource("/" + file).getFile();
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    creation = creation + "\n" + line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return creation;
    }
}