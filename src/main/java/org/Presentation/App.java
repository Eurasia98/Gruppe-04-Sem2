package org.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.Data.DatabaseController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    //this is a comment
    private static Scene scene;
    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("searchScreen"));
        window.setScene(scene);
        window.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void switchToDisplaySearchResultsUI(int productionId){
        DatabaseController dc = new DatabaseController();

        try {
            scene.setRoot(loadFXML("DisplaySearchResultsGUI"));
            window.setTitle("Select an option to diplay credits: ");


        } catch (IOException e) {
            System.out.println("Cant change root scene ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}