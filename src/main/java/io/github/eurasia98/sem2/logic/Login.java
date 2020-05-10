package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Login {

    private DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();

    private Account account;

    public List<String> loginVerify (String username, String password) {
        List<String> accountVerified = new ArrayList<>();
        accountVerified.addAll(databaseAccountHandler.verifyLogin(username, password));
        if (accountVerified.isEmpty()){
            return Collections.emptyList();
        }
        List<String> accountLoggedIn = new ArrayList<>();
        accountLoggedIn.add(accountVerified.get(1));
        accountLoggedIn.add(accountVerified.get(3));
        loginVerified(accountVerified);
        return accountLoggedIn;
    }

    // Return account object? Make account in login static? What if people log out?

    public void loginVerified(List<String> accountArray){

        String accountType = accountArray.get(2);
        switch (accountType){
            case "Producer": {
                // Test Producer
                account = new Producer(Integer.parseInt(accountArray.get(0)), accountArray.get(1), accountArray.get(2), "Hans", "Hansen", "");
            }
            // Test SysAdmin
            case "SystemAdministrator": {
                account = new SystemAdministrator(Integer.parseInt(accountArray.get(0)), accountArray.get(1), accountArray.get(2), accountArray.get(3));
            }
            case "Person": {
                System.out.println("not implemented yet");
            }
            default: {
                System.out.println("Not implemented yet");
            }
        }
    }
        //account = new Account (accountArray.get(1), accountArray.get(2), accountArray.get(4));
}
