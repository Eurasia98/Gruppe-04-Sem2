package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    @FXML
    void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    void btnLoginActionHandler(ActionEvent event) {
        App.switchScene("LoginScreen");
    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App.loggedIn){
            btnLogin.setVisible(false);
        } else {
            btnLogin.setVisible(true);
        }
    }
}
