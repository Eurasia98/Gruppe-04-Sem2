package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountManager {
    public AccountManager(){}

    public Boolean checkUsernameAvailability(String username){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.checkUsernameAvailability(username);
    }

    public int getAccount_id(int account_id){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        ArrayList<String> accountInfo = databaseAccountHandler.getAccount(account_id);
        Account account = new Account(Integer.parseInt(accountInfo.get(0)), accountInfo.get(1),
                accountInfo.get(2), accountInfo.get(3));

        return account.getId();
    }

    public Boolean createSpecialAccount(String username, String password, String accountType){
        ArrayList<String> accountInfo = new ArrayList<>();
        accountInfo.add(username);
        accountInfo.add(password);
        accountInfo.add(accountType);

        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.insertSpecialAccount(accountInfo);
    }
}
