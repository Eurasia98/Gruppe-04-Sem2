package io.github.eurasia98.sem2.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseUserManager {
    File file;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public void updateUserIdCounter(int userIdCounter) throws IOException {
        file =  getFile("UserIdCounter.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(Integer.toString(userIdCounter+1));
        fw.flush();
    }

    public int getUserIdCounter(){
        int i = 0;
        file = getFile("UserIdCounter.txt");
        try (Scanner s = new Scanner(file)) {
            if (!s.hasNextLine()){
                return i;
            } else i = Integer.parseInt(s.next())-1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }
}
