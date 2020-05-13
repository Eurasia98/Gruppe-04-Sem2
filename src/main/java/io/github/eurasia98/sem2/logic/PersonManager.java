package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

import java.util.ArrayList;

public class PersonManager {
    public PersonManager(){}

    public void insertPerson(ArrayList<String> personInfo){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        Person person = new Person(personInfo.get(0), personInfo.get(1), personInfo.get(2),
                personInfo.get(3), personInfo.get(4));
        personInfo.add(person.getAccountType());
        databasePersonHandler.insertPerson(personInfo);
    }

    public ArrayList<String[]> getMyPersons(String username, int account_id){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        ArrayList<String[]> myProductionsInfo = databasePersonHandler.getMyPersons(username, account_id);
        return myProductionsInfo;
    }


}
