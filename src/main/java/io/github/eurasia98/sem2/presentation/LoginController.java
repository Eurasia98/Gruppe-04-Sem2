package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.CreditSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lblError;

    // Verifies login in Login class. Stores username and account type in userInfo variable in App.
    @FXML
    private void btnLoginActionHandler () {
        if(!txtFieldUsername.getText().isEmpty() && !pwField.getText().isEmpty()){
            List<String> verifyAccount = App.getCreditSystem().login(txtFieldUsername.getText(), pwField.getText());
            if (verifyAccount.isEmpty()){
                lblError.setVisible(true);
            }
            else {
                App.setUserInfo(verifyAccount);
                App.switchScene("AccountScreen");
            }
        }

        if(txtFieldUsername.getText().isEmpty()){
            txtFieldUsername.setStyle("-fx-prompt-text-fill: red");
        }

        if(pwField.getText().isEmpty()){
            pwField.setStyle("-fx-prompt-text-fill: red");
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
