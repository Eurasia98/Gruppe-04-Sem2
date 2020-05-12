package io.github.eurasia98.sem2.logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ExportData {

    public static boolean printFile(String credits){
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("ExportedCredits.txt", true));
            outputStream.println(credits);
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.close();
        return false;
    }
}
