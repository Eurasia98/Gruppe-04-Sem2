package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyPersonsScreenController implements Initializable {

    @FXML
    private TableView<ModelTableMyPersons> TVPersons;

    @FXML
    private TableColumn<ModelTableMyPersons, String> colFirstname;

    @FXML
    private TableColumn<ModelTableMyPersons, String> colLastname;

    @FXML
    private TableColumn<ModelTableMyPersons, String> colAccount_id;

    @FXML
    private TableColumn<ModelTableMyPersons, String> colAmountOfCredits;

    @FXML
    private ImageView IVLogo;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ModelTableMyPersons> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myPersons = App.getCreditSystem().showMyPersons();
        for (String[] s : myPersons){
            observableList.add(new ModelTableMyPersons(s[0], s[1], s[2], s[3]));
        }

        colAccount_id.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAmountOfCredits.setCellValueFactory(new PropertyValueFactory<>("amountOfCredits"));

        TVPersons.setItems(observableList);
    }
}
