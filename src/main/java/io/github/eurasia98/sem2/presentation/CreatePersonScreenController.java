package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatePersonScreenController implements Initializable {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnAvailableUsername;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private Button btnSavePerson;

    @FXML
    private TextField txtFieldProductionId;

    @FXML
    private TextField txtFieldRole;

    @FXML
    private TextField txtFieldRoleName;

    @FXML
    private Button btnAddEmail;

    @FXML
    private TextArea txtArea;

    @FXML
    void IVLogoHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnAvailableUsernameHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText())){
            txtArea.clear();
            txtArea.setText("Username:"  + txtFieldUsername.getText() + " is available. ");
        } else {
            txtArea.clear();
            txtArea.setText("The username: " + txtFieldUsername + " is not available. ");
        }
    }

    // laver en ny person når man trykker "gem". På denne tager den username, password, first name, last name
    // og laver en person med dette, uanset om der er indtsatet yderligere værdier. Resetter skærmen
    // og skriver en besked til brugeren om succes eller ej.
    @FXML
    void btnSavePersonHandler(ActionEvent event) {
            if (App.getCreditSystem().createNewPerson(txtFieldUsername.getText(),
                txtFieldPassword.getText(), txtFieldFirstName.getText(), txtFieldLastName.getText()) == false){
                updateSuccesfullCreation();
            } else updateUnsuccesfullCreation();
    }

    public void updateSuccesfullCreation(){
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldProductionId.clear();
        txtFieldRole.clear();
        txtFieldRoleName.clear();
        txtArea.setText("Person has been created");
    }

    public void updateUnsuccesfullCreation(){
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldProductionId.clear();
        txtFieldRole.clear();
        txtFieldRoleName.clear();
        txtArea.setText("Person has not been created - something went wrong. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
