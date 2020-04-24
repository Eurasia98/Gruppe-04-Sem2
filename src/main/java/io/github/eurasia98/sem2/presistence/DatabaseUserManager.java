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

    public List<String> verifyLogin (String username, String password){
        file = getFile("Accounts.txt");
        List<String> accountInfo = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String account = s.nextLine();
                String[] accountArray = account.split(";");
                if (username.equals(accountArray[1]) && password.equals(accountArray[2])){
                    String[] foundAccount = Arrays.copyOfRange(accountArray, 1, 5);
                    return Arrays.asList(foundAccount);
                }
            }
            accountInfo.add("Wrong username / password.");
            return accountInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        accountInfo.add("System error. Try again.");
        return accountInfo;

    }
}
