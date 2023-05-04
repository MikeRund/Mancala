package leaderboard;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

