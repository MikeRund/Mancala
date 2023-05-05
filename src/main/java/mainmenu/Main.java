package mainmenu;

import gameboard.BoardGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import leaderboard.LeaderBoardMain;
import logIn.LogInGUI;

/**
 * The MainMenuGUI class displays the main menu.
 * It allows users to access the login, game, and leaderboard menu.
 */
public class Main extends Application {
    private String mainUsername;
//    private String sndUsername;
    private boolean mainUserLoggedIn = false;
//    private boolean sndUserLoggedIn = false;

    /**
     * Gets the username of the main user.
     * @return The username of the main user.
     */
    public String getMainUser() {
        return mainUsername;
    }

    /**
     * Sets the username of the main user.
     * @param mainUsername The username of the main user.
     */
    public void setMainUser(String mainUsername) {
        this.mainUsername = mainUsername;
    }


    /**
     * Checks if the main user is logged in.
     * @return True if the main user is logged in, false otherwise.
     */
    public boolean isMainUserLoggedIn() {
        return mainUserLoggedIn;
    }

    /**
     * Sets the login status of the main user.
     * @param mainUserLoggedIn The login status of the main user.
     */
    public void setMainUserLoggedIn(boolean mainUserLoggedIn) {
        this.mainUserLoggedIn = mainUserLoggedIn;
    }

//    public boolean isSndUserLoggedIn() {
//        return sndUserLoggedIn;
//    }
//
//    public void setSndUserLoggedIn(boolean sndUserLoggedIn) {
//        this.sndUserLoggedIn = sndUserLoggedIn;
//    }

    /**
     * The start method sets up the main menu window and loads the FXML file.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if an error occurs while setting up the main menu window.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the window
        primaryStage.setTitle("Main Menu");
        Main menu = new Main();

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        BoardGUI boardGUI = new BoardGUI();
        LogInGUI logInGUI = new LogInGUI();
        LeaderBoardMain leaderBoardMain = new LeaderBoardMain();
        MainMenuController controller = new MainMenuController(boardGUI, leaderBoardMain, logInGUI);
        loader.setController(controller);
        VBox root = loader.load();

        // Set background image
        String backgroundImageUrl = "file:src/main/resources/mainmenu/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");

        // Set scene
        Scene scene = new Scene(root, 1536, 780);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}