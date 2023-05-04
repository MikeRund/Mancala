package mainmenu;

import gameboard.BoardGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import leaderboard.LeaderBoardMain;
import logIn.LogInMain;


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

    private LogInMain logInMain;
    private BoardGUI boardGUI;
    private LeaderBoardMain leaderBoardMain;

    public MainMenuController(BoardGUI boardGUI, LeaderBoardMain leaderBoardMain, 
    LogInMain logInMain) {
        this.boardGUI = boardGUI;
        this.logInMain = logInMain;
        this.leaderBoardMain = leaderBoardMain;
    }

    public void handleLoginButton() throws Exception {
        Stage stage = new Stage();
        logInMain.start(stage);
    }

    public void handleGameButton() throws Exception {
        Stage stage = new Stage();
        boardGUI.start(stage);
    }

    public void handleLeaderBoardMainButton() throws Exception {
        Stage stage = new Stage();
        leaderBoardMain.start(stage);
    }
}
