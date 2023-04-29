package leaderboard;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerRecordGUI {

    @FXML
    public TableView<Player> playerRecordTable;

    @FXML
    private TableColumn<Player, String> usernameColumn;

    @FXML
    private TableColumn<Player, Integer> winsColumn;

    @FXML
    private TableColumn<Player, Integer> lossesColumn;

    @FXML
    private TableColumn<Player, Boolean> favouriteColumn;
    
    private PlayerRecord playerRecord;
    private User sampleUser;

    private ObservableList<Player> observablePlayerList;

    public PlayerRecordGUI(PlayerRecord playerRecord, User sampleUser) {
        this.playerRecord = playerRecord;
        this.sampleUser = sampleUser;
    }

    public void start(Stage stage) throws IOException {

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerRecord.fxml"));
        loader.setController(this);
        VBox root = loader.load();
 
        observablePlayerList = FXCollections.observableArrayList(playerRecord.getPlayers());
        playerRecordTable.setItems(observablePlayerList);

        // Initialize the table columns
        usernameColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's username as a SimpleStringProperty
            return new SimpleStringProperty(player.getUsername());
        });

        winsColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's wins as a SimpleIntegerProperty
            return new SimpleIntegerProperty(player.getWins()).asObject();
        });
        
        lossesColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's wins as a SimpleIntegerProperty
            return new SimpleIntegerProperty(player.getLosses()).asObject();
        });

        favouriteColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue(); 

            // Check if the player is a favorite for the sample user
            boolean isPlayerFavourite = playerRecord.isPlayerFavourite(sampleUser, player.getUsername());

            // Return the result as a SimpleBooleanProperty
            return new SimpleBooleanProperty(isPlayerFavourite);
        });

        // Set up cell factories for columns to display data correctly
        usernameColumn.setCellFactory(column -> new TableCell<Player, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        winsColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        lossesColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        favouriteColumn.setCellFactory(column -> new TableCell<Player, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    if (item) {
                        setText("★");
                        setStyle("-fx-alignment: CENTER;");
                    } else {
                        setText(null);
                    }
                }
            }
        });

        // Set color for the table
        playerRecordTable.setStyle(
            "-fx-base: #E9CDA2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" 
        );

        // Set alternating row colors
        playerRecordTable.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.styleProperty().bind(
                Bindings.when(Bindings.createIntegerBinding(
                    () -> row.getIndex() % 2, row.indexProperty()
                ).isEqualTo(0))
                .then("-fx-background-color: #F5DEB3;") // Color for even rows
                .otherwise("-fx-background-color: #E9CDA2;") // Color for odd rows
            );
            return row;
        });

        // Set the table column resize policy
        playerRecordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load the players into the table
        observablePlayerList = FXCollections.observableArrayList(playerRecord.getPlayers());
        playerRecordTable.setItems(observablePlayerList);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 300, 70);
        stage.setScene(scene);
        stage.setTitle("Player Records");

    }

    // Update the player record with new player data
    public void updatePlayerRecordData() {
        playerRecordTable.getItems().clear();
        playerRecordTable.getItems().addAll(playerRecord.getPlayerRecord());
    }

    public void show(Stage stage) throws IOException {
        stage.show();
    }   
}
