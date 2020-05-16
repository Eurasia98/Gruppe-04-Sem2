package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonMyCreditsScreenController implements Initializable {

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
    private Button btnMyPage;

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        App.switchScene("AccountScreen");
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
    }
}
