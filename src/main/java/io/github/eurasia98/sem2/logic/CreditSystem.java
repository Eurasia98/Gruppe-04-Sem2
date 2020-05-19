package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.*;
import javafx.scene.chart.PieChart;
import org.postgresql.jdbc2.ArrayAssistant;

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

   public Boolean createNewPerson(ArrayList<String> personInfo) {
      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      if (databaseAccountHandler.insertAccount(personInfo) == true){
         ArrayList<String> finalPersonInfo = new ArrayList<>();
         finalPersonInfo.add(databaseAccountHandler.getAccountId(personInfo.get(0)));
         finalPersonInfo.add(personInfo.get(0));
         finalPersonInfo.add(personInfo.get(1));
         finalPersonInfo.add(personInfo.get(4));
         finalPersonInfo.add(personInfo.get(5));
         finalPersonInfo.add(account.getUsername());
         if (databasePersonHandler.insertPerson(finalPersonInfo) == true){
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public Boolean createNewMovie(String title, String productionId) {
      ArrayList<String> movieInfo = new ArrayList<>();

      movieInfo.add(productionId);
      movieInfo.add(title);
      movieInfo.add("Movie");
      movieInfo.add(account.getUsername());
      DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
      return databaseMovieHandler.insertMovie(movieInfo);
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
      return productionManager.getMyProductions(account.getUsername());
   }

   public ArrayList<String[]> getMyPersons(){
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      ArrayList<String[]> myProductionsInfo = databasePersonHandler.getMyPersons(account.getUsername());
      return myProductionsInfo;
   }

   public Boolean createNewCredit(ArrayList<String> creditsInfo){
      CreditManager creditManager = new CreditManager();
      return creditManager.insertCredit(creditsInfo);
   }

   public Boolean editProductionId(String oldProductionId, String newProductionId){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      ArrayList<String> productionInfo = databaseProductionManager.getProduction(oldProductionId);
      if (account.getUsername().equals(productionInfo.get(4))){
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

      ArrayList<String[]> finalInfoList = new ArrayList<>();
      ArrayList<String[]> creditsInfo = databaseCreditsManager.getCreditsInfo(productionId);
      for (String[] s : creditsInfo){
         ArrayList<String> getPersonInfo = databasePersonHandler.getPersonToEditMyCredits(Integer.parseInt(s[2]));
         finalInfoList.add(new String[]{getPersonInfo.get(0) + " " + getPersonInfo.get(1), s[0], s[1]});
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
      finalList.add(account.getUsername());
      finalList.add(tvSeriesInfo.get(3));
      finalList.add(tvSeriesInfo.get(4));

      return databaseTvSeriesHandler.insertTvSeries(finalList);
   }

   public boolean createNewSeason(ArrayList<String> seasonInfo) {
      DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(seasonInfo.get(0));
      finalList.add(seasonInfo.get(1));
      finalList.add(seasonInfo.get(2));
      finalList.add(account.getUsername());
      finalList.add(seasonInfo.get(3));
      finalList.add(seasonInfo.get(4));
      finalList.add(seasonInfo.get(5));
      return databaseSeasonHandler.insertSeason(finalList);
   }

   public ArrayList<String[]> getMySeriesInfo(String production_id) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      return databaseTvSeriesHandler.getSeriesInfo(production_id);

   }

   public ArrayList<String[]> getSelectedSeasonEpisodesInfo(String selectedSeasonToEdit) {
      DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
      return databaseTvSeriesEpisodeHandler.getSelectedSeasonEpisodesInfo(selectedSeasonToEdit);

   }

   public String getEpisodeDescription(String selectedEpisodeToEdit) {
      DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
      return databaseTvSeriesEpisodeHandler.getDescription(selectedEpisodeToEdit);
   }

   public String getSeriesId(String production_id) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      return databaseTvSeriesHandler.getSeriesId(production_id);
   }


   public boolean createNewEpisode(String selectedSeasonToEdit, String selectedTvSeriesToEdit,
                                   String selectedProductionToEdit, String title,
                                   String description, String txtFieldEpisodeId, String txtFieldEpisodeNumber) {
   DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
   return databaseTvSeriesEpisodeHandler.insertEpisode(selectedSeasonToEdit, selectedTvSeriesToEdit, selectedProductionToEdit,
           title, description, txtFieldEpisodeId, txtFieldEpisodeNumber);
   }

   public boolean changeDescription(String selectedTvSeriesToEdit, String newDescription) {
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      return databaseProductionManager.editDescription(selectedTvSeriesToEdit, newDescription);
   }

   public String testProductionType(String productionId){
      DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
      return databaseProductionManager.printTestProductionType(productionId);
   }

   public boolean createTvProgram(ArrayList<String> tvProgramInfo) {
      DatabaseTvProgramHandler databaseTvProgramHandler = new DatabaseTvProgramHandler();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(tvProgramInfo.get(0));
      finalList.add(tvProgramInfo.get(1));
      finalList.add("Tv program");
      finalList.add(account.getUsername());
      finalList.add(tvProgramInfo.get(2));

      return databaseTvProgramHandler.insertTvProgram(finalList);
   }

   public ArrayList<String[]> getTvProgramEpisodesInfo(String selectedProductionToEdit) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      return databaseTvProgramEpisodeHandler.getEpisodesInfo(selectedProductionToEdit);
   }

   public Boolean changeTvProgramEpisodeTitle(String episode_id, String newTitle) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      return databaseTvProgramEpisodeHandler.changeTitle(episode_id, newTitle);
   }

   public boolean changeTvProgramEpisodeId(String episode_id, String newId) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      return databaseTvProgramEpisodeHandler.changeEpisodeId(episode_id, newId);
   }

   public boolean changeTvProgramEpisodeNumber(String episode_id, String newEpisodeNumber) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      return databaseTvProgramEpisodeHandler.changeEpisodeNumber(episode_id, newEpisodeNumber);
   }

   public boolean createNewTvProgramEpisode(String production_id, String episode_number, String episode_title, String episode_id) {
      ArrayList<String> episodeInfo = new ArrayList<>();
      episodeInfo.add(production_id);
      episodeInfo.add(episode_number);
      episodeInfo.add(episode_title);
      episodeInfo.add(episode_id);
      episodeInfo.add(account.getUsername());
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      if (databaseTvProgramEpisodeHandler.insertEpisode(episodeInfo) == true){
         return true;
      } else return false;
   }

   public String getTvProgramEpisodeDescription(String selectedTvProgramEpisodeToEdit) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();

      return databaseTvProgramEpisodeHandler.getDescription(selectedTvProgramEpisodeToEdit);
   }

   public boolean changeTvProgramEpisodeDescription(String episode_id, String newDescription) {
      DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
      return databaseTvProgramEpisodeHandler.changeEpisodeDescription(episode_id, newDescription);
   }

   public boolean createNewTvProgramCredit(ArrayList<String> creditsInfo) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(creditsInfo.get(0));
      finalList.add(creditsInfo.get(1));
      finalList.add(databaseAccountHandler.getAccountId(creditsInfo.get(2)));
      return databaseCreditsManager.insertTvProgramCredits(finalList);
   }

   public boolean createNewTvProgramCreditWithUserId(ArrayList<String> creditsInfo) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(creditsInfo.get(0));
      finalList.add(creditsInfo.get(1));
      finalList.add(creditsInfo.get(2));
      return databaseCreditsManager.insertTvProgramCredits(creditsInfo);
   }

   public boolean createNewTvProgramCreditAndPerson(ArrayList<String> creditsInfo) {
      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      ArrayList<String> accountInfo = new ArrayList<>();
      accountInfo.add(creditsInfo.get(0));
      accountInfo.add(creditsInfo.get(1));
      accountInfo.add(creditsInfo.get(4));
      accountInfo.add("Person");
      if (databaseAccountHandler.insertAccount(accountInfo) == true){
         ArrayList<String> personInfo = new ArrayList<>();
         personInfo.add(databaseAccountHandler.getAccountId(creditsInfo.get(0)));
         personInfo.add(creditsInfo.get(0));
         personInfo.add(creditsInfo.get(1));
         personInfo.add(creditsInfo.get(2));
         personInfo.add(creditsInfo.get(3));
         personInfo.add(account.getUsername());
         if (databasePersonHandler.insertPerson(personInfo) == true){
            ArrayList<String> finalList = new ArrayList<>();
            finalList.add(creditsInfo.get(5));
            finalList.add(creditsInfo.get(6));
            finalList.add(personInfo.get(0));
            return databaseCreditsManager.insertTvProgramCredits(finalList);
         } else return false;
      } else return false;
   }

    public boolean createNewTvSeriesCredit(ArrayList<String> creditInfo) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(creditInfo.get(0));
      finalList.add(creditInfo.get(1));
      finalList.add(creditInfo.get(2));
      finalList.add(creditInfo.get(3));
      return databaseCreditsManager.insertCredit(finalList);
    }

   public boolean deleteCredit(String role, String roleName, String productionId) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      return databaseCreditsManager.deleteCredit(role, roleName, productionId);
   }

   public boolean deleteCreditFromTvProgram(String role, String productionId) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      return databaseCreditsManager.deleteCreditFromTvProgram(role, productionId);
   }

   public boolean createNewTvSeriesCreditAndPerson(ArrayList<String> creditsInfo) {
      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      ArrayList<String> accountInfo = new ArrayList<>();
      accountInfo.add(creditsInfo.get(0));
      accountInfo.add(creditsInfo.get(1));
      accountInfo.add(creditsInfo.get(4));
      accountInfo.add("Person");
      if (databaseAccountHandler.insertAccount(accountInfo) == true){
         ArrayList<String> personInfo = new ArrayList<>();
         personInfo.add(databaseAccountHandler.getAccountId(creditsInfo.get(0)));
         personInfo.add(creditsInfo.get(0));
         personInfo.add(creditsInfo.get(1));
         personInfo.add(creditsInfo.get(2));
         personInfo.add(creditsInfo.get(3));
         personInfo.add(account.getUsername());
         if (databasePersonHandler.insertPerson(personInfo) == true){
            ArrayList<String> finalList = new ArrayList<>();
            finalList.add(personInfo.get(0));
            finalList.add(creditsInfo.get(7));
            finalList.add(creditsInfo.get(5));
            finalList.add(creditsInfo.get(6));
            return databaseCreditsManager.insertCredit(finalList);
         } else return false;
      } else return false;

   }

   public boolean createNewTvSeriesCreditWithUsername(ArrayList<String> creditInfo) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(databaseAccountHandler.getAccountId(creditInfo.get(0)));
      finalList.add(creditInfo.get(1));
      finalList.add(creditInfo.get(2));
      finalList.add(creditInfo.get(3));
      return databaseCreditsManager.insertCredit(finalList);
   }

   public boolean deleteCreditFromTvSeries(String role, String roleName, String productionId) {
      DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
      return databaseCreditsManager.deleteCredit(role, roleName,productionId);
   }
}
