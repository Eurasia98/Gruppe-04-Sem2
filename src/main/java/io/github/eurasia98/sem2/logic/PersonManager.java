package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;

import java.util.ArrayList;

public class PersonManager {
    public PersonManager(){}

    public Person createPerson(String username, String password, String firstName, String lastName){
        Person person = new Person(username, password, firstName, lastName);
        return person;
    }

    public Person createPersonWithEmail(String username, String password, String firstName,
                                        String lastName, String email){
        Person person = new Person(username, password, firstName, lastName, email);
        return person;
    }

    public Person createPersonWithCredits(String username, String password, String firstName,
                                          String lastName, ArrayList<Credit> myCreditList){
        Person person = new Person(username, password, firstName, lastName, myCreditList);
        return person;
    }

    public Person createPersonWithEmailAndCredits(String username, String password, String firstName,
                                          String lastName, String email ,ArrayList<Credit> myCreditList){
        Person person = new Person(username, password, firstName, lastName, email, myCreditList);
        return person;
    }

    public Boolean insertPerson(Person person){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        return databasePersonHandler.insertPerson(person);
    }
}
