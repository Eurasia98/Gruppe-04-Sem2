package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {
    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonProduction;

    @FXML
    private Button buttonSearch;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    void ivSearchActionHandler(MouseEvent event) {
        //App.switchToSearchScreen();
    }

    @FXML
    void buttonHandlerLogin(ActionEvent event) {

    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }
    @FXML
    void productionScreenActionHandler(ActionEvent event){
        App.switchScene("CreateMovieScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
