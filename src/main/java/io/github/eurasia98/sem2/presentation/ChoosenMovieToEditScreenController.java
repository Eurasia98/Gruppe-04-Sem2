package io.github.eurasia98.sem2.presentation;

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

public class ChoosenMovieToEditScreenController implements Initializable {

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
    private TextArea txtAreaDescription;

    @FXML
    private Button btnChangeDescription;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private TextArea txtAreaInfo;

    @FXML
    private Label lblTitle;

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
    void btnChangeDescriptionHandler() {
        btnSaveChanges.setVisible(true);
        txtAreaDescription.setEditable(true);
        txtAreaInfo.appendText("Du kan nu foretage ændringer i beskrivelsen. Max 1500 tegn. ");
    }

    @FXML
    void btnSaveChangesHandler() {
        if (App.getCreditSystem().changeDescriptionMovie(App.getSelectedProductionToEdit(), txtAreaDescription.getText()) == true){
            resetFields();
        } else {
            resetFields();
            txtAreaInfo.appendText("Der skete en fejl. ");
        }
    }

    private void resetFields() {
        txtAreaDescription.setEditable(false);
        btnSaveChanges.setVisible(false);
    }

    public void update(){
        try{
            txtAreaDescription.clear();
            txtAreaDescription.appendText(App.getCreditSystem().getDescription(App.getSelectedProductionToEdit()));
            txtAreaDescription.setEditable(false);

            txtAreaInfo.clear();
            txtAreaInfo.setEditable(false);

            lblTitle.setText(App.getCreditSystem().getMovieTitle(App.getSelectedProductionToEdit()));
        } catch (java.lang.NullPointerException e){
            resetFields();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
        loggedIn();
    }
}
