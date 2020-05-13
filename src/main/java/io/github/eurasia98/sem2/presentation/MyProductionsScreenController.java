package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyProductionsScreenController implements Initializable {


    @FXML
    private ImageView IVLogo;

    @FXML
    private TableView<ModelTableMyProductions> TVMyProductions;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCTitle;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCProductionId;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCProductionType;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ModelTableMyProductions> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myproductions = App.getCreditSystem().showMyProductions(App.getCreditSystem().getAccount_id());
        for (String[] s : myproductions){
            observableList.add(new ModelTableMyProductions(s[0], s[2], s[3]));
        }

        TVCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TVCProductionId.setCellValueFactory(new PropertyValueFactory<>("production_id"));
        TVCProductionType.setCellValueFactory(new PropertyValueFactory<>("production_type"));

        TVMyProductions.setItems(observableList);
    }
}
