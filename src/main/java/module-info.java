module logIn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens logIn to javafx.fxml;
    exports logIn;
}