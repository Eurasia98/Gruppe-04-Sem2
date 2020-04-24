package io.github.eurasia98.sem2.presistence;

import io.github.eurasia98.sem2.logic.Producer;

import java.io.*;
import java.util.Scanner;

public class DatabaseProducerManager {
    private File file;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public void saveNewProducer(Producer producer){
        file = getFile("Accounts.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            String s = producer.toString();
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(s + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
