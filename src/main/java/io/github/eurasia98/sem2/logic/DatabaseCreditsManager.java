package io.github.eurasia98.sem2.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DatabaseCreditsManager {
    File file;
    private APerson person;
    private Production production;
    private Credit credit;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public String searchCredits(int productionId) {
        DatabaseProductionManager dpm = new DatabaseProductionManager();
        StringBuilder sb = new StringBuilder();
        file = getFile("Credits.txt");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(";");
                if (productionId == Integer.parseInt(lineArray[0])){
                    person = new APerson(lineArray[1], lineArray[2]);
                    production = new Production(dpm.searchProductionTitleFromId(productionId), productionId);
                    credit = new Credit(person, production, lineArray[3], lineArray[4]);
                    sb.append(credit.toString() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }
}
