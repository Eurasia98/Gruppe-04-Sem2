package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateProducerScreenController implements Initializable {

    @FXML
    private TextField fNameField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField lNameField;

    @FXML
    void saveButtonHandler(ActionEvent event) {
        if (!fNameField.getText().isEmpty() && !lNameField.getText().isEmpty()){
            App.creditSystem.getProducerManager().createProducer(fNameField.getText(), lNameField.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
