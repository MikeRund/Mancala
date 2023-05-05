/**
 * The LogInController class is the controller for the login screen of the application.
 * It handles the user's login credentials and provides functionality for the login and signup buttons.
 * It implements the Initializable interface to initialize the FXML elements of the scene.
 */
package logIn;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    @FXML
    private Button button_login;

    @FXML
    private Button button_signup;

    @FXML
    private TextField textfield_username;

    @FXML
    private TextField textfield_password;

    /**
     * Initializes the FXML elements of the login screen.
     * Sets up event handlers for the login and signup buttons.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, textfield_username.getText(), textfield_password.getText());
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sign-up.fxml", "Sign Up", null);
            }
        });
    }
}
