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
    private Button buttonSearch;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private void ivSearchActionHandler(MouseEvent event) {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.searchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        }
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    @FXML
    void buttonHandlerLogin(ActionEvent event) {
        App.switchScene("LoginScreen");
    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

}
