package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditMyTvSeriesCreditsScreenController implements Initializable {

    @FXML
    private TableView<ModelTableEditMyTvSeriesCredits> tvCredits;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcRoleType;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcRoleName;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnAddCredits;

    @FXML
    private Button btnDeleteCredit;

    @FXML
    void btnAddCreditsHandler() {
        App.switchScene("CreateTvSeriesCreditsScreen");
    }

    @FXML
    void btnMyPageHandler() {
        App.resetSelects();
        App.switchScene("AccountScreen");
    }

    @FXML
    void ivLogoHandler() {
        App.resetSelects();
        App.switchScene("FrontPage");
    }

    @FXML
    void btnDeleteCreditHandler() {
        try{
            String role = tvCredits.getSelectionModel().getSelectedItem().getRoleType();
            String roleName = tvCredits.getSelectionModel().getSelectedItem().getRoleName();
            if (App.getCreditSystem().deleteCreditFromTvSeries(role, roleName,App.getSelectedProductionToEdit()) == true){
                update();
            } else {
                update();
            }
        } catch (java.lang.NullPointerException e){
            update();
        }
    }

    private void update() {
        ObservableList<ModelTableEditMyTvSeriesCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getCreditsInfo(App.getSelectedProductionToEdit());

        for (String[] s : creditsInfo){
            observableList.add(new ModelTableEditMyTvSeriesCredits(s[0], s[1] ,s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        tvCredits.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }
}
