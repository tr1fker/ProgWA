module tr1fker.laboratorywork7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports tr1fker.laboratorywork7;
    opens tr1fker.laboratorywork7 to javafx.fxml;
}