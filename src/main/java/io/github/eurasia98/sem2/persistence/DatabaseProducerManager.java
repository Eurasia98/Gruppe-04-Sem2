package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Producer;

import java.io.*;
import java.util.Scanner;

public class DatabaseProducerManager {
    private File file;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public void saveNewProducer(Producer producer){
        System.out.println(producer.toString());
        file = getFile("Accounts.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            String s = producer.toString();
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(s + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail");
        }

    }
}
