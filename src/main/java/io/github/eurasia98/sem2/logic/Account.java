package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

public class Account {

    private int id;
    private String username;
    private String password;
    private String accountType;
    private String email;

    public Account(int id, String username, String password, String accountType, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.email = email;
    }

    public Account(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public int getId(){ return id; }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setAccountType(String accountType) { this.accountType = accountType; }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}