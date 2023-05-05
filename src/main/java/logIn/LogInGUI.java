/**
 * This class represents the GUI for the login page of the application. It extends the JavaFX Application class
 * and contains the start method which sets up the GUI and launches it.
 */
package logIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainmenu.MainMenuGUI;

public class LogInGUI extends Application {

    /**
     * This method sets up the GUI for the login page and launches it.
     *
     * @param primaryStage the primary stage of the application
     * @throws Exception if there is an error loading the fxml file
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("log-in.fxml"));
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * This method launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {launch(args);}
}
