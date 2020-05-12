package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.presentation.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AccountScreenController implements Initializable {


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
    private Button btnAddMovie;

    @FXML
    private Button btnAddPerson;

    @FXML
    private TextField txtFieldAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Button btnLogOut;

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
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    @FXML
    public void btnCreateProducerActionHandler(ActionEvent actionEvent) {
        App.switchScene("CreateProducerScreen");
    }

    @FXML
    void btnCreateProductionActionHandler(ActionEvent event) {
        App.switchScene("CreateProductionScreen");
    }

    @FXML
    void btnEditProductionActionHandler(ActionEvent event) {

    }

    // Technically logs out.
    @FXML
    private void btnLogOutActionHandler(ActionEvent event){
        App.setUserInfo(Collections.emptyList());
        App.getCreditSystem().login("", "");
        App.switchScene("FrontPage");
    }

    @FXML
    private void btnAddMovieHandler(ActionEvent event) {
        App.switchScene("CreateMovieScreen");
    }

    @FXML
    private void btnAddPersonHandler(ActionEvent event) {
        App.switchScene("CreatePersonScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldAccount.setText(App.getUserInfo().get(0));
        Hyperlink myPage = new Hyperlink("Min side");
        myPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.switchScene("AccountScreen");
            }
        });
        vBoxAccount.getChildren().add(1, myPage);
    }
}
