package io.github.eurasia98.sem2.presentation.accountPresentation;

import io.github.eurasia98.sem2.logic.accountLogic.AccountManager;
import io.github.eurasia98.sem2.logic.accountLogic.PersonManager;
import io.github.eurasia98.sem2.presentation.App;
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

public class createPersonScreenController implements Initializable {

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
    private Button btnAddCredit;

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
        AccountManager accountManager = new AccountManager();
        if (accountManager.checkUsernameAvailability(txtFieldUsername.getText()) == true){
            txtArea.clear();
            txtArea.setText("Username: " + txtFieldUsername.getText() + " is available. ");
        } else {
            txtArea.clear();
            txtArea.setText("Username: " + txtFieldUsername + " is not available. ");
        }
    }

    @FXML
    void btnAddCreditHandler(ActionEvent event) {
        txtFieldProductionId.setVisible(true);
        txtFieldRole.setVisible(true);
        txtFieldRoleName.setVisible(true);
    }

    @FXML
    void btnSavePersonHandler(ActionEvent event) {
        if (txtFieldEmail.getText() == null || txtFieldEmail.getText().trim().isEmpty()){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
