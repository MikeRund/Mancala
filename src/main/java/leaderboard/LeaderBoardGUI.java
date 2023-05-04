package leaderboard;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * The LeaderboardGUI class displays the leaderboard. 
 * It allows users to view the leaderboard, sort it by wins or win percentage,
 * and see their favourite players. 
 */
public class LeaderBoardGUI extends Application {

    @FXML
    public TableView<Player> leaderboardTable;

    @FXML
    private TableColumn<Player, Integer> rankColumn;

    @FXML
    private TableColumn<Player, String> usernameColumn;

    @FXML
    private TableColumn<Player, Integer> winsColumn;

    @FXML
    private TableColumn<Player, Double> winPercentageColumn;

    @FXML
    private TableColumn<Player, Boolean> favouriteColumn;

    private LeaderBoardController leaderBoardController;

    public LeaderBoardGUI(LeaderBoardController leaderBoardController) {
        this.leaderBoardController = leaderBoardController;
    }

    public static void launchLeaderboard(Stage primaryStage) {
        UltilityFunction ultilityFunction = new UltilityFunction();

        try {// Create a leaderboard
            LeaderBoard leaderBoard = new LeaderBoard(ultilityFunction);
            leaderBoard.addALLPlayersLeaderBoard();

            // Create a player record
            PlayerRecord playerRecord = new PlayerRecord(ultilityFunction);
            playerRecord.addALlPlayerRecords();

            // Create a sample user to view the leaderboard
            User user = new User(playerRecord.getPlayerRecord().get(0));

            // Create the player record controller and GUI
            PlayerRecordController playerRecordController = new PlayerRecordController(playerRecord, user, ultilityFunction);
            PlayerRecordGUI playerRecordGUI = new PlayerRecordGUI(playerRecordController);

            // Create the leaderboard controller and GUI
            LeaderBoardController leaderBoardController = new LeaderBoardController(leaderBoard, user, playerRecord, playerRecordGUI, ultilityFunction, playerRecordController);
            LeaderBoardGUI leaderBoardGUI = new LeaderBoardGUI(leaderBoardController);

            // Start the leaderboard GUI
            leaderBoardGUI.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the window
        primaryStage.setTitle("Leaderboard");

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));
        loader.setController(leaderBoardController);
        VBox root = loader.load();

        // Set background image
        String backgroundImageUrl = "file:src/main/resources/leaderboard/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");

        leaderBoardController.setUpTable();
        leaderBoardController.setUpRankColumn();
        leaderBoardController.setUpUsernameColumn();
        leaderBoardController.setUpWinsColumn();
        leaderBoardController.setUpWinPercentColumn();
        leaderBoardController.setUpFavouriteColumn();
 
        // Set scene
        Scene scene = new Scene(root, 512, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        leaderBoardController.startAutoUpdate();
    }
}

