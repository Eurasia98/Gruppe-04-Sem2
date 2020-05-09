package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DisplayCreditsController implements Initializable {

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
            App.setSearchString(txtFieldSearch.getText());
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
        displayCredits();
    }

    public void displayCredits(){
        ArrayList<Credit> creditArrayList = App.getCreditSystem().getCreditsToDisplay();
        for (Credit credit : creditArrayList){
            txtAreaCreditsDisplay.appendText(credit.toString() + "\n");
        }
    }
}