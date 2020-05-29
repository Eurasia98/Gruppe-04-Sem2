package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProducerHandler;

import java.util.ArrayList;

public class ProducerManager {
    DatabaseProducerHandler databaseProducerHandler = new DatabaseProducerHandler();

    public void createProducer(int userId, String username, String password, String fName, String lName, String email){
        Producer producer = new Producer(userId, username, password, fName, lName, email);
        databaseProducerHandler.saveProducer(producer);
    }

    public void insertProducer(ArrayList<String> producerInfo){
        DatabaseProducerHandler databaseProducerHandler = new DatabaseProducerHandler();
        databaseProducerHandler.insertProducer(producerInfo);
    }
}
