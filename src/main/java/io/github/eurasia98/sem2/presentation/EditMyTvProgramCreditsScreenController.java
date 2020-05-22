package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditMyTvProgramCreditsScreenController implements Initializable {

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
    private TableView<ModelTableEditMyTvProgramCredits> tvCredits;

    @FXML
    private TableColumn<ModelTableEditMyTvProgramCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableEditMyTvProgramCredits, String> tvcRoleType;


    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnAddCredits;

    @FXML
    private Button btnDeleteCredit;

    @FXML
    void btnDeleteCreditHandler() {
        String role = tvCredits.getSelectionModel().getSelectedItem().getRole();
        if (App.getCreditSystem().deleteCreditFromTvProgram(role, App.getSelectedProductionToEdit()) == true){
            update();
        } else {
            update();
        }
    }

    @FXML
    void btnAddCreditsHandler() {
        App.switchScene("CreateTvProgramCreditsScreen");
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }

    private void update() {
        ObservableList<ModelTableEditMyTvProgramCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getCreditsInfo(App.getSelectedProductionToEdit());
        for (String[] s : creditsInfo){
            observableList.add(new ModelTableEditMyTvProgramCredits(s[0], s[1]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("role"));

        tvCredits.setItems(observableList);
    }
}
