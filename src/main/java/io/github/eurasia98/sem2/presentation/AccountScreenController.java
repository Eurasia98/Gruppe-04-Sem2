package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.presentation.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
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
    private Button btnAddPerson;

    @FXML
    private TextField txtFieldAccount;

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
        /*if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");*/

        try {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("SearchScreenUpdatedScreen");
        } catch (java.lang.NullPointerException e){
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    @FXML
    private void btnCreateProducerActionHandler(ActionEvent actionEvent) {
        App.switchScene("CreateProducerScreen");
    }

    @FXML
    private void btnCreateProductionActionHandler(ActionEvent event) {
        App.switchScene("CreateProductionScreen");
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
        App.switchScene("PersonMyCreditsScreen");
    }

    @FXML
    void btnMyPersonsHandler(ActionEvent event) {
        App.switchScene("MyPersonsScreen");
    }

    @FXML
    void btnMyProductionsHandler(ActionEvent event) {
        App.switchScene("MyProductionsScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldAccount.setText(App.getUserInfo().get(0));
        Hyperlink myPage = new Hyperlink("Min side");
        myPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.switchScene("AccountScreen");
            }
        });
        vBoxAccount.getChildren().add(1, myPage);

        switch (App.getUserInfo().get(1)){
            case "System Administrator": {
                btnCreateProducer.setManaged(true);
                btnCreateProducer.setVisible(true);
            }
            case "Producer": {
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
