package io.github.eurasia98.sem2.presentation.accountPresentation;

import io.github.eurasia98.sem2.presentation.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AccountController {


    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Button btnCreateProducer;

    @FXML
    private Button btnCreateProduction;

    @FXML
    private Button btnEditProduction;

    @FXML
    public void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    public void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    public void ivSearchMouseClickHandler() {
        /*if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");*/
    }

    @FXML
    public void btnCreateProducerActionHandler(ActionEvent actionEvent) {
        App.switchScene("CreateProducerScreen");
    }

    @FXML
    void btnCreateProductionActionHandler(ActionEvent event) {

    }

    @FXML
    void btnEditProductionActionHandler(ActionEvent event) {

    }
}
