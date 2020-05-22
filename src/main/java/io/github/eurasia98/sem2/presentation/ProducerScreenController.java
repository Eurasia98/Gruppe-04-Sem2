package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProducerScreenController {

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
