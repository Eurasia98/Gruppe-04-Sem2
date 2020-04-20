package org.example;

public class Account {

    private String username;
    private String password;
    private int accountType;

    public Account(String username, String password, int typeNum) {
        this.username = username;
        this.password = password;
        this.accountType = typeNum;
    }
    Account
    acc1 = new Account("Nybruger","password123", 1);

    public Account() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountType() {
        return accountType;
    }
}