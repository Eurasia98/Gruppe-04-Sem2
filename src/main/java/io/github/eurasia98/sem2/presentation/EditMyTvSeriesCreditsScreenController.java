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
    void btnAddCreditsHandler() {
        App.switchScene("CreateTvSeriesCreditsScreen");
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
        ObservableList<ModelTableEditMyTvSeriesCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getCreditsInfo(App.getSelectedProductionToEdit());
        ArrayList<Integer> persons_ids = new ArrayList<>();

        for (String[] s : creditsInfo){
            observableList.add(new ModelTableEditMyTvSeriesCredits((s[0] + " " + s[1]), s[2] ,s[3]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("roleType"));

        tvCredits.setItems(observableList);
    }
}
