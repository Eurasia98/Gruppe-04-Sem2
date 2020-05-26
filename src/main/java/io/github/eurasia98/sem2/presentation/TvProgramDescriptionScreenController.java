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

public class TvProgramDescriptionScreenController implements Initializable {

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
    private Button btnChangeDescription;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private TextArea txtAreaDescrption;

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
        txtAreaDescrption.setEditable(true);
    }

    @FXML
    void btnSaveChanges() {
        if (App.getCreditSystem().changeTvProgramEpisodeDescription(App.getSelectedTvProgramEpisode(),txtAreaDescrption.getText()) == true){
            App.switchScene("ChoosenTvProgramToEdit");
        } else {
            try{
                txtAreaDescrption.appendText(App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisode()));
                txtAreaDescrption.setEditable(false);
            } catch (NullPointerException e){
                txtAreaDescrption.clear();
                txtAreaDescrption.setEditable(false);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisode()) != null){
            txtAreaDescrption.appendText(App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisode()));
            txtAreaDescrption.setEditable(false);
        } else txtAreaDescrption.setEditable(false);
        loggedIn();
    }
}
