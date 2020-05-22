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

public class MyPersonsScreenController implements Initializable {

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
    private TableView<ModelTableMyPersons> tvPersons;

    @FXML
    private TableColumn<ModelTableMyPersons, String> tvcFirstname;

    @FXML
    private TableColumn<ModelTableMyPersons, String> tvcLastname;

    @FXML
    private TableColumn<ModelTableMyPersons, String> tvcAccount_id;

    @FXML
    private TableColumn<ModelTableMyPersons, String> tvcAmountOfCredits;

    @FXML
    private TableColumn<ModelTableMyPersons, String> tvcEmail;

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

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
    void btnEditEmailHandler() {

    }

    @FXML
    void btnSaveChangesHandler() {

    }

    @FXML
    private Button btnEditEmail;

    @FXML
    private TextField txtFieldCurrentEmail;

    @FXML
    private TextField txtFieldNewEmail;

    @FXML
    private Button btnSaveChanges;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();

        ObservableList<ModelTableMyPersons> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myPersons = App.getCreditSystem().getMyPersons();
        for (String[] s : myPersons){
            observableList.add(new ModelTableMyPersons(s[0], s[1], s[2], s[3], s[4]));
            System.out.println(s[2]);
        }

        tvcAccount_id.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        tvcFirstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        tvcLastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        tvcAmountOfCredits.setCellValueFactory(new PropertyValueFactory<>("amountOfCredits"));
        tvcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tvPersons.setItems(observableList);
    }

    public void resetFields(){
        txtFieldCurrentEmail.clear();
        txtFieldCurrentEmail.setVisible(false);
        txtFieldNewEmail.clear();
        txtFieldNewEmail.setVisible(false);
        btnSaveChanges.setVisible(false);
    }
}
