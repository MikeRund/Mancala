package leaderboard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Only for testing
 */
public class AddPlayerGUI extends Application {

    private LeaderBoard leaderBoard;
    private PlayerRecord playerRecord;
    private PlayerRecordGUI playerRecordGUI;
    private LeaderBoardController leaderBoardController;

    public AddPlayerGUI(LeaderBoardController leaderBoardController, LeaderBoard leaderBoard, 
    User sampleUser, PlayerRecord playerRecord, PlayerRecordGUI playerRecordGUI) {
        this.leaderBoardController = leaderBoardController;
        this.leaderBoard = leaderBoard;
        this.playerRecord = playerRecord;
        this.playerRecordGUI = playerRecordGUI;
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
            leaderBoard.updateLeaderBoard(newPlayer, wins, losses);
            playerRecord.updatePlayerRecord(newPlayer, wins, losses);
            leaderBoardController.updateLeaderboardTable(); 
            playerRecordGUI.updatePlayerRecordData();       
        });
                
        root.getChildren().addAll(gridPane, addButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
