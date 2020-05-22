package io.github.eurasia98.sem2.presentation;

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

public class CreateProducerScreenController implements Initializable {

    @FXML
    private TextField productionCompanyNameField;

    @FXML
    private TextField fNameField;

    @FXML
    private Button btnSaveProducer;

    @FXML
    private TextField lNameField;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextArea txtAreaResponse;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    void IVLogoHandler(MouseEvent event) {
        App.switchScene("FrontPage");
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
    @FXML
    void btnSaveProducerHandler(ActionEvent event) {
        if (App.getCreditSystem().createNewProducer(txtFieldUsername.getText(),txtFieldPassword.getText(),
                fNameField.getText(), lNameField.getText(), emailField.getText(), productionCompanyNameField.getText()) == false){
            updateProducerSuccesful();
        } else updateProducerUnsuccesful();
    }
    //username, password, fName, lName, email, productionCompanyName
    public void updateProducerSuccesful(){
        productionCompanyNameField.clear();
        fNameField.clear();
        lNameField.clear();
        emailField.clear();
        txtAreaResponse.setText("Producer has been succesfully created");
    }

    public void updateProducerUnsuccesful(){
        productionCompanyNameField.clear();
        fNameField.clear();
        lNameField.clear();
        emailField.clear();
        txtAreaResponse.setText("Producer has not been created - something went wrong. ");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
