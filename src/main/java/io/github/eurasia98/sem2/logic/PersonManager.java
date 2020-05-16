package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

import java.util.ArrayList;

public class PersonManager {
    public PersonManager(){}

    public void insertPerson(ArrayList<String> personInfo){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        personInfo.add("Person");
        databasePersonHandler.insertPerson(personInfo);
    }

    public ArrayList<String[]> getMyPersons(String username, int account_id){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        ArrayList<String[]> myProductionsInfo = databasePersonHandler.getMyPersons(username, account_id);
        return myProductionsInfo;
    }


}
