package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CreditsController implements Initializable {

    @FXML
    private TextArea txtAreaCreditsDisplay;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivLogo;

    @FXML
    private ImageView ivSearch;

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    @FXML
    void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.searchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        }
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    @FXML
    void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAreaCreditsDisplay.appendText(App.creditSystem.searchCredits(App.selectedTitle));
    }
}