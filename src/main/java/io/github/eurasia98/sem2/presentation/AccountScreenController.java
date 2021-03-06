package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Separator sepAdminControls;

    @FXML
    private Label lblAdminControls;

    @FXML
    private Separator sepNormal;

    @FXML
    private Label lblAccountUsername;

    @FXML
    private Button btnChangeEmail;

    @FXML
    private Label lblAccountEmail;

    @FXML
    private TextField txtFieldNewEmail;

    @FXML
    private Button btnSaveEmail;

    @FXML
    private Button btnChangePassword;

    @FXML
    private PasswordField pwOldPassword;

    @FXML
    private PasswordField pwNewPassword1;

    @FXML
    private PasswordField pwNewPassword2;

    @FXML
    private Button btnSavePassword;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblAccountFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblAccountLastName;

    @FXML
    private Label lblCompany;

    @FXML
    private Label lblAccountCompany;

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
            App.switchScene("searchScreenUpdatedScreen");
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
    

    // Technically logs out.
    @FXML
    private void btnLogOutActionHandler(ActionEvent event){
        App.setUserInfo(Collections.emptyList());
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

    // Enables text field and button to change email.
    @FXML
    private void btnChangeEmailActionHandler(ActionEvent event) {
        txtFieldNewEmail.setDisable(false);
        txtFieldNewEmail.setVisible(true);
        btnSaveEmail.setDisable(false);
        btnSaveEmail.setVisible(true);
    }

    // Enables password fields and button to change password.
    @FXML
    private void btnChangePasswordActionHandler(ActionEvent event) {
        pwOldPassword.setDisable(false);
        pwOldPassword.setVisible(true);
        pwNewPassword1.setDisable(false);
        pwNewPassword1.setVisible(true);
        pwNewPassword2.setDisable(false);
        pwNewPassword2.setVisible(true);
        btnSavePassword.setDisable(false);
        btnSavePassword.setVisible(true);
    }

    /*  Checks if the user has entered their old email in the new email field. 
        Sends content to CreditSystem::editAccountEmail() and updates email label.
        Gives the user a notification if successful. Red prompt text if not.    */
    @FXML
    private void btnSaveEmailActionHandler(ActionEvent event) {
        if (!txtFieldNewEmail.getText().equals(lblAccountEmail.getText())){
            if (App.getCreditSystem().editAccountEmail(txtFieldNewEmail.getText())){
                lblAccountEmail.setText(txtFieldNewEmail.getText());
                txtFieldNewEmail.clear();
                txtFieldNewEmail.setPromptText("Email ændret.");
            }
        }
        else {
            txtFieldNewEmail.clear();
            txtFieldNewEmail.setPromptText("Forrige og ny email er ens.");
            txtFieldNewEmail.setStyle("-fx-prompt-text-fill: red");
        }
    }

    /*  Checks if the user entered the same password in both new password fields.
        Sends new and old password to CreditSystem::editAccountPassword 
        and gives the user a notification if successful. Red prompt text if not.   */
    @FXML
    private void btnSavePasswordActionHandler(ActionEvent event) {
        if (pwNewPassword1.getText().equals(pwNewPassword2.getText())){
            if (App.getCreditSystem().editAccountPassword(pwOldPassword.getText(), pwNewPassword1.getText())){
                pwOldPassword.clear();
                pwNewPassword1.clear();
                pwNewPassword2.clear();
                pwOldPassword.setPromptText("");
                pwNewPassword1.setPromptText("");
                pwNewPassword2.setPromptText("Kodeord ændret.");
            }
        }
        else{
            pwNewPassword1.setStyle("-fx-prompt-text-fill: red");
            pwNewPassword2.setStyle("-fx-prompt-text-fill: red");
        }
    }

    // Gets username from App::userInfo and creates "Min side" hyperlink
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

    /*  Shows buttons corresponding to the user's account type rights.
        Switch case doesn't break in the individual cases,
        as the SystemAdministrator has all rights,
        and the Producer also has the right of the Person.  */
    private void sideBarVisibility(){

        switch (App.getUserInfo().get(1)) {
            case "SystemAdministrator": {
                sepAdminControls.setManaged(true);
                sepAdminControls.setVisible(true);
                lblAdminControls.setManaged(true);
                lblAdminControls.setVisible(true);
                btnCreateProducer.setManaged(true);
                btnCreateProducer.setVisible(true);
                btnRegDanmark.setManaged(true);
                btnRegDanmark.setVisible(true);
                btnSysAdmin.setManaged(true);
                btnSysAdmin.setVisible(true);
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

    /*  Fills account information with getters depending on
        if the user is a Producer or a Person. SystemAdministrator
        and RegistreringDanmark have no additional information. */
    private void accountInfoFill(){
        lblAccountUsername.setText(App.getCreditSystem().getAccount().getUsername());
        lblAccountEmail.setText(App.getCreditSystem().getAccount().getEmail());
        if(App.getCreditSystem().getAccount().getAccountType().equals("Producer")){
            lblAccountFirstName.setText(App.getCreditSystem().getProducerAccount().getFName());
            lblAccountLastName.setText(App.getCreditSystem().getProducerAccount().getLName());
            lblAccountCompany.setText(App.getCreditSystem().getProducerAccount().getProductionCompanyName());
        }
        else if (App.getCreditSystem().getAccount().getAccountType().equals("Person")){
            lblAccountFirstName.setText(App.getCreditSystem().getPersonAccount().getFirstName());
            lblAccountLastName.setText(App.getCreditSystem().getPersonAccount().getLastName());
            lblCompany.setVisible(false);
            lblCompany.setDisable(true);
        }
    }

    // Calls the 3 different methods to fill out the scene depending on account type.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedIn();
        sideBarVisibility();
        accountInfoFill();
    }
}
