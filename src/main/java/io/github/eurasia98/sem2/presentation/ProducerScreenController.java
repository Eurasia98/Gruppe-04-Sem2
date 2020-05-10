package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProducerScreenController {

    @FXML
    private Button btnCreateProduction;

    @FXML
    private Button btnMyProductions;

    @FXML
    private Button btnCreatePerson;

    @FXML
    void btnCreatePersonHandler(ActionEvent event) {
        App.switchScene("CreatePersonScreen");
    }

    @FXML
    void btnCreateProductionHandler(ActionEvent event) {
        App.switchScene("CreateProductionScreen");
    }

    @FXML
    void btnMyProductionsHandler(ActionEvent event) {

    }
}
