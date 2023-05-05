/**
 * Controller class for sign up screen.
 */
package logIn;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;

    @FXML
    private Button button_login;

    @FXML
    private TextField textfield_username;

    @FXML
    private TextField textfield_password;

    /**
     * Initializes the sign up screen.
     *
     * @param url URL of the FXML file for the screen.
     * @param resourceBundle Resources used by the screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textfield_username.getText().trim().isEmpty()
                        && !textfield_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, textfield_username.getText(), textfield_password.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up.");
                    alert.show();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log in!", null);
            }
        });

    }
}
