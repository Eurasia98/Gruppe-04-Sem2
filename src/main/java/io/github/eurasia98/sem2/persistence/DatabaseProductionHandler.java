package io.github.eurasia98.sem2.persistence;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseProductionHandler {
    static Connection connection = null;
    private ArrayList<String> backupProductions;

    public Boolean insertProduction(ArrayList<String> productionInfo) {
        this.connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(production_id, title, production_type, owner_id) VALUES(?,?,?,?)");

            insertProductionStatement.setString(1, productionInfo.get(0));
            insertProductionStatement.setString(2, productionInfo.get(1));
            insertProductionStatement.setString(3, productionInfo.get(2));
            insertProductionStatement.setString(4, productionInfo.get(3));

            return insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getProduction(String production_id) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> productionInfo = new ArrayList<>();

        try {
            PreparedStatement getProductionStatement = connection.prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            getProductionStatement.setString(1, production_id);

            ResultSet rs = getProductionStatement.executeQuery();

            while (rs.next()) {
                productionInfo.add(rs.getString(1));
                productionInfo.add(Integer.toString(rs.getInt(2)));
                productionInfo.add(rs.getString(3));
                productionInfo.add(rs.getString(4));
                productionInfo.add(rs.getString(5));
                productionInfo.add(rs.getString(6));
            }

            return productionInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

/*    public Production getProduction(String production_id){
        connection = DatabaseAccesHandler.getConnection();


        try {
            PreparedStatement getProductionStatement = connection.prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            getProductionStatement.setString(1, production_id);

            ResultSet rs = getProductionStatement.executeQuery();

            ProductionManager productionManager = new ProductionManager();

            while (rs.next()){
                Production production = productionManager.createProduction(rs.getString("title"), rs.getString("production_id"));
                return production;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }*/

    public ArrayList<String[]> getMyProductions(String username) {
        connection = DatabaseAccessHandler.getConnection();
        String[] productionInfoArray;
        ArrayList<String[]> productionsInfo = new ArrayList<>();

        try {
            PreparedStatement getMyProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE owner_id = ?");
            getMyProductionsStatement.setString(1, username);
            ResultSet rs = getMyProductionsStatement.executeQuery();
            while (rs.next()) {
                productionInfoArray = new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)};
                productionsInfo.add(productionInfoArray);
            }
            return productionsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String printTestProductionType(String productionId) {
        //System.out.println(getProductionType(productionId));
        return getProductionType(productionId);
    }

    public Boolean editProductionId(String oldProductionId, String newProductionId) {
        try {
            String productionType = getProductionType(oldProductionId);
            connection = DatabaseAccessHandler.getConnection();

                PreparedStatement updateProductionIdStatement = connection.prepareStatement(
                        "UPDATE productions SET production_id = ? WHERE production_id = ?");
                updateProductionIdStatement.setString(1, newProductionId);
                updateProductionIdStatement.setString(2, oldProductionId);
                updateProductionIdStatement.execute();
                return true;
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
            }
    }

/*        switch (productionType) {
            case "Movie":
                System.out.println("Første test");
                DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
                if (databaseMovieHandler.insertBackupMovies(oldProductionId) == true) {
                    System.out.println("First true");
                    if (insertBackupProductions(oldProductionId) == true) {
                        System.out.println("Second true");
                        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
                        ArrayList<Integer> personsInCreditsAccountId = databaseCreditsManager.getAllPersonsFromCreditsAccountId(oldProductionId);
                        if (databaseCreditsManager.insertBackupCredits(oldProductionId) == true) {
                            System.out.println("Third true");
                            DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
                            if (databasePersonHandler.insertBackupPersons(personsInCreditsAccountId) == true) {
                                System.out.println("Fourth true");
                                DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
                                if (databaseAccountHandler.insertBackupAccounts(personsInCreditsAccountId) == true) {
                                    System.out.println("Fifth true");
                                    try {
                                        connection.setAutoCommit(false);
                                        PreparedStatement deleteMovieRowStatement = connection.prepareStatement(
                                                "DELETE FROM movies WHERE production_id = ?");
                                        deleteMovieRowStatement.setString(1, oldProductionId);
                                        deleteMovieRowStatement.execute();

                                        PreparedStatement deleteCreditsRowsStatement = connection.prepareStatement(
                                                "DELETE FROM credits WHERE production_id = ?");
                                        deleteCreditsRowsStatement.setString(1, oldProductionId);
                                        deleteCreditsRowsStatement.execute();

                                        PreparedStatement deletePersonsRowStatement = connection.prepareStatement(
                                                "DELETE FROM persons WHERE account_id = ?");

                                        for (int accountId : personsInCreditsAccountId) {
                                            deletePersonsRowStatement.setInt(1, accountId);
                                            deletePersonsRowStatement.addBatch();
                                        }

                                        deletePersonsRowStatement.executeBatch();

                                        PreparedStatement deleteAccountsRowStatement = connection.prepareStatement(
                                                "DELETE FROM accounts WHERE id = ?");

                                        for (int accountId : personsInCreditsAccountId) {
                                            deleteAccountsRowStatement.setInt(1, accountId);
                                            deleteAccountsRowStatement.addBatch();
                                        }

                                        deleteAccountsRowStatement.executeBatch();

                                        PreparedStatement deleteProductionRowStatement = connection.prepareStatement(
                                                "DELETE FROM productions WHERE production_id = ?");
                                        deleteProductionRowStatement.setString(1, oldProductionId);
                                        deleteProductionRowStatement.execute();

                                        connection.commit();
                                        connection.close();

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                    ArrayList<String> productionInfo = getBackupProductions(oldProductionId, newProductionId);
                                    ArrayList<String[]> accountsInfo = databaseAccountHandler.getBackupAccounts();
                                    ArrayList<String> movieInfo = databaseMovieHandler.getBackupMovieInfo(oldProductionId, newProductionId);
                                    ArrayList<String[]> personsInfo = databasePersonHandler.getBackupPersons();
                                    ArrayList<String[]> creditsInfo = databaseCreditsManager.getBackupCreditsInfo(newProductionId, oldProductionId);

                                    databaseAccountHandler.editInsertAccounts(accountsInfo);
                                    databasePersonHandler.editInsertPersons(personsInfo);
                                    editInsertProduction(productionInfo);
                                    databaseMovieHandler.editInsertMovies(movieInfo);
                                    databaseCreditsManager.editInsertCredits(creditsInfo);

                                    resetBackupTables();

                                    return true;
                                }
                            }
                        }
                    }
                }

            case "Serie":
                System.out.println("Første print serie");
                DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
                if (databaseTvSeriesEpisodeHandler.insertBackupEpisodes(oldProductionId) == true) {
                    System.out.println("First true");
                    DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
                    if (databaseSeasonHandler.insertBackupSeasons(oldProductionId) == true) {
                        System.out.println("Second true");
                        DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
                        if (databaseTvSeriesHandler.insertBackupSeries(oldProductionId) == true) {
                            System.out.println("Third true");
                            if (insertBackupProductions(oldProductionId) == true) {
                                DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
                                ArrayList<Integer> personsInCreditsAccountId = databaseCreditsManager.getAllPersonsFromCreditsAccountId(oldProductionId);
                                if (databaseCreditsManager.insertBackupCredits(oldProductionId) == true) {
                                    System.out.println("Fourth true");
                                    DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
                                    if (databasePersonHandler.insertBackupPersons(personsInCreditsAccountId) == true) {
                                        DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
                                        if (databaseAccountHandler.insertBackupAccounts(personsInCreditsAccountId) == true) {
                                            System.out.println("Sixth true");
                                            try {
                                                connection.setAutoCommit(false);
                                                PreparedStatement deleteEpisodeRowStatement = connection.prepareStatement(
                                                        "DELETE FROM episodes WHERE production_id = ?");
                                                deleteEpisodeRowStatement.setString(1, oldProductionId);
                                                deleteEpisodeRowStatement.execute();

                                                PreparedStatement deleteSeasonRowStatement = connection.prepareStatement(
                                                        "DELETE FROM seasons WHERE production_id = ?");
                                                deleteSeasonRowStatement.setString(1, oldProductionId);
                                                deleteSeasonRowStatement.execute();

                                                PreparedStatement deleteSeriesRowStatement = connection.prepareStatement(
                                                        "DELETE FROM tv_series WHERE production_id = ?");
                                                deleteSeriesRowStatement.setString(1, oldProductionId);
                                                deleteSeriesRowStatement.execute();

                                                PreparedStatement deleteCreditsRowsStatement = connection.prepareStatement(
                                                        "DELETE FROM credits WHERE production_id = ?");
                                                deleteCreditsRowsStatement.setString(1, oldProductionId);
                                                deleteCreditsRowsStatement.execute();

                                                PreparedStatement deletePersonsRowStatement = connection.prepareStatement(
                                                        "DELETE FROM persons WHERE account_id = ?");

                                                for (int accountId : personsInCreditsAccountId) {
                                                    deletePersonsRowStatement.setInt(1, accountId);
                                                    deletePersonsRowStatement.addBatch();
                                                }

                                                deletePersonsRowStatement.executeBatch();

                                                PreparedStatement deleteAccountsRowStatement = connection.prepareStatement(
                                                        "DELETE FROM accounts WHERE id = ?");

                                                for (int accountId : personsInCreditsAccountId) {
                                                    deleteAccountsRowStatement.setInt(1, accountId);
                                                    deleteAccountsRowStatement.addBatch();
                                                }

                                                deleteAccountsRowStatement.executeBatch();

                                                PreparedStatement deleteProductionRowStatement = connection.prepareStatement(
                                                        "DELETE FROM productions WHERE production_id = ?");
                                                deleteProductionRowStatement.setString(1, oldProductionId);
                                                deleteProductionRowStatement.execute();

                                                connection.commit();
                                                connection.close();

                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }
                                            ArrayList<String> productionInfo = getBackupProductions(oldProductionId, newProductionId);
                                            ArrayList<String[]> accountsInfo = databaseAccountHandler.getBackupAccounts();
                                            ArrayList<String[]> personsInfo = databasePersonHandler.getBackupPersons();
                                            ArrayList<String[]> creditsInfo = databaseCreditsManager.getBackupCreditsInfo(newProductionId, oldProductionId);
                                            ArrayList<String[]> seasonsInfo = databaseSeasonHandler.getBackupSeasonInfo(newProductionId, oldProductionId);
                                            ArrayList<String[]> episodesInfo = databaseTvSeriesEpisodeHandler.getBackupEpisodeInfo(newProductionId, oldProductionId);
                                            ArrayList<String> series_info = databaseTvSeriesHandler.getBackupSeriesInfo(newProductionId, oldProductionId);

                                            editInsertProduction(productionInfo);
                                            databaseAccountHandler.editInsertAccounts(accountsInfo);
                                            databaseTvSeriesHandler.editInsertTvSeries(series_info);
                                            databaseSeasonHandler.editInsertSeasons(seasonsInfo);
                                            databaseTvSeriesEpisodeHandler.editInsertEpisodes(episodesInfo);
                                            databasePersonHandler.editInsertPersons(personsInfo);
                                            databaseCreditsManager.editInsertCredits(creditsInfo);

                                            resetBackupTables();

                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            case "Tv program":
                System.out.println("First true tvprogram");
                DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
                if (databaseTvProgramEpisodeHandler.insertBackupEpisodes(oldProductionId) == true) {
                    System.out.println("Thrid true tvprogram");
                    DatabaseTvProgramHandler databaseTvProgramHandler = new DatabaseTvProgramHandler();
                    if (databaseTvProgramHandler.insertBackupTvProgram(oldProductionId) == true) {
                        System.out.println("Fourth true tvprograms");
                        if (insertBackupProductions(oldProductionId) == true) {
                            DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
                            ArrayList<Integer> personsInCreditsAccountId = databaseCreditsManager.getAllPersonsFromCreditsAccountId(oldProductionId);
                            if (databaseCreditsManager.insertBackupCredits(oldProductionId) == true) {
                                System.out.println("Fifth true tvprogram");
                                DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
                                if (databasePersonHandler.insertBackupPersons(personsInCreditsAccountId) == true) {
                                    System.out.println("Sixth true tvprogram");
                                    DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
                                    if (databaseAccountHandler.insertBackupAccounts(personsInCreditsAccountId) == true) {
                                        try {
                                            connection.setAutoCommit(false);
                                            PreparedStatement deleteTvProgramEpisodesRowStatement = connection.prepareStatement(
                                                    "DELETE FROM tvprogram_episodes WHERE production_id = ?");
                                            deleteTvProgramEpisodesRowStatement.setString(1, oldProductionId);
                                            deleteTvProgramEpisodesRowStatement.execute();

                                            PreparedStatement deleteTvProgramRowStatement = connection.prepareStatement(
                                                    "DELETE FROM tv_programs WHERE production_id = ?");
                                            deleteTvProgramRowStatement.setString(1, oldProductionId);
                                            deleteTvProgramRowStatement.execute();

                                            PreparedStatement deleteCreditsRowsStatement = connection.prepareStatement(
                                                    "DELETE FROM credits WHERE production_id = ?");
                                            deleteCreditsRowsStatement.setString(1, oldProductionId);
                                            deleteCreditsRowsStatement.execute();

                                            PreparedStatement deletePersonsRowStatement = connection.prepareStatement(
                                                    "DELETE FROM persons WHERE account_id = ?");

                                            for (int accountId : personsInCreditsAccountId) {
                                                deletePersonsRowStatement.setInt(1, accountId);
                                                deletePersonsRowStatement.addBatch();
                                            }

                                            deletePersonsRowStatement.executeBatch();

                                            PreparedStatement deleteAccountsRowStatement = connection.prepareStatement(
                                                    "DELETE FROM accounts WHERE id = ?");

                                            for (int accountId : personsInCreditsAccountId) {
                                                deleteAccountsRowStatement.setInt(1, accountId);
                                                deleteAccountsRowStatement.addBatch();
                                            }

                                            deleteAccountsRowStatement.executeBatch();

                                            PreparedStatement deleteProductionRowStatement = connection.prepareStatement(
                                                    "DELETE FROM productions WHERE production_id = ?");
                                            deleteProductionRowStatement.setString(1, oldProductionId);
                                            deleteProductionRowStatement.execute();

                                            connection.commit();
                                            connection.close();

                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        ArrayList<String> productionInfo = getBackupProductions(oldProductionId, newProductionId);
                                        ArrayList<String[]> accountsInfo = databaseAccountHandler.getBackupAccounts();
                                        ArrayList<String[]> personsInfo = databasePersonHandler.getBackupPersons();
                                        ArrayList<String[]> creditsInfo = databaseCreditsManager.getBackupCreditsInfo(newProductionId, oldProductionId);
                                        ArrayList<String[]> episodesInfo = databaseTvProgramEpisodeHandler.getBackupTvProgramEpisodesInfo(newProductionId, oldProductionId);
                                        ArrayList<String> tvprogram_info = databaseTvProgramHandler.getBackupTvProgramInfo(newProductionId, oldProductionId);

                                        editInsertProduction(productionInfo);
                                        databaseAccountHandler.editInsertAccounts(accountsInfo);
                                        databaseTvProgramHandler.editInsertTvProgram(tvprogram_info);
                                        databaseTvProgramEpisodeHandler.editInsertEpisodes(episodesInfo);
                                        databasePersonHandler.editInsertPersons(personsInfo);
                                        databaseCreditsManager.editInsertCredits(creditsInfo);

                                        resetBackupTables();

                                        return true;
                                    }
                                }
                            }

                        }
                    }
                }
        }*/
       // return false;
    // } ** Hører til editproduktion ovenfor - lukker den.

/*    private void resetBackupTables() {
        try {
            PreparedStatement deleteBackupMoviesStatement = connection.prepareStatement(
                    "DELETE FROM backup_movies");
            deleteBackupMoviesStatement.execute();

            PreparedStatement deleteBackupEpisodes = connection.prepareStatement(
                    "DELETE FROM backup_episodes");
            deleteBackupEpisodes.execute();

            PreparedStatement deleteBackupProductions = connection.prepareStatement(
                    "DELETE FROM backup_productions");
            deleteBackupProductions.execute();

            PreparedStatement deleteBackupPersons = connection.prepareStatement(
                    "DELETE FROM backup_persons");
            deleteBackupPersons.execute();

            PreparedStatement deleteBackupCredits = connection.prepareStatement(
                    "DELETE FROM backup_credits");
            deleteBackupCredits.execute();

            PreparedStatement deleteBackupAccounts = connection.prepareStatement(
                    "DELETE FROM backup_accounts");
            deleteBackupAccounts.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public Boolean editProductionTitle(String newTitle, String production_id) {
        connection = DatabaseAccessHandler.getConnection();
        String productionType = getProductionType(production_id);

        switch (productionType) {
            case "Movie":
                DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
                if (databaseMovieHandler.editTitle(newTitle, production_id) == true) {
                    try {
                        PreparedStatement editProductionTitle = connection.prepareStatement(
                                "UPDATE productions SET title = ? WHERE production_id = ?");
                        editProductionTitle.setString(1, newTitle);
                        editProductionTitle.setString(2, production_id);
                        editProductionTitle.execute();
                        return true;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            case "Serie":
                DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
                if (databaseTvSeriesHandler.editTitle(newTitle, production_id) == true) {
                    try {
                        PreparedStatement editProductionTitle = connection.prepareStatement(
                                "UPDATE productions SET title = ? WHERE production_id = ?");
                        editProductionTitle.setString(1, newTitle);
                        editProductionTitle.setString(2, production_id);
                        editProductionTitle.execute();
                        return true;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            case "Tv program":
                DatabaseTvProgramHandler databaseTvProgramHandler = new DatabaseTvProgramHandler();
                if (databaseTvProgramHandler.editTitle(newTitle, production_id) == true){
                    try {
                        PreparedStatement editProductionTitle = connection.prepareStatement(
                                "UPDATE productions SET title = ? WHERE production_id = ?");
                        editProductionTitle.setString(1, newTitle);
                        editProductionTitle.setString(2, production_id);
                        editProductionTitle.execute();
                        return true;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        }
        return false;
    }

    private String getProductionType(String production_id) {
        try {
            PreparedStatement getProductionType = connection.prepareStatement(
                    "SELECT production_type FROM productions WHERE production_id = ?");
            getProductionType.setString(1, production_id);

            ResultSet productionTypeResultSet = getProductionType.executeQuery();

            while (productionTypeResultSet.next()) {
                return productionTypeResultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Boolean insertBackupProductions(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE production_id = ?");
            backupProductionsStatement.setString(1, oldProductionId);

            ResultSet backupProductionResultSet = backupProductionsStatement.executeQuery();

            ArrayList<String> productionInfo = new ArrayList<>();

            while (backupProductionResultSet.next()) {
                productionInfo.add(backupProductionResultSet.getString(1));
                productionInfo.add(backupProductionResultSet.getString(2));
                productionInfo.add(backupProductionResultSet.getString(3));
                productionInfo.add(backupProductionResultSet.getString(4));
                productionInfo.add(backupProductionResultSet.getString(5));
                productionInfo.add(backupProductionResultSet.getString(6));
            }

            PreparedStatement insertBackupProductions = connection.prepareStatement(
                    "INSERT INTO backup_productions(production_id, id, title, production_type, " +
                            "owner_id, description)" +
                            "VALUES(?,?,?,?,?,?)");

            insertBackupProductions.setString(1, productionInfo.get(0));
            insertBackupProductions.setInt(2, Integer.parseInt(productionInfo.get(1)));
            insertBackupProductions.setString(3, productionInfo.get(2));
            insertBackupProductions.setString(4, productionInfo.get(3));
            insertBackupProductions.setString(5, productionInfo.get(4));
            insertBackupProductions.setString(6, productionInfo.get(5));

            insertBackupProductions.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Boolean checkBackupProductions(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_productions WHERE production_id = ?");
            checkUpdateStatement.setString(1, oldProductionId);

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            if (checkUpdateResultset.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Boolean checkIfProductionIsDeleted(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement productionStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE production_id = ?");
            productionStatement.setString(1, oldProductionId);

            ResultSet productionsResultset = productionStatement.executeQuery();

            if (productionsResultset.next()) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    private void editInsertProduction(ArrayList<String> productionInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(production_id, id, title, production_type," +
                            "owner_id, description) VALUES(?,?,?,?,?,?)");
            insertProductionStatement.setString(1, productionInfo.get(0));
            insertProductionStatement.setInt(2, Integer.parseInt(productionInfo.get(1)));
            insertProductionStatement.setString(3, productionInfo.get(2));
            insertProductionStatement.setString(4, productionInfo.get(3));
            insertProductionStatement.setString(5, productionInfo.get(4));
            insertProductionStatement.setString(6, productionInfo.get(5));

            insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ArrayList<String> getBackupProductions(String oldProductionId, String newProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> productionsInfo = new ArrayList<>();

        try {
            PreparedStatement getProductionStatement = connection.prepareStatement(
                    "SELECT * FROM backup_productions WHERE production_id = ?");
            getProductionStatement.setString(1, oldProductionId);
            ResultSet productionsResultSet = getProductionStatement.executeQuery();

            while (productionsResultSet.next()) {
                productionsInfo.add(newProductionId);
                productionsInfo.add(productionsResultSet.getString(2));
                productionsInfo.add(productionsResultSet.getString(3));
                productionsInfo.add(productionsResultSet.getString(4));
                productionsInfo.add(productionsResultSet.getString(5));
                productionsInfo.add(productionsResultSet.getString(6));
            }
            return productionsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getTitle(String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement getTitleStatement = connection.prepareStatement("SELECT title FROM productions WHERE production_id = ?");
            getTitleStatement.setString(1, production_id);

            ResultSet titleResultSet = getTitleStatement.executeQuery();

            while (titleResultSet.next()) {
                return titleResultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean editDescription(String selectedTvSeriesToEdit, String newDescription) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editDescriptionStatement = connection.prepareStatement(
                    "UPDATE tv_series SET description = ? WHERE series_id = ?");
            editDescriptionStatement.setString(1, newDescription);
            editDescriptionStatement.setString(2, selectedTvSeriesToEdit);
            editDescriptionStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean editMovieDescription(String productionId, String newDescription) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editDescriptionStatement = connection.prepareStatement(
                    "UPDATE productions SET description = ? WHERE production_id = ?");
            editDescriptionStatement.setString(1, newDescription);
            editDescriptionStatement.setString(2, productionId);
            editDescriptionStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String getDescription(String productionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editDescriptionStatement = connection.prepareStatement(
                    "SELECT description FROM productions WHERE production_id = ?");
            editDescriptionStatement.setString(1, productionId);
            ResultSet descriptionResultSet = editDescriptionStatement.executeQuery();

            if (descriptionResultSet.next()){
                return descriptionResultSet.getString(1);
            } else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<String[]> searchUpdated(String searchField) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            ArrayList<String[]> searchInfo = new ArrayList<>();
            PreparedStatement searchStatement = connection.prepareStatement(
                    "SELECT title, production_id FROM productions WHERE title ilike ?");
            searchStatement.setString(1, "%" + searchField + "%");

            ResultSet searchResultSet = searchStatement.executeQuery();

            while (searchResultSet.next()){
                searchInfo.add(new String[]{searchResultSet.getString(1), searchResultSet.getString(2)});
            }
            return searchInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public boolean deleteProduction(String production_id) {
        connection = DatabaseAccessHandler.getConnection();
        DatabaseCreditsHandler databaseCreditsHandler = new DatabaseCreditsHandler();
        DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
        DatabaseSeasonHandler databaseSeasonHandler = new DatabaseSeasonHandler();
        DatabaseTvSeriesEpisodeHandler databaseTvSeriesEpisodeHandler = new DatabaseTvSeriesEpisodeHandler();
        DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
        DatabaseTvProgramHandler databaseTvProgramHandler = new DatabaseTvProgramHandler();
        DatabaseTvProgramEpisodeHandler databaseTvProgramEpisodeHandler = new DatabaseTvProgramEpisodeHandler();
        String productionType = getProductionType(production_id);
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();


            switch (productionType){
                case "Movie":
                    if (databaseCreditsHandler.deleteCreditDeleteProduction(production_id) == true){
                        if (databaseMovieHandler.deleteMovie(production_id) == true){
                            connection.commit();
                            connection.close();
                            return deleteProductionFinal(production_id);
                        } else {
                            connection.rollback(savepoint);
                            return false;
                        }
                    } else {
                        connection.rollback(savepoint);
                        return false;
                    }
                case "Serie":
                    if (databaseTvSeriesEpisodeHandler.deleteEpisode(production_id) == true){
                        if (databaseSeasonHandler.deleteSeason(production_id) == true){
                            if (databaseTvSeriesHandler.deleteSeries(production_id) == true){
                                if (databaseCreditsHandler.deleteCreditDeleteProduction(production_id) == true){
                                    connection.commit();
                                    connection.close();
                                    return deleteProductionFinal(production_id);
                                }
                            } else {
                                connection.rollback(savepoint);
                                return false;
                            }
                        } else {
                            connection.rollback(savepoint);
                            return false;
                        }
                    } else return false;
                case "Tv program":
                    if (databaseTvProgramEpisodeHandler.deleteEpisode(production_id) == true){
                        if (databaseTvProgramHandler.deleteProgram(production_id) == true){
                            connection.commit();
                            connection.close();
                            return deleteProductionFinal(production_id);
                        } else {
                            connection.rollback(savepoint);
                            return false;
                        }
                    } else {
                        connection.rollback(savepoint);
                        return false;
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } {
            return false;
        }
    }

    private Boolean deleteProductionFinal(String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM productions WHERE production_id = ?");
            deleteStatement.setString(1, production_id);
            deleteStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

/*    public ArrayList<Production> getMyProductions(int account_id){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<Production> myProductions = new ArrayList<>();
        ProductionManager productionManager = new ProductionManager();

        try {
            PreparedStatement getMyProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE owner = ?");
            getMyProductionsStatement.setInt(1, account_id);
            ResultSet rs = getMyProductionsStatement.executeQuery();
            while (rs.next()){
                Production production = productionManager.createProduction(rs.getString("title"), rs.getString("production_id"));
                myProductions.add(production);
            }
            return myProductions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }*/
}
