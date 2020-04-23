package io.github.eurasia98.sem2.logic;

public class ProducerManager {
    DatabaseProducerManager databaseProducerManager = new DatabaseProducerManager();

    public void createProducer(String fName, String lName){
        Producer producer = new Producer(fName, lName);
        databaseProducerManager.saveNewProducer(producer);
    }
}
