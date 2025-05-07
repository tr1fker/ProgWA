module tr1fker.laboratorywork5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports tr1fker.laboratorywork5.part3.client;
    opens tr1fker.laboratorywork5.part3.client to javafx.fxml;
}