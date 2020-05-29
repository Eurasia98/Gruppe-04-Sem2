package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.presentation.App;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateProducerScreenController implements Initializable {

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private Button btnSaveButton;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextArea txtAreaResponse;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private PasswordField pwField;

    @FXML
    private TextField txtFieldProductionCompanyName;

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
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
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

    @FXML
    void btnAvailableUsernameHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText())){
            txtAreaResponse.clear();
            txtAreaResponse.setText("Username:"  + txtFieldUsername.getText() + " is available. ");
        } else {
            txtAreaResponse.clear();
            txtAreaResponse.setText("The username: " + txtFieldUsername + " is not available. ");
        }
    }

    // Spaces have to be filled out. No time to implement security.
    @FXML
    void btnSaveProducerHandler(ActionEvent event) {
        ArrayList<String> producerInfo = new ArrayList<>();
        producerInfo.add(txtFieldUsername.getText());
        producerInfo.add(pwField.getText());
        producerInfo.add(txtFieldEmail.getText());
        producerInfo.add("Producer");
        producerInfo.add(txtFieldFirstName.getText());
        producerInfo.add(txtFieldLastName.getText());
        producerInfo.add(txtFieldProductionCompanyName.getText());
        if (App.getCreditSystem().createNewProducer(producerInfo)){
            updateProducerSuccessful();
        } else updateProducerUnsuccessful();
    }

    //username, password, fName, lName, email, productionCompanyName
    public void updateProducerSuccessful(){
        txtFieldProductionCompanyName.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        pwField.clear();
        txtFieldUsername.clear();
        txtAreaResponse.setText("Producer has been succesfully created");
    }

    public void updateProducerUnsuccessful(){
        txtFieldProductionCompanyName.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        pwField.clear();
        txtFieldUsername.clear();
        txtAreaResponse.setText("Producer has not been created - something went wrong. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn();
    }
}
