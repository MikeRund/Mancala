package leaderboard;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The LeaderBoardController class manages the functions to setup the leaderboardGUI. 
 */
public class LeaderBoardController {

    @FXML
    private Button sortByWinsButton;

    @FXML
    private Button sortByWinPercentageButton;

    @FXML
    public TableView<Player> leaderboardTable;

    @FXML
    private TableColumn<Player, Integer> rankColumn;

    @FXML
    private TableColumn<Player, String> usernameColumn;

    @FXML
    private TableColumn<Player, Integer> winsColumn;

    @FXML
    private TableColumn<Player, Double> winPercentageColumn;

    @FXML
    private TableColumn<Player, Boolean> favouriteColumn;

    private LeaderBoard leaderBoard;
    private User sampleUser;
    private PlayerRecord playerRecord;
    private PlayerRecordGUI playerRecordGUI;
    private UltilityFunction ultilityFunction;

    ObservableList<Player> observablePlayerList;

    public LeaderBoardController(LeaderBoard leaderBoard, User sampleUser, PlayerRecord playerRecord, 
    PlayerRecordGUI playerRecordGUI, UltilityFunction ultilityFunction) {
        this.leaderBoard = leaderBoard;
        this.sampleUser = sampleUser;
        this.playerRecord = playerRecord;
        this.playerRecordGUI = playerRecordGUI;
        this.ultilityFunction = ultilityFunction;
        observablePlayerList = FXCollections.observableArrayList(leaderBoard.getLeaderBoard());
    }
    
    public void setUpRankColumn() {
        rankColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's rank as a SimpleIntegerProperty
            return new SimpleIntegerProperty(player.getRank()).asObject();
        });

        rankColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
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
    }

    public void setUpUsernameColumn() {
        usernameColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's username as a SimpleStringProperty
            return new SimpleStringProperty(player.getUsername());
        });

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
    }

    public void setUpWinsColumn() {
        winsColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's wins as a SimpleIntegerProperty
            return new SimpleIntegerProperty(player.getWins()).asObject();
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
    }

    public void setUpWinPercentColumn() {
        winPercentageColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue();
        
            // Return the player's win percentage as a SimpleDoubleProperty
            return new SimpleDoubleProperty(player.getWinPercentage()).asObject();
        });

        winPercentageColumn.setCellFactory(column -> new TableCell<Player, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f%%", item));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }

    public void setUpFavouriteColumn() {
        favouriteColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue(); 

            // Check if the player is a favorite for the user
            boolean isPlayerFavourite = ultilityFunction.isPlayerFavourite(sampleUser, player.getUsername());

            // Return the result as a SimpleBooleanProperty
            return new SimpleBooleanProperty(isPlayerFavourite);
        });

        favouriteColumn.setCellFactory(column -> new TableCell<Player, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    Player currentPlayer = getTableView().getItems().get(getIndex());
                    if (ultilityFunction.isPlayerFavourite(sampleUser, currentPlayer.getUsername())) {
                        setText("★");
                        setStyle("-fx-alignment: CENTER;");
                    } else {
                        setText(null);
                    }
                }
            }
        });
    }

    public void setUpTable() {
        // Set the table column resize policy
        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setUpTableStyle();
        leaderboardTable.setItems(observablePlayerList);
    }

    public void setUpTableStyle() {
        // Set color for the table
        leaderboardTable.setStyle(
            "-fx-base: #E9CDA2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" 
        );
        setUpAlternateRowColor();
    }
    
    public void setUpAlternateRowColor() {
        leaderboardTable.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.styleProperty().bind(
                Bindings.when(Bindings.createIntegerBinding(
                    () -> row.getIndex() % 2, row.indexProperty()
                ).isEqualTo(0))
                .then("-fx-background-color: #F5DEB3;") // Color for even rows
                .otherwise("-fx-background-color: #E9CDA2;") // Color for odd rows
            );
            handleClickEventOnPlayerRow(row);
            return row;
        });
    }

    // Sort leaderboard by wins button 
    @FXML
    public void handleSortByWinsButton() {
        leaderBoard.sortLeaderBoardWins();
        observablePlayerList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observablePlayerList);
    }
    
    // Sort leaderboard by win percentage button 
    @FXML
    public void handleSortByWinPercentageButton() {
        leaderBoard.sortLeaderBoardWinPercent();
        observablePlayerList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observablePlayerList);
    }

    // Update the leaderboard with new player data
    public void updateLeaderboardTable() {
        leaderboardTable.getItems().clear();
        leaderboardTable.getItems().addAll(leaderBoard.getLeaderBoard());
    }

    private void handleClickEventOnPlayerRow(TableRow<Player> row) {
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Player clickedPlayer = row.getItem();
                showPlayerRecord(clickedPlayer);
            }
        });
    }

    private void showPlayerRecord(Player player) {
        playerRecord = new PlayerRecord(ultilityFunction); 

        playerRecordGUI = new PlayerRecordGUI(playerRecord, sampleUser, ultilityFunction);

        // Update the record with the clicked player's information
        playerRecord.updatePlayerRecord(player, player.getWins(), player.getLosses());
 
        Stage playerRecordStage = new Stage();
        try {
            playerRecordGUI.start(playerRecordStage);
            playerRecordGUI.show(playerRecordStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startAutoUpdate() {
        Timeline autoUpdateTimeline = new Timeline(new KeyFrame(Duration.millis(750), 
        event -> updateLeaderboardTable()));
        autoUpdateTimeline.setCycleCount(Timeline.INDEFINITE);
        autoUpdateTimeline.play();
    }    
}
