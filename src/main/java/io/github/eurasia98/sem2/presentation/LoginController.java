package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    private TextField textFieldLoginUsername;

    @FXML
    private TextField textFieldLoginPassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    void buttonHandlerLogin(ActionEvent event) {
        // Needs to validate login
        App.switchScene("FrontPage");
    }


    public void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }
}
