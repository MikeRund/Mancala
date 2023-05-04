package mainmenu;

import gameboard.BoardGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import leaderboard.LeaderBoardMain;
import leaderboard.User;
import logIn.LogInGUI;

/**
 * The MainMenuGUI class displays the main menu.
 * It allows users to access the login, game, and leaderboard menu.
 */
public class MainMenuGUI extends Application {
    private String mainUsername;
    private String sndUsername;
    private boolean mainUserLoggedIn = false;
    private boolean sndUserLoggedIn = false;

    public String getMainUser() {
        return mainUsername;
    }

    public void setMainUser(String mainUsername) {
        this.mainUsername = mainUsername;
    }

//    public String getOpponent() {
//        return sndUser;
//    }
//
//    public void setOpponent(User sndUser) {
//        this.sndUser = sndUser;
//    }

    public boolean isMainUserLoggedIn() {
        return mainUserLoggedIn;
    }

    public void setMainUserLoggedIn(boolean mainUserLoggedIn) {
        this.mainUserLoggedIn = mainUserLoggedIn;
    }

    public boolean isSndUserLoggedIn() {
        return sndUserLoggedIn;
    }

    public void setSndUserLoggedIn(boolean sndUserLoggedIn) {
        this.sndUserLoggedIn = sndUserLoggedIn;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the window
        primaryStage.setTitle("Main Menu");
        MainMenuGUI menu = new MainMenuGUI();

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