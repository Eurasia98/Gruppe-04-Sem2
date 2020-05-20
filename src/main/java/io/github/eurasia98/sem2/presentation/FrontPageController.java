package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnAddMovie;

    @FXML
    private Button btnAddPerson;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private TextField txtFieldAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Button btnMyCredits;

    // Move to account control
    @FXML
    private void btnAddMovieHandler(ActionEvent event) {
        App.switchScene("CreateMovieScreen");
    }

    // Move to account control
    @FXML
    private void btnAddPersonHandler(ActionEvent event) {
        App.switchScene("CreatePersonScreen");
    }

    // Switches to login screen
    @FXML
    private void btnLoginHandler(ActionEvent event) {
        App.switchScene("LoginScreen");
    }

    // Returns to front page
    @FXML
    private void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    // Stores content of txtFieldSearch as a static variable in App
    @FXML
    private void ivSearchMouseClickHandler() {
        App.setSearchField(txtFieldSearch.getText());
        App.switchScene("SearchScreenUpdatedScreen");
    }

    // Enables search by pressing return key
    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    void btnMyCreditsHandler(ActionEvent event) {
        App.switchScene("MyCreditsScene");
    }

    // Checks App.getUserInfo to see if there is an account stored there. Hides/Shows Login button and "Min side" hyperlink
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!App.getUserInfo().isEmpty()){
            btnLogin.setVisible(false);
            vBoxAccount.setVisible(true);
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

        else {
            btnLogin.setVisible(true);
            vBoxAccount.setVisible(false);
            txtFieldAccount.clear();
            if (vBoxAccount.getChildren().size() == 2){
                vBoxAccount.getChildren().remove(1);
            }
        }

    }
}
