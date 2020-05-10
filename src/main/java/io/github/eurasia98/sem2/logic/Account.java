package io.github.eurasia98.sem2.logic;

public class Account {

    private int userId;
    private String username;
    private String password;
    private String accountType;

    public Account(int userId, String username, String password, String accountType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public Account(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Too many different ways to make the different accounts. Fix before finish.
    public Account (String username, String password){
        this.username = username;
        this.password = password;
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