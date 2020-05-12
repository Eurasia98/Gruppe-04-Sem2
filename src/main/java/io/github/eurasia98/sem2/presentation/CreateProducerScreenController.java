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
    private Button saveButton;

    @FXML
    private TextField lNameField;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView ivLogo;

    @FXML
    void IVLogoHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {
        createProducer();
    }

    public void createProducer(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createProducer();
    }
}
