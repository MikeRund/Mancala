package mainmenu;

import gameboard.BoardGUI;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import leaderboard.LeaderBoardMain;
import logIn.LogInGUI;


/**
 * The MainMenuController class manages the functions to setup the main menu.
 */
public class MainMenuController {

    @FXML
    private Button loginButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button leaderBoardButton;

    private LogInGUI logInGUI;
    private BoardGUI boardGUI;
    private LeaderBoardMain leaderBoardMain;

    public MainMenuController(BoardGUI boardGUI, LeaderBoardMain leaderBoardMain,
                              LogInGUI logInGUI) {
        this.boardGUI = boardGUI;
        this.logInGUI = logInGUI;
        this.leaderBoardMain = leaderBoardMain;
    }

    public void handleLoginButton() throws Exception {
        Stage stage = new Stage();
        logInGUI.start(stage);

        // Wait for UserData instance to be updated
        UserData.getInstance().loggedInProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                String username = UserData.getInstance().getUsername();
                System.out.println("Got the username at menu: " + username);
                boardGUI.setUsername(username);
                leaderBoardMain.setUsername(username);

            }
        });
    }

    public void handleGameButton() throws Exception {
        Stage stage = new Stage();
        System.out.println("Current user at board: " + boardGUI.getUsername());
        boardGUI.start(stage);
    }

    public void handleLeaderBoardMainButton() throws Exception {
        Stage stage = new Stage();
        leaderBoardMain.start(stage);
    }

    public void updateLogInStatus(MainMenuGUI menu) {
        boolean loggedIn = UserData.getInstance().getLoggedIn();
        String username = UserData.getInstance().getUsername();
        if(loggedIn) {
            menu.setMainUserLoggedIn(loggedIn);
            menu.setMainUser(username);
        }
    }
}