package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class LoginController {
    @FXML
    private TextField txtFieldUsername;

    @FXML
    private PasswordField pwField;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldError;

    @FXML
    private void btnLoginActionHandler () {
        List<String> verifyAccount = App.getCreditSystem().login(txtFieldUsername.getText(), pwField.getText());
        if (verifyAccount.isEmpty()){
            txtFieldError.setText("Forkert brugernavn / password");
        }
        else {
            App.setUserInfo(verifyAccount);
            App.switchScene("AccountScreen");
        }
    }

    // Returns to front page
    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    // Enables login by pressing return key
    @FXML
    private void keyPressEventHandler(KeyEvent event){
        if (event.getCode().toString().equals("ENTER")){
            btnLoginActionHandler();
        }
    }
}
