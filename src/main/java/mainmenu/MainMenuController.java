package mainmenu;

import gameboard.BoardGUI;
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

    /**
     * The constructor for the MainMenuController class. Initializes the necessary GUIs.
     * @param boardGUI the game board GUI
     * @param leaderBoardMain the leaderboard GUI
     * @param logInGUI the login GUI
     */
    public MainMenuController(BoardGUI boardGUI, LeaderBoardMain leaderBoardMain,
                              LogInGUI logInGUI) {
        this.boardGUI = boardGUI;
        this.logInGUI = logInGUI;
        this.leaderBoardMain = leaderBoardMain;
    }

    /**
     * Handles the user clicking on the login button, opening the login GUI and updating the UserData instance with the
     * logged in user's information.
     * @throws Exception
     */
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

    /**
     * Handles the user clicking on the game button, opening the game board GUI.
     * @throws Exception
     */
    public void handleGameButton() throws Exception {
        Stage stage = new Stage();
        System.out.println("Current user at board: " + boardGUI.getUsername());
        boardGUI.start(stage);
    }

    /**
     * Handles the user clicking on the leaderboard button, opening the leaderboard GUI.
     * @throws Exception
     */
    public void handleLeaderBoardMainButton() throws Exception {
        Stage stage = new Stage();
        leaderBoardMain.start(stage);
    }

    /**
     * Updates the login status of the main menu GUI based on the status of the UserData instance.
     * @param menu the main menu GUI
     */
    public void updateLogInStatus(Main menu) {
        boolean loggedIn = UserData.getInstance().getLoggedIn();
        String username = UserData.getInstance().getUsername();
        if(loggedIn) {
            menu.setMainUserLoggedIn(loggedIn);
            menu.setMainUser(username);
        }
    }
}