package leaderboard;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerRecordGUI extends Application {

    @FXML
    public TableView<Player> playerRecordTable;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, Integer> winsColumn;

    @FXML
    private TableColumn<Player, Integer> lossesColumn;
    
    private ObservableList<Player> observablePlayerList;
    private PlayerRecord playerRecord;
    private User sampleUser;

    private ObservableList<Player> observableList;

    public PlayerRecordGUI(PlayerRecord playerRecord, User sampleUser) {
        this.playerRecord = playerRecord;
        this.sampleUser = sampleUser;
    }

    public void start(Stage primaryStage) throws IOException {

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerRecord.fxml"));
        loader.setController(this);
        VBox root = loader.load();
        
        observablePlayerList = FXCollections.observableArrayList(playerRecord.getPlayers());
        observableList = FXCollections.observableArrayList(observablePlayerList);
        playerRecordTable.setItems(observableList);
        
        // Initialize the table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));

        // Set the table column resize policy
        playerRecordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load the players into the table
        observablePlayerList = FXCollections.observableArrayList(playerRecord.getPlayers());
        playerRecordTable.setItems(observablePlayerList);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Records");
        primaryStage.show();
    }

    // Update the player record with new player data
    public void updatePlayerRecordData(Player player, int wins, int losses) {
        playerRecord.updatePlayerRecord(player, wins, losses);
        observablePlayerList.setAll(playerRecord.getPlayerRecord());
        playerRecordTable.setItems(observablePlayerList);
    }
}
