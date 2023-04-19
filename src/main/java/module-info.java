module gameboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens gameboard to javafx.fxml;
    exports gameboard;
}