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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTvSeriesScreenController implements Initializable {

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
    private TextField txtFieldProductionId;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private TextField txtFieldSeriesId;

    @FXML
    private Button btnSaveSeries;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextArea txtAreaDisplayInfo;

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
    private void btnSaveSeriesHandler() {
        ArrayList<String> seriesInfo = new ArrayList<>();
        seriesInfo.add(txtFieldProductionId.getText());
        seriesInfo.add(txtFieldTitle.getText());
        seriesInfo.add(txtFieldSeriesId.getText());
        seriesInfo.add(txtAreaDescription.getText());
        if (App.getCreditSystem().createNewTv_series(seriesInfo) == true){
            resetFields();
            updateSucces();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn();
    }

    private void resetFields(){
        txtFieldTitle.clear();
        txtFieldProductionId.clear();
        txtFieldSeriesId.clear();
        txtAreaDescription.clear();
        txtAreaDisplayInfo.clear();
        txtAreaDisplayInfo.setEditable(false);
    }

    private void updateSucces(){
        txtAreaDisplayInfo.appendText("Serien er blevet oprettet. Find den under mine produktioner. " +
                "\n Her kan du ligeledes tilføje sæsoner og episoder til serien. ");
    }
}
