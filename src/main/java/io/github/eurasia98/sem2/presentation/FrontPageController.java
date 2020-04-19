package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FrontPageController {
    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonSearch;

    @FXML
    void buttonActionSearch(ActionEvent event) {
        App.switchToSearchScreen();
    }

    @FXML
    void buttonHandlerLogin(ActionEvent event) {

    }
}
