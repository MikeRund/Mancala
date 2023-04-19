package gameboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class BoardGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameboard/BoardFX.fxml")));
        primaryStage.setTitle("Mancala");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}
