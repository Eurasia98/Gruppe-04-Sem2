package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProducerManager;

public class ProducerManager {
    DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();

    public void createProducer(String username, String password, String fName, String lName, String email){
        Producer producer = new Producer(username, password, fName, lName, email);
        databaseProducerManager.saveProducer(producer);
    }
}
