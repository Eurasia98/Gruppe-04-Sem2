package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonMyCreditsScreenController implements Initializable {

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
    private TableView<ModelTablePersonMyCredits> tvMyCredits;

    @FXML
    private TableColumn<ModelTablePersonMyCredits, String> tvcTitle;

    @FXML
    private TableColumn<ModelTablePersonMyCredits, String> tvcProductionType;

    @FXML
    private TableColumn<ModelTablePersonMyCredits, String> tvcRoleType;

    @FXML
    private TableColumn<ModelTablePersonMyCredits, String> tvcRoleName;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ModelTablePersonMyCredits> creditsInfoList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfoArray = App.getCreditSystem().getLoggedInPersonCredits();

        for (String[] s : creditsInfoArray){
            creditsInfoList.add(new ModelTablePersonMyCredits(s[0], s[1], s[2], s[3]));
        }

        tvcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcProductionType.setCellValueFactory(new PropertyValueFactory<>("production_type"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("role_type"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("role_name"));

        tvMyCredits.setItems(creditsInfoList);

        loggedIn();
    }
}
