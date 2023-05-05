/**
 * This class is the controller for the LoggedIn view, which is displayed after a user successfully logs in.
 * It handles the events for the button that allows the user to continue to the game board.
 */
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

    /**
     * Initializes the controller class.
     * Sets the action event for the continue button.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage) button_continue.getScene().getWindow()).close();
            }
        });
    }

    /**
     * Sets the user information and updates the UserData instance.
     *
     * @param username The username of the user.
     */
    public void setUserInformation(String username) {
        label_welcome.setText("Welcome " + username + "!");

        UserData.getInstance().setUsername(username);
        UserData.getInstance().setLoggedIn(true);
        DBUtils.updateUserData(username);
        username = UserData.getInstance().getUsername();
        boolean loggedIn = UserData.getInstance().getLoggedIn();
        UserData.getInstance().setLoggedInProperty(true);
        System.out.println("Got the username: " + username + " " + loggedIn);
    }

}

