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

public class EditMyTvProgramCreditsScreenController implements Initializable {
    @FXML
    private TableView<ModelTableEditMyTvProgramCredits> tvCredits;

    @FXML
    private TableColumn<ModelTableEditMyTvProgramCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableEditMyTvProgramCredits, String> tvcRoleType;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnAddCredits;

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
        ObservableList<ModelTableEditMyTvProgramCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getCreditsInfo(App.getSelectedProductionToEdit());
        for (String[] s : creditsInfo){
            observableList.add(new ModelTableEditMyTvProgramCredits(s[0], s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("role"));

        tvCredits.setItems(observableList);
    }
}
