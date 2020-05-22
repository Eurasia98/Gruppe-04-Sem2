package io.github.eurasia98.sem2.logic;

public class Account {

    private int userId;
    private String username;
    private String password;
    private String accountType;
    private String email;

    public Account(int userId, String username, String password, String accountType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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