package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProducerManager;

import java.util.ArrayList;

public class ProducerManager {
    DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();

    public Producer createProducer(String username, String password, String fName, String lName, String email){
        Producer producer = new Producer(username, password, fName, lName, email);
        databaseProducerManager.saveProducer(producer);
        return producer;
    }
    public Producer createProducerWithEmailAndCredits(String username, String password, String firstName,
                                                  String lastName){
        Producer producer = new Producer(username, password, firstName, lastName);
        databaseProducerManager.saveProducer(producer);
        return producer;
    }
}
