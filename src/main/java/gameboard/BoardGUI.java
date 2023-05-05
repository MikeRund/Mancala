package gameboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leaderboard.Player;

import java.util.Objects;

public class BoardGUI extends Application {

    private String username;
    private Player player1;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameboard/BoardFX.fxml")));
        primaryStage.setTitle("Mancala");
        primaryStage.setScene(new Scene(root, 603.2, 400));
        primaryStage.show();
    }
}
