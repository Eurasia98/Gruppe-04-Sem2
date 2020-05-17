package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AccountScreenController implements Initializable {


    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Button btnCreateProducer;

    @FXML
    private Button btnCreateProduction;

    @FXML
    private Button btnEditProduction;


    @FXML
    private Button btnAddPerson;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnMyCredits;

    @FXML
    private Button btnMyProductions;

    @FXML
    private Button btnMyPersons;

    @FXML
    private Button btnRegDanmark;

    @FXML
    private Button btnSysAdmin;

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    @FXML
    private void btnCreateProducerActionHandler(ActionEvent actionEvent) {
        App.switchScene("CreateProducerScreen");
    }

    @FXML
    private void btnCreateProductionActionHandler(ActionEvent event) {
        App.switchScene("CreateProductionScreen");
    }

    @FXML
    private void btnEditProductionActionHandler(ActionEvent event) {

    }

    // Technically logs out.
    @FXML
    private void btnLogOutActionHandler(ActionEvent event){
        App.setUserInfo(Collections.emptyList());
        // App.getCreditSystem().login("", "");
        App.switchScene("FrontPage");
    }

    @FXML
    private void btnAddPersonActionHandler(ActionEvent event) {
        App.switchScene("CreatePersonScreen");
    }

    @FXML
    private void btnMyCreditsActionHandler(ActionEvent event){
//        App.switchScene("MyCreditsScreen");
    }

    @FXML
    private void btnMyPersonsHandler(ActionEvent event) {
        App.switchScene("MyPersonsScreen");
    }

    @FXML
    private void btnMyProductionsHandler(ActionEvent event) {
        App.switchScene("MyProductionsScreen");
    }

    @FXML
    private void btnRegDanmarkActionHandler(){
        App.switchScene("CreateRegDanmarkScreen");
    }

    @FXML
    private void btnSysAdminActionHandler(){
        App.switchScene("CreateSysAdminScreen");
    }

    private void loggedIn(){
        lblAccount.setText(App.getUserInfo().get(0));
        Hyperlink myPage = new Hyperlink("Min side");
        myPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.switchScene("AccountScreen");
            }
        });
        vBoxAccount.getChildren().add(1, myPage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedIn();

        switch (App.getUserInfo().get(1)){
            case "SystemAdministrator": {
                btnCreateProducer.setManaged(true);
                btnCreateProducer.setVisible(true);
                btnRegDanmark.setManaged(true);
                btnRegDanmark.setVisible(true);
                btnSysAdmin.setManaged(true);
                btnSysAdmin.setVisible(true);
            }
            case "Producer": {
                btnEditProduction.setManaged(true);
                btnEditProduction.setVisible(true);
                btnCreateProduction.setManaged(true);
                btnCreateProduction.setVisible(true);
                btnAddPerson.setManaged(true);
                btnAddPerson.setVisible(true);
                btnMyPersons.setManaged(true);
                btnMyPersons.setVisible(true);
                btnMyProductions.setManaged(true);
                btnMyProductions.setVisible(true);
            }
            case "Person": {
                btnMyCredits.setManaged(true);
                btnMyCredits.setVisible(true);
            }
            default: {
                break;
            }

        }
    }
}
