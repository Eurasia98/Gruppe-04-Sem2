package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.*;
import javafx.scene.control.TextField;

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
      return accountManager.checkUsernameAvailability(username);
   }

   public Boolean createNewPerson(String username, String password, String firstName, String lastName) {
      PersonManager personManager = new PersonManager();
      ArrayList<String> personInfo = new ArrayList<>();
      personInfo.add(username);
      personInfo.add(password);
      personInfo.add(firstName);
      personInfo.add(lastName);
      personInfo.add(account.getUsername());
      personInfo.add(null);
      personManager.insertPerson(personInfo);

      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      if (databasePersonHandler.getId(username) != 0){
         return true;
      } else return false;
   }

   public boolean createNewPerson(String username, String password, String firstName, String lastName, String email) {
      PersonManager personManager = new PersonManager();
      ArrayList<String> personInfo = new ArrayList<>();
      personInfo.add(username);
      personInfo.add(password);
      personInfo.add(firstName);
      personInfo.add(lastName);
      personInfo.add(account.getUsername());
      personInfo.add(email);
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

   public ArrayList<String[]> getMyPersons(){
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      ArrayList<String[]> myProductionsInfo = databasePersonHandler.getMyPersons(account.getUsername());
      return myProductionsInfo;
   }

   public Boolean createNewCredit(int account_id, String production_id, String roleType, String roleName){
      CreditManager creditManager = new CreditManager();
      return creditManager.insertCredit(new Credit(account_id, production_id, roleType, roleName));

   }

   public Boolean editProductionId(String oldProductionId, String newProductionId){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      ArrayList<String> productionInfo = databaseProductionManager.getProduction(oldProductionId);

      if (account.getId() == Integer.parseInt(productionInfo.get(4))){
         return databaseProductionManager.editProductionId(oldProductionId, newProductionId);
      }
      return false;
   }

   public Boolean editTitle(String newTitle, String production_id){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      return databaseProductionManager.editProductionTitle(newTitle, production_id);
   }

   public ArrayList<String> getProductionInfo(String productionId){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      return databaseProductionManager.getProduction(productionId);
   }

   public ArrayList<String[]> getCreditsInfo(String productionId){
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();

      ArrayList<String> productionInfo = databaseProductionManager.getProduction(productionId);
      ArrayList<String[]> finalInfoList = new ArrayList<>();
      ArrayList<String[]> creditsInfo = databaseCreditsManager.getCreditsInfo(productionId);
      for (String[] s : creditsInfo){
         ArrayList<String> getPersonInfo = databasePersonHandler.getPersonToEditMyCredits(Integer.parseInt(s[0]));
         finalInfoList.add(new String[]{getPersonInfo.get(1), getPersonInfo.get(2),
                 getPersonInfo.get(0), productionInfo.get(2), productionInfo.get(3),
                 s[1], s[2]});
      }

      return finalInfoList;
   }

   public boolean exportData(){
      if(ExportData.printFile(getCreditsToDisplay())){
         return true;
      }
      return false;
   }

   public ArrayList<String> getPersonInfo(String username) {
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      int account_id = databasePersonHandler.getId(username);
      ArrayList<String> personInfo = databasePersonHandler.getPersonInfo(account_id);

      return personInfo;
   }

    public ArrayList<String[]> getLoggedInPersonCredits() {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      ArrayList<String[]> creditsInfo = databaseCreditsManager.getLoggedInPersonsCredits(account.getId());
      return creditsInfo;
    }

   public boolean createNewTv_series(ArrayList<String> tvSeriesInfo) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      final ArrayList<String> finalList = new ArrayList<>();
      finalList.add(tvSeriesInfo.get(0));
      finalList.add(tvSeriesInfo.get(1));
      finalList.add(tvSeriesInfo.get(2));
      finalList.add(Integer.toString(account.getId()));
      finalList.add(tvSeriesInfo.get(3));
      finalList.add(tvSeriesInfo.get(4));

      return databaseTvSeriesHandler.insertTvSeries(finalList);
   }

   public boolean createNewSeason(ArrayList<String> seasonInfo) {
      DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
      return databaseSeasonHandler.insertSeason(seasonInfo);
   }

   public ArrayList<String[]> getMySeriesInfo(String production_id) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      return databaseTvSeriesHandler.getSeriesInfo(production_id);

   }

   public ArrayList<String[]> getSelectedSeasonEpisodesInfo(String selectedSeasonToEdit) {
      DatabaseEpisodeHandler databaseEpisodeHandler = new DatabaseEpisodeHandler();
      return databaseEpisodeHandler.getSelectedSeasonEpisodesInfo(selectedSeasonToEdit);

   }

   public String getEpisodeDescription(String selectedEpisodeToEdit) {
      DatabaseEpisodeHandler databaseEpisodeHandler = new DatabaseEpisodeHandler();
      return databaseEpisodeHandler.getDescription(selectedEpisodeToEdit);
   }

   public String getSeriesId(String production_id) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      return databaseTvSeriesHandler.getSeriesId(production_id);
   }


   public boolean createNewEpisode(String selectedSeasonToEdit, String selectedTvSeriesToEdit,
                                   String selectedProductionToEdit, String title,
                                   String description, String txtFieldEpisodeId, String txtFieldEpisodeNumber) {
   DatabaseEpisodeHandler databaseEpisodeHandler = new DatabaseEpisodeHandler();
   return databaseEpisodeHandler.insertEpisode(selectedSeasonToEdit, selectedTvSeriesToEdit, selectedProductionToEdit,
           title, description, txtFieldEpisodeId, txtFieldEpisodeNumber);
   }

   public boolean changeDescription(String selectedTvSeriesToEdit, String newDescription) {
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      return databaseProductionManager.editDescription(selectedTvSeriesToEdit, newDescription);
   }
}
