package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountManager {
    public AccountManager(){}

    public Boolean checkUsernameAvailability (String username){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.checkUsernameAvailability(username);
    }

    public Boolean createSpecialAccount (String username, String password, String accountType){
        ArrayList<String> accountInfo = new ArrayList<>();
        accountInfo.add(username);
        accountInfo.add(password);
        accountInfo.add(accountType);

        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.insertSpecialAccount(accountInfo);
    }

    public Boolean editAccountPassword (String oldPassword, String newPassword){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        if (databaseAccountHandler.editAccountPassword(Login.getAccount().getUsername(), oldPassword, newPassword)){
            Login.getAccount().setPassword(newPassword);
            return true;
        }
        return false;
    }

    public Boolean editAccountEmail (String newEmail){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        if (databaseAccountHandler.editAccountEmail(Login.getAccount().getUsername(), newEmail)){
            Login.getAccount().setEmail(newEmail);
            return true;
        }
        return false;
    }
}
