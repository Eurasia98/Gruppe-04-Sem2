package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FrontPageController {
    @FXML
    private Button buttonLogin;

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
            if (!txtFieldSearch.getText().isEmpty()) {
                App.switchToSearchScreen();
/*
                ListView listView = new ListView();
                VBox vbox = new VBox();
                listView.getItems().addAll(
                        App.creditSystem.userSearch(txtFieldSearch.getText()));
*/
            }
        }

    @FXML
    void buttonHandlerLogin(ActionEvent event) {

    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchToFrontPage();
    }

}
