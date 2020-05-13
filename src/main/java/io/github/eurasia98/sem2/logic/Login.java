package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProducerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Login {

    private Account account;

    public List<String> loginVerify (String username, String password) {
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        List<String> accountVerified = new ArrayList<>();
        accountVerified.addAll(databaseAccountHandler.verifyLogin(username, password));
        if (accountVerified.isEmpty()){
            return Collections.emptyList();
        }

        loginVerified(accountVerified);

        List<String> accountLoggedIn = new ArrayList<>();
        accountLoggedIn.add(accountVerified.get(1));
        accountLoggedIn.add(accountVerified.get(3));
        loginVerified(accountVerified);


        return accountLoggedIn;
    }

    // Return account object? Make account in login static? What if people log out?

    public void loginVerified(List<String> accountArray){

        /*String accountType = accountArray.get(1);
        switch (accountType){
            case "Producer": {
                // Test Producer
                account = new Producer(Integer.parseInt(accountArray.get(0)), accountArray.get(1), accountArray.get(2), "Hans", "Hansen", "");
                break;
            }
            // Test SysAdmin
            case "SystemAdministrator": {
                account = new SystemAdministrator(Integer.parseInt(accountArray.get(0)), accountArray.get(1), accountArray.get(2), accountArray.get(3));
                break;
            }
            case "Person": {
                System.out.println("not implemented yet");
                break;
            }
            default: {
                System.out.println("Not implemented yet");
                break;
            }
        }*/

        String accountType = accountArray.get(3);
        switch (accountType){
            case "Producer": {
                DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();
                ArrayList<String> producerInfo = databaseProducerManager.getProducer(Integer.parseInt(accountArray.get(0)));
                Producer producer = new Producer(Integer.parseInt(producerInfo.get(0)), producerInfo.get(1), producerInfo.get(2),
                        producerInfo.get(3), producerInfo.get(4), producerInfo.get(5));

                account = producer;
                break;
            }
            // Test SysAdmin
            case "SystemAdministrator": {
                account = new SystemAdministrator(Integer.parseInt(accountArray.get(0)), accountArray.get(1), accountArray.get(2), accountArray.get(3));
                break;
            }
            case "Person": {
                System.out.println("not implemented yet");
                break;
            }
            default: {
                System.out.println("Not implemented yet");
                break;
            }
        }
    }
        //account = new Account (accountArray.get(1), accountArray.get(2), accountArray.get(4));
}
