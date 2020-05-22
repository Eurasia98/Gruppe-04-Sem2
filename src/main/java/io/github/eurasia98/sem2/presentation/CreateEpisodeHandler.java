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

public class CreateEpisodeHandler implements Initializable {

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
    private TextField txtFieldEpisodeTitle;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtFieldEpisodeNumber;

    @FXML
    private TextField txtFieldEpisodeId;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnSave;

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
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveHandler() {
        if (App.getCreditSystem().createNewEpisode(App.getSelectedSeasonToEdit(), App.getSelectedTvSeriesToEdit(),
                App.getSelectedProductionToEdit(),txtFieldEpisodeTitle.getText(), txtAreaDescription.getText(),
                txtFieldEpisodeId.getText(), txtFieldEpisodeNumber.getText()) == true){
            updateSucces();
        } else resetFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        loggedIn();
    }

    public void updateSucces(){
        App.switchScene("ChoosenSeasonToEdit");
    }

    public void resetFields(){
        txtAreaDescription.clear();
        txtFieldEpisodeId.clear();
        txtFieldEpisodeId.clear();
        txtFieldEpisodeNumber.clear();
    }

}
