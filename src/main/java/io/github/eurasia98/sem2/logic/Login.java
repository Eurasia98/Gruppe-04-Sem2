package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseAccountHandler;
import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProducerHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Login {

    private static Account account;

    public List<String> loginVerify (String username, String password) {
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
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

    public void loginVerified(List<String> accountArray){

        String accountType = accountArray.get(3);
        switch (accountType){
            case "SystemAdministrator": {
                account = new SystemAdministrator(Integer.parseInt(accountArray.get(0)), accountArray.get(1),
                        accountArray.get(2), accountArray.get(4));
                break;
            }
            case "Producer": {
                DatabaseProducerHandler databaseProducerHandler = new DatabaseProducerHandler();
                ArrayList<String> producerInfo = databaseProducerHandler.getProducer(Integer.parseInt(accountArray.get(0)));
                account = new Producer(Integer.parseInt(producerInfo.get(0)), producerInfo.get(1), producerInfo.get(2),
                        producerInfo.get(3), accountArray.get(4), Integer.parseInt(producerInfo.get(5)), accountArray.get(1), accountArray.get(2));
                break;
            }
            case "Person": {
                DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
                ArrayList<String> personInfo = databasePersonHandler.getPersonInfo(Integer.parseInt(accountArray.get(0)));
                account = new Person(Integer.parseInt(personInfo.get(0)), Integer.parseInt(personInfo.get(1)), personInfo.get(4),
                        personInfo.get(5), personInfo.get(6), accountArray.get(1), accountArray.get(2), accountArray.get(4));
                break;
            }
            case "RegistreringDanmark": {
                account = new RegistreringDanmark(Integer.parseInt(accountArray.get(0)), accountArray.get(1),
                        accountArray.get(2), accountArray.get(4));
                break;
            }
            default: {
                System.out.println("Not implemented yet");
                break;
            }
        }
    }

    public static Account getAccount() {
        return account;
    }

    public static Producer getProducerAccount() {
        return ((Producer)account);
    }

    public static Person getPersonAccount() {
        return ((Person)account);
    }
}
