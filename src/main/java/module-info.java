module leaderboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    
    opens leaderboard to javafx.fxml;
    exports leaderboard;
    
    exports logIn to javafx.graphics, javafx.fxml;
    opens logIn to javafx.fxml;

    exports gameboard to javafx.graphics;
    opens gameboard to javafx.graphics, javafx.fxml;

    exports mainmenu to javafx.graphics;
    opens mainmenu to javafx.graphics, javafx.fxml;
}
    

