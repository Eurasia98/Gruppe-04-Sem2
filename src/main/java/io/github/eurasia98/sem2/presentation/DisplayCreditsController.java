package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
    private Button btnLogin;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private TextField txtFieldAccount;

    @FXML
    private Button btnExportData;

    @FXML
    private TextField txtFieldExport;

    // Returns to front page
    @FXML
    private void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    // Stores content of txtFieldSearch as a static variable in App
    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        }
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    // Enables search by pressing return key
    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void btnLoginActionHandler(ActionEvent event) {
        App.switchScene("LoginScreen");
    }

    @FXML
    private void btnExportDataActionHandler(ActionEvent event){
        txtFieldExport.setVisible(true);
        if (App.getCreditSystem().exportData()){
            txtFieldExport.setText("Krediteringer gemt");
            txtFieldExport.setStyle("-fx-prompt-text-fill: green");
        }
        else {
            txtFieldExport.setText("Fejl med export af krediteringer");
            txtFieldExport.setStyle("-fx-prompt-text-fill: red");
        }
    }

    private void displayCredits(){
        txtAreaCreditsDisplay.appendText(App.getCreditSystem().getCreditsToDisplay());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayCredits();

        if (!App.getUserInfo().isEmpty()){
            btnLogin.setVisible(false);
            vBoxAccount.setVisible(true);
            txtFieldAccount.setText(App.getUserInfo().get(0));
            Hyperlink myPage = new Hyperlink("Min side");
            myPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    App.switchScene("AccountScreen");
                }
            });
            vBoxAccount.getChildren().add(1, myPage);

            // Only shows for person user. FIX BEFORE FINAL EDITION
            if (App.getUserInfo().get(1).equals("Person")){
                btnExportData.setVisible(true);
            }
        }

        else {
            btnLogin.setVisible(true);
            vBoxAccount.setVisible(false);
            txtFieldAccount.clear();
            if (vBoxAccount.getChildren().size() == 2){
                vBoxAccount.getChildren().remove(1);
            }
        }
    public void displayCredits(){
        ArrayList<String> creditsToDisplay = App.getCreditSystem().getCreditsToDisplay();
        for (String credit : creditsToDisplay){
            txtAreaCreditsDisplay.appendText(credit + "\n");
        }
    }

}
