module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.Logic to javafx.fxml;
    exports org.Logic;
}