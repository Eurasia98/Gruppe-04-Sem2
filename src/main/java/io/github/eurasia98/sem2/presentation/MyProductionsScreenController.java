package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyProductionsScreenController implements Initializable {

    @FXML
    private TextArea txtAreaDisplayMyProductions;

    @FXML
    private ImageView IVLogo;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> myProductionsList = App.getCreditSystem().showMyProductions(App.getCreditSystem().getAccount_id());
        for (String s : myProductionsList){
            txtAreaDisplayMyProductions.appendText(s + "\n");
        }
    }
}
