package leaderboard;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LeaderBoardMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        LeaderBoardGUI.launchLeaderboard(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
