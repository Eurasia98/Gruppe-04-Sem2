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
    private static String selectedProductionToEdit;
    private static String selectedTvSeriesToEdit;
    private static String selectedSeasonToEdit;
    private static String selectedEpisodeToEdit;

    public static String getSelectedTvSeriesToEdit() {
        return selectedTvSeriesToEdit;
    }

    public static void setSelectedTvSeriesToEdit(String selectedTvSeriesToEdit) {
        App.selectedTvSeriesToEdit = selectedTvSeriesToEdit;
    }

    public static String getSelectedEpisodeToEdit() {
        return selectedEpisodeToEdit;
    }

    public static void setSelectedEpisodeToEdit(String selectedEpisodeToEdit) {
        App.selectedEpisodeToEdit = selectedEpisodeToEdit;
    }

    public static String getSelectedSeasonToEdit() {
        return selectedSeasonToEdit;
    }

    public static void setSelectedSeasonToEdit(String selectedSeasonToEdit) {
        App.selectedSeasonToEdit = selectedSeasonToEdit;
    }

    public static String getSelectedProductionToEdit() {
        return selectedProductionToEdit;
    }

    public static void setSelectedProductionToEdit(String selectedProductionToEdit) {
        App.selectedProductionToEdit = selectedProductionToEdit;
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