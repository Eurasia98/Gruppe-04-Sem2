package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditSystem {
   private static ArrayList<String> creditsToDisplay;

   public static ArrayList<String> getCreditsToDisplay() {
        return creditsToDisplay;
    }

   public static Account getAccount() {
      return Login.getAccount();
   }

   public Boolean availableUsername(String username) {
      AccountManager accountManager = new AccountManager();
      return accountManager.checkUsernameAvailability(username);
   }

   public Boolean createNewPerson(ArrayList<String> personInfo) {
       DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
       DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
       if (databaseAccountHandler.insertAccount(personInfo)) {
           ArrayList<String> finalPersonInfo = new ArrayList<>();
           finalPersonInfo.add(databaseAccountHandler.getAccountId(personInfo.get(0)));
           finalPersonInfo.add(personInfo.get(0));
           finalPersonInfo.add(personInfo.get(1));
           finalPersonInfo.add(personInfo.get(4));
           finalPersonInfo.add(personInfo.get(5));
           finalPersonInfo.add(getAccount().getUsername());
           return databasePersonHandler.insertPerson(finalPersonInfo);
       }
       return false;
   }

   public Boolean createSpecialAccount(String username, String password, String accountType){
      AccountManager accountManager = new AccountManager();
      return accountManager.createSpecialAccount(username, password, accountType);
   }

   public Boolean createNewMovie(String title, String productionId) {
      ArrayList<String> movieInfo = new ArrayList<>();

      movieInfo.add(productionId);
      movieInfo.add(title);
      movieInfo.add("Movie");
      movieInfo.add(getAccount().getUsername());
      DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
      return databaseMovieHandler.insertMovie(movieInfo);
   }

    public HashMap<String, String> search(String searchString) {
        DatabaseSearchController databaseSearchController = new DatabaseSearchController();
        HashMap<String, String> searchResultsMap = new HashMap<>();
        ArrayList<SearchResults> searchResultsArrayList = databaseSearchController.search(searchString);
        for (SearchResults searchResult : searchResultsArrayList) {
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
        return databaseSearchController.searchCredits(searchResults);
    }

    public List<String> login(String username, String password) {
        Login login = new Login();
        return login.loginVerify(username, password);
    }

   public ArrayList<String[]> showMyProductions(){
      ProductionManager productionManager = new ProductionManager();
      return productionManager.getMyProductions(getAccount().getUsername());
   }

   public ArrayList<String[]> getMyPersons(){
      DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
       return databasePersonHandler.getMyPersons(getAccount().getUsername());
   }

    public Boolean createNewCredit(ArrayList<String> creditsInfo) {
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.insertCredit(creditsInfo);
    }

    public Boolean editProductionId(String oldProductionId, String newProductionId) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        ArrayList<String> productionInfo = databaseProductionManager.getProduction(oldProductionId);
        if (Login.getAccount().getUsername().equals(productionInfo.get(4))) {
            return databaseProductionManager.editProductionId(oldProductionId, newProductionId);
        }
        return false;
    }

    public Boolean editTitle(String newTitle, String production_id) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.editProductionTitle(newTitle, production_id);
    }

    public ArrayList<String> getProductionInfo(String productionId) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.getProduction(productionId);
    }

    public ArrayList<String[]> getCreditsInfo(String productionId) {
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();

        ArrayList<String[]> finalInfoList = new ArrayList<>();
        ArrayList<String[]> creditsInfo = databaseCreditsManager.getCreditsInfo(productionId);
        for (String[] s : creditsInfo) {
            ArrayList<String> getPersonInfo = databasePersonHandler.getPersonToEditMyCredits(Integer.parseInt(s[2]));
            finalInfoList.add(new String[]{getPersonInfo.get(0) + " " + getPersonInfo.get(1), s[0], s[1]});
        }

        return finalInfoList;
    }

    public boolean exportData(String title) {
        return ExportData.printFile(getCreditsToDisplay(), title);
    }

    public ArrayList<String> getPersonInfo(String username) {
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        int account_id = databasePersonHandler.getId(username);

        return databasePersonHandler.getPersonInfo(account_id);
    }

   public Boolean editAccountPassword(String oldPassword, String newPassword){
      AccountManager accountManager = new AccountManager();
      return accountManager.editAccountPassword(oldPassword, newPassword);
   }

   public Boolean editAccountEmail(String newEmail){
      AccountManager accountManager = new AccountManager();
      return accountManager.editAccountEmail(newEmail);
   }

   public static Producer getProducerAccount() {
      return Login.getProducerAccount();
   }

   public static Person getPersonAccount() {
      return Login.getPersonAccount();
   }

    public ArrayList<String[]> getLoggedInPersonCredits() {
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.getLoggedInPersonsCredits(Login.getAccount().getId());
    }

   public boolean createNewTv_series(ArrayList<String> tvSeriesInfo) {
      DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
      final ArrayList<String> finalList = new ArrayList<>();
      finalList.add(tvSeriesInfo.get(0));
      finalList.add(tvSeriesInfo.get(1));
      finalList.add(tvSeriesInfo.get(2));
      finalList.add(getAccount().getUsername());
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

    public String testProductionType(String productionId) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.printTestProductionType(productionId);
    }

   public boolean createTvProgram(ArrayList<String> tvProgramInfo) {
      DatabaseTvProgramHandler databaseTvProgramHandler = new DatabaseTvProgramHandler();
      ArrayList<String> finalList = new ArrayList<>();
      finalList.add(tvProgramInfo.get(0));
      finalList.add(tvProgramInfo.get(1));
      finalList.add("Tv program");
      finalList.add(getAccount().getUsername());
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
        episodeInfo.add(Login.getAccount().getUsername());
        DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
        return databaseTvProgramEpisodeHandler.insertEpisode(episodeInfo);
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
        return databaseCreditsManager.insertTvProgramCredits(finalList);
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
        if (databaseAccountHandler.insertAccount(accountInfo)) {
            ArrayList<String> personInfo = new ArrayList<>();
            personInfo.add(databaseAccountHandler.getAccountId(creditsInfo.get(0)));
            personInfo.add(creditsInfo.get(0));
            personInfo.add(creditsInfo.get(1));
            personInfo.add(creditsInfo.get(2));
            personInfo.add(creditsInfo.get(3));
            personInfo.add(Login.getAccount().getUsername());
            if (databasePersonHandler.insertPerson(personInfo)) {
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
        if (databaseAccountHandler.insertAccount(accountInfo)) {
            ArrayList<String> personInfo = new ArrayList<>();
            personInfo.add(databaseAccountHandler.getAccountId(creditsInfo.get(0)));
            personInfo.add(creditsInfo.get(0));
            personInfo.add(creditsInfo.get(1));
            personInfo.add(creditsInfo.get(2));
            personInfo.add(creditsInfo.get(3));
            personInfo.add(Login.getAccount().getUsername());
            if (databasePersonHandler.insertPerson(personInfo)) {
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
        return databaseCreditsManager.deleteCredit(role, roleName, productionId);
    }

    public Boolean changeDescriptionSeriesEpisode(String selectedSeriesEpisodeToEdit, String newDescription) {
        DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
        return databaseTvSeriesEpisodeHandler.editDescription(selectedSeriesEpisodeToEdit, newDescription);
    }

    public boolean changeTvSeriesEpisodeNumber(String episode_id, String newEpisodeNumber) {
        DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
        return databaseTvSeriesEpisodeHandler.changeEpisodeNumber(episode_id, newEpisodeNumber);
    }

    public boolean changeTvSeriesEpisodeTitle(String episode_id, String newTitle) {
        DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
        return databaseTvSeriesEpisodeHandler.changeTitle(episode_id, newTitle);
    }

    public boolean changeDescriptionMovie(String productionId, String newDescription) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.editMovieDescription(productionId, newDescription);
    }

    public String getDescription(String productionId) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.getDescription(productionId);
    }

    public String getMovieTitle(String productionId) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.getTitle(productionId);
    }

    public ArrayList<String[]> searchUpdated(String searchField) {
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.searchUpdated(searchField);
    }

    public ArrayList<String[]> getSearchCredits(String productionid) {
        try {
            DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
            DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
            ArrayList<String[]> unSortedFinalList = new ArrayList<>();
            ArrayList<String[]> creditsInfo = databaseCreditsManager.getCreditsInfo(productionid);
            for (String[] s : creditsInfo) {
                ArrayList<String> personInfo = databasePersonHandler.getPersonInfo(Integer.parseInt(s[2]));
                unSortedFinalList.add(new String[]{personInfo.get(4) + " " +personInfo.get(5), s[0], s[1]});
            }
            CreditManager creditManager = new CreditManager();
            ArrayList<String[]> sortedFinalList = creditManager.sortMovieCreditsNames(unSortedFinalList);


            return sortedFinalList;
        } catch (java.lang.NullPointerException e){
            return null;
        }



    }

    public String getAnAccountId(String username) {
        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
        return databaseAccountHandler.getAccountId(username);
    }

    public String getUsername(String account_id) {
       DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
       return databaseAccountHandler.getAccountUsername(account_id);
    }

    public String getEmail(String username) {
       DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
       return databaseAccountHandler.getEmail(username);
    }

    public String getUsernameWithId(int id) {
       DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
       return databaseAccountHandler.getAccountUsernameWithId(id);
    }

    public void editAccountPersonEmail(String newMail, int id) {
       DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
       databaseAccountHandler.editAccountPersonEmail(newMail, id);
    }

    public boolean editSeasonId(String currentId, String newId) {
       DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
       return databaseSeasonHandler.editSeasonId(currentId, newId);
    }

    public boolean editSeasonNumber(String id, String newNumber) {
       DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
       return databaseSeasonHandler.editSeasonNumber(id, newNumber);
    }

    public void editSeriesEpisodeId(String currentId, String newId) {
       DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
       databaseTvSeriesEpisodeHandler.editId(currentId, newId);
    }
}
