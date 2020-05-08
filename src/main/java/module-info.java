module io.github.eurasia98.sem2.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens io.github.eurasia98.sem2.presentation to javafx.fxml;
    exports io.github.eurasia98.sem2.presentation;
}