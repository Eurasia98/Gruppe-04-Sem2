package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchShowCreditsController implements Initializable {
    @FXML
    private ImageView ivLogo;

    @FXML
    private TableView<ModelTableSearchCredits> tvMyCredits;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcRoleName;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcRoleType;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void ivSearchHandler() {
        try {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("SearchScreenUpdatedScreen");
        } catch (java.lang.NullPointerException e) {
            txtFieldSearch.clear();
        }
    }

    @FXML
    void searchKeyHandler(KeyEvent event) {
        try {
            if (event.getSource() == "ENTER") {
                App.setSearchField(txtFieldSearch.getText());
                App.switchScene("SearchScreenUpdatedScreen");
            } else txtFieldSearch.clear();
        } catch (java.lang.NullPointerException e) {
            txtFieldSearch.clear();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
        txtFieldSearch.clear();
    }

    public void update() {
        ObservableList<ModelTableSearchCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getSearchCredits(App.getSelectedSearchResult());
        for (String[] s : creditsInfo) {
            observableList.add(new ModelTableSearchCredits(s[0], s[1], s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("role_type"));


        tvMyCredits.setItems(observableList);
    }
}
