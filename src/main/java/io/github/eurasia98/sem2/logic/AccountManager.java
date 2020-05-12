package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

import java.util.ArrayList;

public class AccountManager {
    public AccountManager(){}

    public Boolean checkUsernameAvailability(String username){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.checkUsernameAvailability(username);
    }

    public Account getAccount(int account_id){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        ArrayList<String> accountInfo = databaseAccountHandler.getAccount(account_id);
        Account account = new Account(Integer.parseInt(accountInfo.get(0)), accountInfo.get(1),
                accountInfo.get(2), accountInfo.get(3));

        return account;
    }
}
