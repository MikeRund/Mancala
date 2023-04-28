module leaderboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens leaderboard to javafx.fxml;
    exports leaderboard;
}
