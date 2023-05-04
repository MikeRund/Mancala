package leaderboard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
* The PlayerRecordGUI class displays the player record. 
* It has functionalities to update the player record data,
* and mark and unmark favourite players.
*/
public class PlayerRecordGUI {
    
    private PlayerRecordController playerRecordController;

    public PlayerRecordGUI(PlayerRecordController playerRecordController) {
        this.playerRecordController = playerRecordController;
    }

    public void start(Stage stage) throws IOException {

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerRecord.fxml"));
        loader.setController(playerRecordController);
        VBox root = loader.load();

        // Set background image
        String backgroundImageUrl = "file:src/main/resources/leaderboard/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");

        playerRecordController.setUpPlayerRecordTable();
        playerRecordController.setUpUsernameColumn();
        playerRecordController.setUpWinsColumn();
        playerRecordController.setUpLossesColumn();
        playerRecordController.setUpFavouriteColumn();

        // Create a scene 
        Scene scene = new Scene(root, 300, 140);
        stage.setScene(scene);
        stage.setTitle("Player Record");
    } 
}
