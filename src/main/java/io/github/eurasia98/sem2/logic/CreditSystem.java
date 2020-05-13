package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditSystem {
   private static ArrayList<String> creditsToDisplay;
   private static Account account;

   public static ArrayList<String> getCreditsToDisplay() {
      return creditsToDisplay;
   }

   public static Account getAccount() {
      return account;
   }

   public static int getAccount_id(){
      return account.getId();
   }

   public Boolean availableUsername(String username) {
      AccountManager accountManager = new AccountManager();
      return accountManager.checkUsernameAvailability(username) == true;
   }

   public Boolean createNewPerson(String username, String password, String firstName, String lastName) {
      PersonManager personManager = new PersonManager();
      ArrayList<String> personInfo = new ArrayList<>();
      personInfo.add(username);
      personInfo.add(password);
      personInfo.add(firstName);
      personInfo.add(lastName);
      personInfo.add(account.getUsername());
      personManager.insertPerson(personInfo);

      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      if (databasePersonHandler.getId(username) != 0){
         return true;
      } else return false;
   }

   public Boolean createNewMovie(String title, String productionId) {
      MovieManager movieManager = new MovieManager();
      movieManager.insertMovie(new Movie(title, productionId, getAccount_id()));

      DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
      if (databaseMovieHandler.getTitle(productionId) != null){
         return true;
      } else return false;
   }

   public Boolean createNewTv_series(String title, String productionId, String series_id,String description) {
      TvSeriesManager tvSeriesManager = new TvSeriesManager();
      tvSeriesManager.insertTvSeries(new TvSeries(title, productionId, series_id, getAccount_id(),description));

      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      if (databaseTvSeriesHandler.getTitle(productionId) != null){
         return true;
      } else return false;
   }

   public HashMap<String, String> search(String searchString) {
      DatabaseSearchController databaseSearchController = new DatabaseSearchController();
      HashMap<String, String> searchResultsMap = new HashMap<>();
      ArrayList<SearchResults> searchResultsArrayList = databaseSearchController.search(searchString);
      for (SearchResults searchResult : searchResultsArrayList){
         searchResultsMap.put(searchResult.getTitle(), searchResult.getProductionId());
      }
      return searchResultsMap;
   }

   public static void setCreditsToDisplay(String production_id) {
      CreditManager creditManager = new CreditManager();
      creditsToDisplay = creditManager.searchCredits(production_id);
   }

   public ArrayList<Credit> findCredits(SearchResults searchResults) {
      DatabaseSearchController databaseSearchController = new DatabaseSearchController();
      ArrayList<Credit> credits = databaseSearchController.searchCredits(searchResults);
      return credits;
   }

   public List<String> login(String username, String password) {
      Login login = new Login();

      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      if (databaseAccountHandler.getAccount(username) != null ){
         ArrayList<String> accountInfo = databaseAccountHandler.getAccount(username);
         account = new Account(Integer.parseInt(accountInfo.get(0)), accountInfo.get(1), accountInfo.get(2), accountInfo.get(3));
      }

      return login.loginVerify(username, password);
   }

   public ArrayList<String[]> showMyProductions(){
      ProductionManager productionManager = new ProductionManager();
      return productionManager.getMyProductions(account.getId());
   }

   public ArrayList<String[]> showMyPersons(){
      PersonManager personManager = new PersonManager();
      return personManager.getMyPersons(account.getUsername(), account.getId());
   }

   public Boolean createNewCredit(int account_id, String production_id, String roleType, String roleName){
      CreditManager creditManager = new CreditManager();
      creditManager.insertCredit(new Credit(account_id, production_id, roleType, roleName));
      return true;
   }

   public boolean exportData(){
      if(ExportData.printFile(getCreditsToDisplay())){
         return true;
      }
      return false;
   }
}
