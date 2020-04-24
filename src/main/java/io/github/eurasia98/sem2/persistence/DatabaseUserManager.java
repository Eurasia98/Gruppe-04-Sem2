package io.github.eurasia98.sem2.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
            } else i = Integer.parseInt(s.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    public Boolean checkAccountType(String accountType){
        file = getFile("AccountTypes.txt");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                String[] types = s.nextLine().split(";");
                if (types[1].equalsIgnoreCase(accountType)){
                    return true;
                } else return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return false;
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
                    String[] foundAccount = Arrays.copyOfRange(accountArray, 0, 6);
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
