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
    static CreditSystem creditSystem;

    public CreditSystem getCreditSystem() {
        return creditSystem;
    }

    private final SearchScreenController searchScreen = new SearchScreenController();

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("FrontPage"));
        window.setScene(scene);
        window.show();
    }

    public static void injectCreditSystem(CreditSystem cs){
        creditSystem = cs;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchScene(String sceneName){
        try {
            scene.setRoot(loadFXML(sceneName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchField(String searchString){

    }

    public void closeSystem(){
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    public static void launch(String[] args) {
        launch();
    }
}