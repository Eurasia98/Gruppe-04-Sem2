package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTvSeriesCreditsController implements Initializable {

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnAvailableUsername;

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
    private TextField txtFieldNewRole;

    @FXML
    private TextField txtFieldNewRoleName;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCreateNewPerson;

    @FXML
    private Button btnKnownUser;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldRole;

    @FXML
    private TextField txtFieldRoleName;

    @FXML
    private Button btnSaveCredit;

    @FXML
    private TextField txtFieldUserId;

    @FXML
    private Button btnMyPage;

    @FXML
    void ivLogoHandler() {
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
        txtFieldNewUsername.setVisible(true);
        txtFieldPassword.setVisible(true);
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldEmail.setVisible(true);
        txtFieldNewRole.setVisible(true);
        txtFieldNewRoleName.setVisible(true);
        btnSavePerson.setVisible(true);
    }

    @FXML
    void btnKnownUserHandler() {
        resetFields();
        txtFieldUsername.setVisible(true);
        txtFieldUserId.setVisible(true);
        txtFieldRole.setVisible(true);
        txtFieldRoleName.setVisible(true);

        btnSaveCredit.setVisible(true);
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnSaveCreditHandler() {
        if (!txtFieldUsername.getText().isEmpty()){
            ArrayList<String> creditInfo = new ArrayList<>();
            creditInfo.add(txtFieldUsername.getText());
            creditInfo.add(App.getSelectedProductionToEdit());
            creditInfo.add(txtFieldRole.getText());
            creditInfo.add(txtFieldRoleName.getText());

            if (App.getCreditSystem().createNewTvSeriesCredit(creditInfo) == true){
                resetFields();
                App.switchScene("ChoosenTvShowToEdit");
            }
        }

    }

    @FXML
    void btnSavePersonHandler() {
        ArrayList<String> creditInfo = new ArrayList<>();
        creditInfo.add(txtFieldNewUsername.getText());
        creditInfo.add(txtFieldPassword.getText());
        creditInfo.add(txtFieldFirstName.getText());
        creditInfo.add(txtFieldLastName.getText());
        creditInfo.add(txtFieldEmail.getText());
        creditInfo.add(txtFieldNewRole.getText());
        creditInfo.add(txtFieldNewRoleName.getText());

    }

    public void resetFields(){
        txtFieldUsername.clear();
        txtFieldUsername.setVisible(false);
        txtFieldUserId.clear();
        txtFieldUserId.setVisible(false);
        txtFieldRole.clear();
        txtFieldRole.setVisible(false);
        txtFieldRoleName.clear();
        txtFieldRoleName.setVisible(false);
        btnSaveCredit.setVisible(false);

        btnAvailableUsername.setVisible(false);
        txtFieldNewUsername.clear();
        txtFieldNewUsername.setVisible(false);
        txtFieldPassword.clear();
        txtFieldPassword.setVisible(false);
        txtFieldFirstName.clear();
        txtFieldFirstName.setVisible(false);
        txtFieldLastName.clear();
        txtFieldLastName.setVisible(false);
        txtFieldEmail.clear();
        txtFieldEmail.setVisible(false);
        txtFieldNewRole.clear();
        txtFieldNewRole.setVisible(false);
        txtFieldNewRoleName.clear();
        txtFieldNewRoleName.setVisible(false);
        btnSavePerson.setVisible(false);

        txtArea.setVisible(false);
        txtArea.setEditable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
    }
}
