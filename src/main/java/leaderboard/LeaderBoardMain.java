/**
 * The LeaderBoardMain class is the main class of the leaderboard program.
 * It launches the leaderboard GUI and sets the username.
 */
package leaderboard;

import javafx.application.Application;
import javafx.stage.Stage;


public class LeaderBoardMain extends Application {

    private String username;

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Starts the leaderboard GUI.
     * @param primaryStage the primary stage of the GUI
     * @throws Exception if an error occurs during the GUI start up
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        LeaderBoardGUI.launchLeaderboard(primaryStage);

    }

    /**
     * The main method that launches the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
