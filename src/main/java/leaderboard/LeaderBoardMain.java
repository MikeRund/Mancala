package leaderboard;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LeaderBoardMain extends Application {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        LeaderBoardGUI.launchLeaderboard(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
