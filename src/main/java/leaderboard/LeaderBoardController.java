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
    private PlayerRecordController playerRecordController;

    ObservableList<Player> observablePlayerList;

    /**
     * Constructor for the LeaderBoardController class.
     * @param leaderBoard the LeaderBoard object containing the players' information
     * @param sampleUser the User object representing the current user
     * @param playerRecord the PlayerRecord object containing the players' record
     * @param playerRecordGUI the PlayerRecordGUI object for displaying the players' record
     * @param ultilityFunction the UltilityFunction object containing utility functions for the GUI
     * @param playerRecordController the PlayerRecordController object for handling the PlayerRecord GUI
     */
    public LeaderBoardController(LeaderBoard leaderBoard, User sampleUser, PlayerRecord playerRecord, 
    PlayerRecordGUI playerRecordGUI, UltilityFunction ultilityFunction, 
    PlayerRecordController playerRecordController) {
        this.leaderBoard = leaderBoard;
        this.sampleUser = sampleUser;
        this.playerRecord = playerRecord;
        this.playerRecordGUI = playerRecordGUI;
        this.ultilityFunction = ultilityFunction;
        this.playerRecordController = playerRecordController;
        observablePlayerList = FXCollections.observableArrayList(leaderBoard.getLeaderBoard());
    }

    /**
     * Sets up the rank column of the leaderboard table view.
     */
    public void setUpRankColumn() {
        setUpRankColumnCellValue();
        setUpRankColumnCell();
    }

    private void setUpRankColumnCellValue() {
        rankColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue();
            return new SimpleIntegerProperty(player.getRank()).asObject();
        });
    }

    private void setUpRankColumnCell() {
        rankColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateIntegerCell(item, empty, this);
            }
        });
    }

    /**
     * Sets up the username column of the leaderboard table view.
     */
    public void setUpUsernameColumn() {
        setUpUsernameColumnCellValue();
        setUpUsernameColumnCell();
    }

    private void setUpUsernameColumnCellValue() {
        usernameColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue();
            return new SimpleStringProperty(player.getUsername());
        });
    }

    private void setUpUsernameColumnCell() {
        usernameColumn.setCellFactory(column -> new TableCell<Player, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateStringCell(item, empty, this);
            }
        });
    }

    /**
     * Sets up the wins column in the leader board table.
     * It sets the cell value to be the number of wins of the player, and
     * sets the cell factory to update the cell with the win count.
     */
    public void setUpWinsColumn() {
        setUpWinsColumnCellValue();
        setUpWinsColumnCell();
    }

    private void setUpWinsColumnCellValue() {
        winsColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue();
            return new SimpleIntegerProperty(player.getWins()).asObject();
        });
    }

    private void setUpWinsColumnCell() {
        winsColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateIntegerCell(item, empty, this);
            }
        });
    }

    /**
     * Set up the win percentage column in the leaderboard table.
     */
    public void setUpWinPercentColumn() {
        setUpWinPercentColumnCellValue();
        setUpWinPercentColumnCell();
    }

    private void setUpWinPercentColumnCellValue() {
        winPercentageColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue();
            return new SimpleDoubleProperty(player.getWinPercentage()).asObject();
        });
    }

    private void setUpWinPercentColumnCell() {
        winPercentageColumn.setCellFactory(column -> new TableCell<Player, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateDoubleCell(item, empty, this);
            }
        });
    }

    /**
     * Set up the favourite column in the leaderboard table.
     */
    public void setUpFavouriteColumn() {
        setUpFavouriteColumnCellValue();
        setUpFavouriteColumnCell();
    }

    private void setUpFavouriteColumnCellValue() {
        favouriteColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue(); 
            boolean isPlayerFavourite = ultilityFunction.isPlayerFavourite(sampleUser, player.getUsername());
            return new SimpleBooleanProperty(isPlayerFavourite);
        });
    }

    private void setUpFavouriteColumnCell() {
        favouriteColumn.setCellFactory(column -> new TableCell<Player, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateFavouriteCell(item, empty, this, 
                sampleUser, ultilityFunction);
            }
        });
    }
    /**
     * Set up the leaderboard table.
     */
    public void setUpTable() {
        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setUpTableStyle();
        leaderboardTable.setItems(observablePlayerList);
    }

    /**
     * Set up the style for the leaderboard table.
     */
    public void setUpTableStyle() {
        // Set color for the table
        leaderboardTable.setStyle(
            "-fx-base: #E9CDA2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" 
        );
        setUpAlternateRowColor();
    }

    /**
     * Set up alternate row colors for the leaderboard table.
     */
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

    /**
     * Handle the click event on the Sort by Wins button to sort the leaderboard by wins.
     */
    @FXML
    public void handleSortByWinsButton() {
        leaderBoard.sortLeaderBoardWins();
        observablePlayerList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observablePlayerList);
    }

    /**
     * Handle the click event on the Sort by Win Percentage button to sort the leaderboard by win percentage.
     */
    @FXML
    public void handleSortByWinPercentageButton() {
        leaderBoard.sortLeaderBoardWinPercent();
        observablePlayerList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observablePlayerList);
    }

    /**
     * Update the leaderboard table with new player data.
     */
    public void updateLeaderboardTable() {
        leaderboardTable.getItems().clear();
        leaderboardTable.getItems().addAll(leaderBoard.getLeaderBoard());
    }

    /**
     * Handle the click event on a player row to show the player record.
     *
     * @param row the row of the player that was clicked
     */
    private void handleClickEventOnPlayerRow(TableRow<Player> row) {
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Player clickedPlayer = row.getItem();
                showPlayerRecord(clickedPlayer);
            }
        });
    }

    /**
     * Show the player record for the given player.
     *
     * @param player the player whose record will be shown
     */
    private void showPlayerRecord(Player player) {
        playerRecord = new PlayerRecord(ultilityFunction); 
        playerRecordController = new PlayerRecordController(playerRecord, sampleUser, ultilityFunction);
        playerRecordGUI = new PlayerRecordGUI(playerRecordController);

        // Update the record with the clicked player's information
        playerRecord.updatePlayerRecord(player, player.getWins(), player.getLosses());

        Stage playerRecordStage = new Stage();
        try {
            playerRecordGUI.start(playerRecordStage);
            playerRecordController.show(playerRecordStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start auto-updating the leaderboard table.
     */
    public void startAutoUpdate() {
        Timeline autoUpdateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), 
        event -> updateLeaderboardTable()));
        autoUpdateTimeline.setCycleCount(Timeline.INDEFINITE);
        autoUpdateTimeline.play();
    }    
}
