package leaderboard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddPlayerGUI extends Application {

    private LeaderboardGUI leaderboardGUI;

    public AddPlayerGUI(LeaderboardGUI leaderboardGUI) {
        this.leaderboardGUI = leaderboardGUI;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Player");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label winsLabel = new Label("Wins:");
        TextField winsField = new TextField();
        Label lossesLabel = new Label("Losses:");
        TextField lossesField = new TextField();

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(winsLabel, 0, 1);
        gridPane.add(winsField, 1, 1);
        gridPane.add(lossesLabel, 0, 2);
        gridPane.add(lossesField, 1, 2);

        Button addButton = new Button("Add Player");
        addButton.setOnAction(event -> {
            String username = usernameField.getText();
            int wins = Integer.parseInt(winsField.getText());
            int losses = Integer.parseInt(lossesField.getText());
            Player newPlayer = new Player(username);
            newPlayer.setWins(wins);
            newPlayer.setLosses(losses);
            leaderboardGUI.updateLeaderboardData(newPlayer, wins, losses);
        });

        Label testingLabel = new Label("Only for testing");
        testingLabel.setTextFill(Color.RED);

        root.getChildren().addAll(testingLabel, gridPane, addButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
