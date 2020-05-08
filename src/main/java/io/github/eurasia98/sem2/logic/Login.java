package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.logic.accountLogic.ASystemAdministrator;
import io.github.eurasia98.sem2.logic.accountLogic.Account;
import io.github.eurasia98.sem2.logic.accountLogic.Producer;
import io.github.eurasia98.sem2.persistence.accountPersistence.DatabaseUserManager;

import java.util.ArrayList;
import java.util.List;

public class Login {

    private DatabaseUserManager databaseUserManager = new DatabaseUserManager();

    private Account account;

    public String loginVerify (String username, String password) {
        List<String> accountVerified = new ArrayList<>();
        accountVerified.addAll(databaseUserManager.verifyLogin(username, password));
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

        if (databaseUserManager.checkAccountType(accountArray.get(5))){
            System.out.println(accountArray.get(5));
            String s = accountArray.get(5);
            switch (s){
                case "Producer": {
                    account = new Producer(accountArray.get(1), accountArray.get(2), accountArray.get(5),
                            accountArray.get(3), accountArray.get(4), Integer.parseInt(accountArray.get(0)));
                }
                case "SystemAdministrator": {
                    account = new ASystemAdministrator();
                }
                case "Person": {
                    System.out.println("not implemented yet");
                }
            }
        }
        //account = new Account (accountArray.get(1), accountArray.get(2), accountArray.get(4));
    }




}
