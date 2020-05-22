package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProducerManager;

import java.util.ArrayList;
import java.util.List;

public class ProducerManager {
    DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();

    /*public Producer createProducer(String username, String password, String fName, String lName, String email) {
        Producer producer = new Producer(username, password, fName, lName, email);
        databaseProducerManager.saveProducer(producer);
        return producer;
    }*/
    public Boolean saveProducer(Producer producer){
        DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();
        return databaseProducerManager.saveProducer(producer);
    }
}
