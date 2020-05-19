package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.postgresql.jdbc2.ArrayAssistant;

import java.util.ArrayList;

public class CreateTvProgramCreditsController {
    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnAvailableUsername;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldNewUsername;

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
    private TextField txtFieldRole;

    @FXML
    private TextField txtFieldNewRole;

    @FXML
    private TextField txtFieldRoleName;

    @FXML
    private TextField txtFieldNewRoleName;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCreateNewPerson;

    @FXML
    private Button btnKnownUser;

    @FXML
    private Button btnSaveCredit;

    @FXML
    private TextField txtFieldUserId;

    @FXML
    private Button btnMyPage;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnAvailableUsernameHandler() {
        if (App.getCreditSystem().availableUsername(txtFieldNewUsername.getText())) {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ledigt. ");
        } else {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ikke ledigt. ");
            txtFieldNewUsername.clear();
        }
    }

    @FXML
    void btnCreateNewPersonHandler() {
        resetFields();
        btnAvailableUsername.setVisible(true);
        btnSavePerson.setVisible(true);

        txtFieldNewUsername.setVisible(true);
        txtFieldPassword.setVisible(true);
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldEmail.setVisible(true);
        txtFieldNewRole.setVisible(true);
        txtFieldNewRoleName.setVisible(true);
    }

    @FXML
    void btnKnownUserHandler() {
        resetFields();
        txtFieldUsername.setVisible(true);
        txtFieldRole.setVisible(true);
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldEmail.setVisible(true);
        txtFieldRole.setVisible(true);
        txtFieldRoleName.setVisible(true);
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveCreditHandler() {

        if (!txtFieldUsername.getText().isEmpty()){
            ArrayList<String> creditsInfo = new ArrayList<>();
            creditsInfo.add(txtFieldUsername.getText());
            creditsInfo.add(txtFieldRole.getText());
            creditsInfo.add(txtFieldRoleName.getText());
            creditsInfo.add(App.getSelectedProductionToEdit());
            if (App.getCreditSystem().createNewTvProgramCredit(creditsInfo) == true){
                resetFields();
                txtArea.appendText("Krediteringen blev gemt. ");
            } else {
                resetFields();
                txtArea.appendText("Krediteringen blev ikke gemt. Noget gik galt. ");
            }
        } else if (!txtFieldUserId.getText().isEmpty()){
            ArrayList<String> creditsInfo = new ArrayList<>();
            creditsInfo.add(txtFieldUserId.getText());
            creditsInfo.add(txtFieldRole.getText());
            creditsInfo.add(txtFieldRoleName.getText());
            creditsInfo.add(App.getSelectedProductionToEdit());
            if (App.getCreditSystem().createNewTvProgramCreditWithUserId(creditsInfo) == true){
                resetFields();
                txtArea.appendText("Krediteringen blev gemt. ");
            } else {
                resetFields();
                txtArea.appendText("Krediteringen blev ikke gemt. Noget gik galt. ");
            }
        } else if (!txtFieldNewUsername.getText().isEmpty()){
            ArrayList<String> creditsInfo = new ArrayList<>();
            creditsInfo.add(txtFieldNewUsername.getText());
            creditsInfo.add(txtFieldPassword.getText());
            creditsInfo.add(txtFieldFirstName.getText());
            creditsInfo.add(txtFieldLastName.getText());
            if (!txtFieldEmail.getText().isEmpty()){
                creditsInfo.add(txtFieldEmail.getText());
            } else creditsInfo.add(null);
            creditsInfo.add(txtFieldNewRole.getText());
            if (!txtFieldNewRoleName.getText().isEmpty()){
                creditsInfo.add(txtFieldNewRoleName.getText());
            } else creditsInfo.add(null);
            creditsInfo.add(App.getSelectedProductionToEdit());
            if (App.getCreditSystem().createNewTvProgramCreditAndPerson(creditsInfo) == true){
                App.switchScene("EditMyTvProgramCreditsScreen");
            } else txtArea.appendText("Der skete desværre en fejl. ");
        } else txtArea.appendText("Der skete desværre en fejl.");
    }

    @FXML
    void btnSavePersonHandler() {

    }

    private void resetFields() {
        btnAvailableUsername.setVisible(false);
        btnSavePerson.setVisible(false);
        btnSaveCredit.setVisible(false);

        txtFieldRoleName.clear();
        txtFieldRoleName.setVisible(false);
        txtFieldRole.clear();
        txtFieldRole.setVisible(false);
        txtFieldEmail.clear();
        txtFieldEmail.setVisible(false);
        txtFieldLastName.clear();
        txtFieldLastName.setVisible(false);
        txtFieldFirstName.clear();
        txtFieldFirstName.setVisible(false);
        txtFieldPassword.clear();
        txtFieldPassword.setVisible(false);
        txtFieldNewUsername.clear();
        txtFieldNewUsername.setVisible(false);
        txtFieldUsername.clear();
        txtFieldUsername.setVisible(false);
        txtFieldUserId.clear();txtFieldUserId.setVisible(false);
        txtArea.clear();
    }
}
