package io.github.eurasia98.sem2.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseUserManager {
    File file;

    public void updateUserIdCounter(int userIdCounter) throws IOException {
        file = new File("UserIdCounter.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(userIdCounter);
    }

    public int getUserIdCounter(){
        int i = 0;
        file = new File("UserIdCounter.txt");
        try (Scanner s = new Scanner(file)) {
            i = s.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }
}
