/**
 * A class representing the graphical user interface for the Mancala game board.
 */
package gameboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leaderboard.Player;

import java.util.Objects;

public class BoardGUI extends Application {

    private String username;
    private Player player1;

    /**
     * Sets the username of the player using the interface.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the player using the interface.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }

//    public Player getPlayer1() {
//        return player1;
//    }
//
//    public void setPlayer1(Player player1) {
//        this.player1 = player1;
//    }

    /**
     * Launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and displays the user interface.
     *
     * @param primaryStage the primary stage for this application
     * @throws Exception if an error occurs while loading the user interface
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameboard/BoardFX.fxml")));
        primaryStage.setTitle("Mancala");
        primaryStage.setScene(new Scene(root, 603.2, 400));
        primaryStage.show();
    }
}
