package io.github.eurasia98.sem2.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import io.github.eurasia98.sem2.logic.CreditSystem;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    //this is a comment
    private static Scene scene;
    private static Stage window;
    static CreditSystem creditSystem = new CreditSystem();

    public static String searchFieldString;
    public static String selectedTitle;
    public static boolean loggedIn;

    // Needs to do the opposite of loggedIn in the future (for logging out)
    public static void setLoggedIn(boolean loggedIn) {
        App.loggedIn = loggedIn;
    }

    //bruges til at sende string fra frontpage searchfield til searchscreencontroller i initialize.
    public static void setSearchField(String searchString){
        searchFieldString = searchString;
    }

    public static void setSelectedTitle(Hyperlink selectedHyperlink){
        selectedTitle = selectedHyperlink.getText();
    }

    public CreditSystem getCreditSystem() {
        return creditSystem;
    }

    //bruges til at skifte mellem fxml filerne.
    public static void switchScene(String sceneName){
        try {
            scene.setRoot(loadFXML(sceneName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("FrontPage"));
        window.setScene(scene);
        window.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    //loader fxml dokumentet
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //lukker programmet
    public void closeSystem(){
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    public static void launch(String[] args) {
        launch();
    }
}