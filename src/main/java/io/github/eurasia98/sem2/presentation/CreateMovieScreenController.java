package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.MovieManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateMovieScreenController implements Initializable {

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
    private TextField txtFieldTitel;

    @FXML
    private TextField txtFieldProductionid;

    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtAreaMessage;

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    private void loggedIn(){
        lblAccount.setText(App.getUserInfo().get(0));
        Hyperlink myPage = new Hyperlink("Min side");
        myPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.switchScene("AccountScreen");
            }
        });
        vBoxAccount.getChildren().add(1, myPage);
    }

    @FXML
    private void btnSaveHandler(ActionEvent event) {
        if (App.getCreditSystem().createNewMovie(txtFieldTitel.getText(), txtFieldProductionid.getText()) == true){
            updateSucces();
        } else updateFail();

        /*MovieManager movieManager = new MovieManager();
        if (movieManager.insertMovie(movieManager.createMovie(txtFieldTitel.getText(), txtFieldProductionid.getText())) == false){
            updateSucces();
        } else {
            updateFail();
        }*/
    }

    private void updateSucces(){
        txtAreaMessage.clear();
        txtFieldProductionid.clear();
        txtFieldTitel.clear();
        txtAreaMessage.setText("Filmen er blevet gemt. ");
    }

    private void updateFail(){
        txtAreaMessage.clear();
        txtFieldTitel.clear();
        txtFieldProductionid.clear();
        txtAreaMessage.setText("Der skete en fejl. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn();
    }
}
