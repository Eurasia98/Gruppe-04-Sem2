package io.github.eurasia98.sem2.logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExportData {

    public static boolean printFile(ArrayList<String[]> exportData, String title){
        PrintWriter outputStream = null;
        try {
            // Saved file is same name as title of production. Commented out for easier github management.
            outputStream = new PrintWriter(new FileOutputStream(title + ".txt", true));

            outputStream.println(title + " krediteringer. \n\n");
            outputStream.println("Navn: " + "\t" + "Rolle: " + "\t" +"Rollens navn: " + "\n\n");


            for (String[] s : exportData){
                outputStream.println(s[0] + " " + s[1] + " " +s[2] + "\n");
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
