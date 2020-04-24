package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseUserManager;

import java.io.IOException;

public class Account {

    private int userId;
    private String username;
    private String password;
    private String accountType;
    private static int customUsernameCounter = 0;
    private static String customUsername = "test";
    private static String customPassword = "test123";
    private static DatabaseUserManager dum = new DatabaseUserManager();
    private static int userIdCounter = dum.getUserIdCounter();

    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.userId = userIdCounter;
        userIdCounter++;
        try {
            dum.updateUserIdCounter(userIdCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Account(String accountType) {
        this.username = customUsername + Integer.toString(customUsernameCounter);
        customUsernameCounter++;
        this.password = customPassword;
        this.accountType = accountType;
        this.userId = userIdCounter;
        userIdCounter++;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getUserId(){ return userId; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setAccountType(String accountType) { this.accountType = accountType; }






}