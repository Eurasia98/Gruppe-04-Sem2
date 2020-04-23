package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FrontPageController {
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
        App.switchToSearchScreen();
    }

    @FXML
    void buttonHandlerLogin(ActionEvent event) {

    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchToFrontPage();
    }
    @FXML
    void productionScreenActionHandler(ActionEvent event){
        App.switchToProductionScreen();
    }
}
