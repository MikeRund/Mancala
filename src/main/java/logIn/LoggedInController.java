package logIn;

import gameboard.BoardGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mainmenu.UserData;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button button_continue;
    @FXML
    private Label label_welcome;
    @FXML
    private Label label_msg;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage) button_continue.getScene().getWindow()).close();

                //DBUtils.changeScene(event, "log-in.fxml", "Log in", null);
                //DBUtils.changeScene(event, "../gameboard/BoardFX.fxml", "Log in", null);
            }
        });

    }
    public void setUserInformation(String username) {
        label_welcome.setText("Welcome " + username + "!");

        UserData.getInstance().setUsername(username);
        UserData.getInstance().setLoggedIn(true);
        username = UserData.getInstance().getUsername();
        boolean loggedIn = UserData.getInstance().getLoggedIn();
        UserData.getInstance().setLoggedInProperty(true);
        System.out.println("Got the username: " + username + " " + loggedIn);
    }

}

