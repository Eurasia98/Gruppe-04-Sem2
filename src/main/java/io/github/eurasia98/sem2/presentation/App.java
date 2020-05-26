package io.github.eurasia98.sem2.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import io.github.eurasia98.sem2.logic.CreditSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application{

    private static Scene scene;
    private static Stage window;
    private static CreditSystem creditSystem = new CreditSystem();
    private static List<String> userInfo = new ArrayList<>();
    private static String searchField;
    private static String selectedProduction;
    private static String selectedTitle;
    private static String selectedTvSeries;
    private static String selectedSeason;
    private static String selectedSeriesEpisode;
    private static String selectedTvProgramEpisode;
    private static String selectedSearchResult;
    private static ArrayList<String[]> selectedCreditsToExport;

    public static ArrayList<String[]> getSelectedCreditsToExport() {
        return selectedCreditsToExport;
    }

    public static void setSelectedCreditsToExport(ArrayList<String[]> selectedCreditsToExport) {
        App.selectedCreditsToExport = selectedCreditsToExport;
    }

    public static String getSelectedSearchResult() {
        return selectedSearchResult;
    }

    public static void setSelectedSearchResult(String selectedSearchResult) {
        App.selectedSearchResult = selectedSearchResult;
    }

    public static void resetSelects(){
        searchField = null;
        selectedProduction = null;
        selectedTvSeries = null;
        selectedSeason = null;
        selectedSeriesEpisode = null;
        selectedTvProgramEpisode = null;
    }

    public static String getSelectedTvProgramEpisode() {
        return selectedTvProgramEpisode;
    }

    public static void setSelectedTvProgramEpisode(String selectedTvProgramEpisode) {
        App.selectedTvProgramEpisode = selectedTvProgramEpisode;
    }

    public static String getSelectedTvSeries() {
        return selectedTvSeries;
    }

    public static void setSelectedTvSeries(String selectedTvSeries) {
        App.selectedTvSeries = selectedTvSeries;
    }

    public static String getSelectedSeriesEpisode() {
        return selectedSeriesEpisode;
    }

    public static void setSelectedSeriesEpisode(String selectedSeriesEpisode) {
        App.selectedSeriesEpisode = selectedSeriesEpisode;
    }

    public static String getSelectedSeason() {
        return selectedSeason;
    }

    public static void setSelectedSeason(String selectedSeason) {
        App.selectedSeason = selectedSeason;
    }

    public static String getSelectedProduction() {
        return selectedProduction;
    }

    public static void setSelectedProduction(String selectedProduction) {
        App.selectedProduction = selectedProduction;
    }

    public static String getSearchField() {
        return searchField;
    }

    public static void setSearchField(String searchString) {
        searchField = searchString;
    }

    public static CreditSystem getCreditSystem() {
        return creditSystem;
    }

    public static List<String> getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(List<String> userInfo) {
        App.userInfo = userInfo;
    }

    public static String getSelectedTvSeriesToEditFromProductionId(String production_id) {
        return creditSystem.getSeriesId(production_id);
    }

    public static String getSelectedTitle() {
        return selectedTitle;
    }

    public static void setSelectedTitle(String selectedTitle) {
        App.selectedTitle = selectedTitle;
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("KrediteringsSystem");
        scene = new Scene(loadFXML("FrontPage"));
        window.setScene(scene);
        window.setTitle("");
        window.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void closeSystem() {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    public static void switchScene(String sceneString){
        try {
            scene.setRoot(loadFXML(sceneString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void launch(String[] args) {
        launch();
    }

}