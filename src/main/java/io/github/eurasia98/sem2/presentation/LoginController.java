package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    void btnLoginActionHandler () {
        /*String verifyAccount = App.creditSystem.login(txtFieldUsername.getText(), pwField.getText());
        if (verifyAccount.equals("System error. Try again.") || verifyAccount.equals("Wrong username / password.")){
            txtFieldError.setText(verifyAccount);
        }
        else {
            App.setLoggedIn(true);
            App.switchScene("AccountScreen");
        }*/
    }


    public void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    public void keyPressEventHandler(KeyEvent event){
        if (event.getCode().toString().equals("ENTER")){
            btnLoginActionHandler();
        }
    }
}
