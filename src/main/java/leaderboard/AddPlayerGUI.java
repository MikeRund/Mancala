package leaderboard;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPlayerGUI extends Application {

    private LeaderboardGUI leaderboardGUI;
    private LeaderBoard leaderBoard;
    private User sampleUser;
    private PlayerRecord playerRecord;
    private PlayerRecordGUI playerRecordGUI;

    public AddPlayerGUI(LeaderboardGUI leaderboardGUI, LeaderBoard leaderBoard, 
    User sampleUser, PlayerRecord playerRecord, PlayerRecordGUI playerRecordGUI) {
        this.leaderboardGUI = leaderboardGUI;
        this.leaderBoard = leaderBoard;
        this.sampleUser = sampleUser;
        this.playerRecord = playerRecord;
        this.playerRecordGUI = playerRecordGUI;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Player");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label winsLabel = new Label("Wins:");
        TextField winsField = new TextField();
        Label lossesLabel = new Label("Losses:");
        TextField lossesField = new TextField();

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(winsLabel, 0, 1);
        gridPane.add(winsField, 1, 1);
        gridPane.add(lossesLabel, 0, 2);
        gridPane.add(lossesField, 1, 2);

        ComboBox<String> playerComboBox = new ComboBox<>();
        updatePlayerComboBox(playerComboBox);

        Button addButton = new Button("Add Player");
        addButton.setOnAction(event -> {
            String username = usernameField.getText();
            int wins = Integer.parseInt(winsField.getText());
            int losses = Integer.parseInt(lossesField.getText());
            Player newPlayer = new Player(username);
            leaderBoard.updateLeaderBoard(newPlayer, wins, losses);
            playerRecord.updatePlayerRecord(newPlayer, wins, losses);
            leaderboardGUI.updateLeaderboardTable(); 
            playerRecordGUI.updatePlayerRecordData(newPlayer, wins, losses);       
            updatePlayerComboBox(playerComboBox); 
        });

        Button markFavouriteButton = new Button("Mark as Favourite");
        markFavouriteButton.setOnAction(event -> {
            String selectedUsername = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedUsername != null) {
                User favouriteUser = new User(selectedUsername);
                leaderBoard.markFavouriteUser(sampleUser, favouriteUser);
                leaderboardGUI.updateLeaderboardTable(); 
            }
        });

        Button unmarkFavouriteButton = new Button("Unmark");
        unmarkFavouriteButton.setOnAction(event -> {
            String selectedUsername = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedUsername != null) {
                User favouriteUser = new User(selectedUsername);
                leaderBoard.unmarkFavouriteUser(sampleUser, favouriteUser);
                leaderboardGUI.updateLeaderboardTable();
            }
        });
        
        root.getChildren().addAll(gridPane, addButton, playerComboBox, markFavouriteButton, unmarkFavouriteButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updatePlayerComboBox(ComboBox<String> playerComboBox) {
        playerComboBox.getItems().clear();
        ArrayList<String> playerUsernames = (ArrayList<String>) convertToPlayerList(leaderBoard.getPlayerUsernameFromLeaderBoard()).stream()
                .map(player -> player.getUsername())
                .collect(Collectors.toList());
        playerComboBox.getItems().addAll(playerUsernames);
    }
    
    private ArrayList<Player> convertToPlayerList(ArrayList<String> playerStrings) {
        ArrayList<Player> players = new ArrayList<>();
        for (String playerString : playerStrings) {
            players.add(new Player(playerString));
        }
        return players;
    }
}
