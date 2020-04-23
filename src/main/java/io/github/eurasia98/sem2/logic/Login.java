package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login {

    private DatabaseController databaseController = new DatabaseController();

    private Account account;

    public String loginVerify (String username, String password) {
        List<String> accountVerified = new ArrayList<String>();
        accountVerified.addAll(databaseController.verifyLogin(username,password));
        if (accountVerified.get(0).equals("Wrong username / password.")){
            return "Wrong username / password.";
        }
        else if (accountVerified.get(0).equals("System error. Try again.")){
            return "System error. Try again.";
        }
        loginVerified(accountVerified);
        return accountVerified.get(3);
    }

    public void loginVerified(List<String> accountArray){
        account = new Account (accountArray.get(0), accountArray.get(1), accountArray.get(3));
    }




}
