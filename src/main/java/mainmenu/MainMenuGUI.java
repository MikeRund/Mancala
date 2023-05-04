package mainmenu;

import gameboard.BoardGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import leaderboard.LeaderBoardMain;
import logIn.LogInMain;

/**
 * The MainMenuGUI class displays the main menu.
 * It allows users to access the login, game, and leaderboard menu.
 */
public class MainMenuGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the window
        primaryStage.setTitle("Main Menu");

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        BoardGUI boardGUI = new BoardGUI();
        LogInMain logInMain = new LogInMain();
        LeaderBoardMain leaderBoardMain = new LeaderBoardMain();
        MainMenuController controller = new MainMenuController(boardGUI, leaderBoardMain, logInMain);
        loader.setController(controller);
        VBox root = loader.load();

        // Set background image
        String backgroundImageUrl = "file:src/main/resources/mainmenu/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");

        // Set scene
        Scene scene = new Scene(root, 500, 125);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}