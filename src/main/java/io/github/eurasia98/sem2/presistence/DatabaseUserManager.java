package io.github.eurasia98.sem2.presistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DatabaseUserManager {
    File file;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public void updateUserIdCounter(int userIdCounter) throws IOException {
        String stringIdCounter = Integer.toString(userIdCounter+1);
        file =  getFile("UserIdCounter.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.write(stringIdCounter);
        bufferedWriter.close();
        fw.close();
    }

    public int getUserIdCounter(){
        int i = 0;
        file = getFile("UserIdCounter.txt");
        try (Scanner s = new Scanner(file)) {
            i = Integer.parseInt(s.next());
            System.out.println(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }
}
