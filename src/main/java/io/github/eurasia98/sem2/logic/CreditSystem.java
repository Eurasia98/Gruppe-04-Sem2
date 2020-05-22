package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsManager;
import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;
import io.github.eurasia98.sem2.persistence.DatabaseSearchController;

import java.util.ArrayList;

public class CreditSystem {
   private static String creditsToDisplay;

   public static String getCreditsToDisplay() {
      return creditsToDisplay;
   }

   public static void setCreditsToDisplay(String production_id) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      creditsToDisplay = databaseCreditsManager.searchCredits(production_id);
   }

   public Boolean availableUsername(String username){
      AccountManager accountManager = new AccountManager();
      return accountManager.checkUsernameAvailability(username) == true;
   }

   public Boolean createNewPerson(String username, String password, String firstName, String lastName){
      PersonManager personManager = new PersonManager();
      return personManager.insertPerson(new Person(username, password, firstName, lastName));
   }

   public Boolean createNewProducer(String username, String password, String fName, String lName, String email, String productionCompanyName){
      ProducerManager producerManager = new ProducerManager();
      return producerManager.saveProducer(new Producer(username, password, fName, lName, email, productionCompanyName));
   }

   public Boolean createNewMovie(String title, String productionId){
      MovieManager movieManager = new MovieManager();
      return movieManager.insertMovie(movieManager.createMovie(title, productionId));
   }

   public ArrayList<SearchResults> search(String searchString){
      DatabaseSearchController databaseSearchController = new DatabaseSearchController();
      ArrayList<SearchResults> searchResultsArrayList = databaseSearchController.search(searchString);
      return searchResultsArrayList;
   }

   public ArrayList<Credit> findCredits(SearchResults searchResults){
      DatabaseSearchController databaseSearchController = new DatabaseSearchController();
      ArrayList<Credit> credits = databaseSearchController.searchCredits(searchResults);
      return credits;
   }

   public ArrayList<Production> showMyProductions(int account_id){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      ArrayList<Production> myProductions = databaseProductionManager.getMyProductions(account_id);
      return myProductions;
   }
}
