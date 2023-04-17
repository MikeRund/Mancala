module com.example.mancala {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mancala to javafx.fxml;
    exports com.example.mancala;
}