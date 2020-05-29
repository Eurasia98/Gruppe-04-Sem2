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

public class EditChoosenEpisodeController implements Initializable {

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
    private TextArea txtAreaInfo;

    @FXML
    private Button btnSaveChanges;

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
    private void btnChangeDescriptionHandler() {
        btnSaveChanges.setVisible(true);
        txtAreaDescription.setEditable(true);
        txtAreaInfo.appendText("Du kan nu foretage ændringer i beskrivelsen. Max 1500 tegn. ");
    }

    @FXML
    private void btnSaveChangesHandler() {
        if (App.getCreditSystem().changeDescriptionSeriesEpisode(App.getSelectedSeriesEpisode(), txtAreaDescription.getText()) == true){
            resetFields();
            update();
        } else {
            resetFields();
            update();
            txtAreaInfo.appendText("Der skete en fejl. ");
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
        loggedIn();
    }

    private void resetFields(){
        txtAreaDescription.clear();
        txtAreaDescription.setEditable(false);
        btnSaveChanges.setVisible(false);
    }

    private void update(){
        try {
            if (!App.getCreditSystem().getEpisodeDescription(App.getSelectedSeriesEpisode()).isEmpty()){
                txtAreaDescription.appendText(App.getCreditSystem().getEpisodeDescription(App.getSelectedSeriesEpisode()));
                btnSaveChanges.setVisible(false);
                txtAreaDescription.setEditable(false);

            } else {
                txtAreaDescription.setEditable(false);
            }
        } catch (java.lang.NullPointerException e){
            txtAreaDescription.setEditable(false);
        }
    }


}
