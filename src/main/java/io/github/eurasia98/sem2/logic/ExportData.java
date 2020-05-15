package io.github.eurasia98.sem2.logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExportData {

    public static boolean printFile(ArrayList<String> exportData){
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("ExportedCredits.txt", true));
            for (String s : exportData){
                outputStream.println(s);
            }
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.close();
        return false;
    }
}
