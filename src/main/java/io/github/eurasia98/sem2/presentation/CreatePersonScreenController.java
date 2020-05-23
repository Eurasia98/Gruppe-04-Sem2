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
import java.util.ArrayList;
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
    private void IVLogoHandler(MouseEvent event) {
        App.switchScene("FrontPage");
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
                        resetFields();
                        txtArea.appendText("Personen blev oprettet. ");
                    } else resetFields();
                } else {
                    resetFields();
                    txtArea.appendText("Personen blev ikke oprettet. Der skete desværre en fejl. ");
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
                    resetFields();
                    txtArea.appendText("Personen blev oprettet. \n Se personen under 'mine personer'. ");
                } else {
                    resetFields();
                    txtArea.appendText("Personen blev ikke oprettet. \n Der skete desværre en fejl. ");
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
                    resetFields();
                    txtArea.appendText("Personen blev oprettet");
                } else {
                    resetFields();
                    txtArea.appendText("Personen blev ikke oprettet. \n Der skete desværre en fejl. ");
                }
            } else {
                resetFields();
                txtArea.appendText("Der skete desværre en fejl. ");
            }
        } else {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldUsername.getText() + " er ikke ledigt. ");
        }
    }

    private void resetFields(){
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        txtFieldProductionId.clear();
        txtFieldRole.clear();
        txtFieldRoleName.clear();
        txtArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
