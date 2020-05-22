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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatePersonScreenController implements Initializable {

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
    private void btnAvailableUsernameHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText())){
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldUsername.getText() + " er ledigt. ");
        } else {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldUsername.getText() + " er ikke ledigt. ");
        }
    }

    // laver en ny person når man trykker "gem". På denne tager den username, password, first name, last name
    // og laver en person med dette, uanset om der er indtsatet yderligere værdier. Resetter skærmen
    // og skriver en besked til brugeren om succes eller ej.
    @FXML
    private void btnSavePersonHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText())) {
            if (!txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty() &&
                    !txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() &&
                    !txtFieldEmail.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty() &&
                    !txtFieldRole.getText().isEmpty() && !txtFieldRoleName.getText().isEmpty()) {
                ArrayList<String> personInfo = new ArrayList<>();
                personInfo.add(txtFieldUsername.getText());
                personInfo.add(txtFieldPassword.getText());
                personInfo.add(txtFieldEmail.getText());
                personInfo.add("Person");
                personInfo.add(txtFieldFirstName.getText());
                personInfo.add(txtFieldLastName.getText());
                if (App.getCreditSystem().createNewPerson(personInfo) == true) {
                    ArrayList<String> creditsInfo = new ArrayList<>();
                    creditsInfo.add(App.getCreditSystem().getAnAccountId(txtFieldUsername.getText()));
                    creditsInfo.add(txtFieldProductionId.getText());
                    creditsInfo.add(txtFieldRole.getText());
                    creditsInfo.add(txtFieldRoleName.getText());
                    if (App.getCreditSystem().createNewCredit(creditsInfo) == true) {
                        updateSuccesfullCreation();
                    } else updateUnsuccesfullCreation();
                } else {
                    updateUnsuccesfullCreation();
                }
            } else if (!txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty() &&
                    !txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() &&
                    !txtFieldEmail.getText().isEmpty() && txtFieldProductionId.getText().isEmpty() &&
                    txtFieldRole.getText().isEmpty() && txtFieldRoleName.getText().isEmpty()) {
                ArrayList<String> personInfo = new ArrayList<>();
                personInfo.add(txtFieldUsername.getText());
                personInfo.add(txtFieldPassword.getText());
                personInfo.add(txtFieldEmail.getText());
                personInfo.add("Person");
                personInfo.add(txtFieldFirstName.getText());
                personInfo.add(txtFieldLastName.getText());
                if (App.getCreditSystem().createNewPerson(personInfo)) {
                    updateSuccesfullCreation();
                } else {
                    updateUnsuccesfullCreation();
                }
            } else if (!txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty() &&
                    !txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() &&
                    txtFieldEmail.getText().isEmpty() && txtFieldProductionId.getText().isEmpty() &&
                    txtFieldRole.getText().isEmpty() && txtFieldRoleName.getText().isEmpty()) {
                ArrayList<String> personInfo = new ArrayList<>();
                personInfo.add(txtFieldUsername.getText());
                personInfo.add(txtFieldPassword.getText());
                personInfo.add(null);
                personInfo.add("Person");
                personInfo.add(txtFieldFirstName.getText());
                personInfo.add(txtFieldLastName.getText());
                if (App.getCreditSystem().createNewPerson(personInfo) == true) {
                    updateSuccesfullCreation();
                } else {
                    updateUnsuccesfullCreation();
                }
            } else {
                updateUnsuccesfullCreation();
            }
        } else {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldUsername.getText() + " er ikke ledigt. ");
        }
    }

    private void updateSuccesfullCreation(){
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldProductionId.clear();
        txtFieldRole.clear();
        txtFieldRoleName.clear();
        txtArea.setText("Personen er blevet oprettet i systemet. ");
    }

    private void updateUnsuccesfullCreation(){
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldProductionId.clear();
        txtFieldRole.clear();
        txtFieldRoleName.clear();
        txtArea.setText("Personen er ikke blevet oprettet i systemet - Udfyld venligst alle relevante felter. \n " +
                "Alle felter omkring en kreditering skal fyldes hvis en kreditering skal oprettes. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
