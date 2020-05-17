package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyPersonsScreenController implements Initializable {

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
    private ImageView ivLogo;

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
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
