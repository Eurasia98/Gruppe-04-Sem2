package io.github.eurasia98.sem2.logic;

public class Account {

    private String username;
    private String password;
    private int accountType;

    public Account(String Username, String Password, int typeNum) {
        this.username = Username;
        this.password = Password;
        this.accountType = typeNum;
    }
    Account
    acc1 = new Account("Nybruger","password123", 1);

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