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
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchScreenUpdatedController implements Initializable {

    @FXML
    private ImageView ivLogo;

    @FXML
    private TableView<ModelTableSearch> tvEpisodes;

    @FXML
    private TableColumn<ModelTableSearch, String> tvcEpisodeTitle;

    @FXML
    private TableColumn<ModelTableSearch, String> tvcProductionId;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private Button btnSelect;

    @FXML
    private ImageView ivSearchImage;

    @FXML
    void btnSelectHandler() {
        App.setSelectedSearchResult(tvEpisodes.getSelectionModel().getSelectedItem().getProduction_id());
        App.switchScene("SearchShowCreditsScreen");
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void ivSearchImageHandler() {
        try {
            App.setSearchField(txtFieldSearch.getText());
            txtFieldSearch.clear();
            update();
        } catch (java.lang.NullPointerException e){
            txtFieldSearch.clear();
        }
    }

    @FXML
    void txtFieldSearchKeyHandler(KeyEvent event) {
        if (event.getSource() == "ENTER"){
            App.setSearchField(txtFieldSearch.getText());
            txtFieldSearch.clear();
            update();
        }
    }

    public void update(){
        ObservableList<ModelTableSearch> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> searchInfo = App.getCreditSystem().searchUpdated(App.getSearchField());
        for (String[] s : searchInfo){
            observableList.add(new ModelTableSearch(s[0], s[1]));
        }
        tvcEpisodeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcProductionId.setCellValueFactory(new PropertyValueFactory<>("production_id"));

        tvEpisodes.setItems(observableList);;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFieldSearch.clear();
        update();
    }
}
