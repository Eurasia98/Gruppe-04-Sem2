package io.github.eurasia98.sem2.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseProducerManager {
    private File file;

    public void saveNewProducer(Producer producer){
        file = new File("Accounts.txt");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                if (s.nextLine().isEmpty()){
                    FileWriter fileWriter = new FileWriter("Accounts.txt", true);
                    fileWriter.write(producer.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
