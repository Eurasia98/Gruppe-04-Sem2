package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProducerHandler;

public class ProducerManager {
    DatabaseProducerHandler databaseProducerHandler = new DatabaseProducerHandler();

    public void createProducer(int userId, String username, String password, String fName, String lName, String email){
        Producer producer = new Producer(userId, username, password, fName, lName, email);
        databaseProducerHandler.saveProducer(producer);
    }
}
