package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

public class AccountManager {
    public AccountManager(){}

    public Boolean checkUsernameAvailability(String username){
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.checkUsernameAvailability(username);
    }
}