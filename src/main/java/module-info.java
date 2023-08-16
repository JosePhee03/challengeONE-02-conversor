module main.conversor {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.conversor to javafx.fxml;
    exports main.conversor;
}