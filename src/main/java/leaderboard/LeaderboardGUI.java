package leaderboard;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The LeaderboardGUI class displays and manages the leaderboard. 
 * It allows users to view the leaderboard, sort it by wins or win percentage,
 * and see their favourite players. 
 */
public class LeaderboardGUI extends Application {

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
    private TableColumn<Player, Boolean> favColumn;

    private LeaderBoard leaderBoard;
    private User sampleUser;

    ObservableList<Player> observableList;

    public LeaderboardGUI(LeaderBoard leaderBoard, User sampleUser) {
        this.leaderBoard = leaderBoard;
        this.sampleUser = sampleUser;
        observableList = FXCollections.observableArrayList(leaderBoard.getLeaderBoard());
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the window
        primaryStage.setTitle("Leaderboard");

        // Load the FXML file and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));
        loader.setController(this);
        VBox root = loader.load();

        // Set background image
        String backgroundImageUrl = "file:src/main/resources/leaderboard/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");
 
        // Set up the columns in the leaderboardTable
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        winPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("winPercentage"));
        favColumn.setCellValueFactory(cellData -> {
            // Get the Player object from the cell data
            Player player = cellData.getValue(); 

            // Check if the player is a favorite for the sample user
            boolean isPlayerFavourite = leaderBoard.isPlayerFavourite(sampleUser, player.getUsername());

            // Return the result as a SimpleBooleanProperty
            return new SimpleBooleanProperty(isPlayerFavourite);
        });
        
        // Set the table column resize policy
        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set color for the table
        leaderboardTable.setStyle(
            "-fx-base: #E9CDA2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" 
        );
        
        // Set alternating row colors
        leaderboardTable.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.styleProperty().bind(
                Bindings.when(Bindings.createIntegerBinding(
                    () -> row.getIndex() % 2, row.indexProperty()
                ).isEqualTo(0))
                .then("-fx-background-color: #F5DEB3;") // Color for even rows
                .otherwise("-fx-background-color: #E9CDA2;") // Color for odd rows
            );

            // Add event handler for row clicks
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                        Player clickedPlayer = row.getItem();
                        showPlayerRecord(clickedPlayer);
                    }
                }
            });
            return row;
        });
        
        // Set up cell factories for columns to display data correctly
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

        favColumn.setCellFactory(column -> new TableCell<Player, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    Player currentPlayer = getTableView().getItems().get(getIndex());
                    if (leaderBoard.isPlayerFavourite(sampleUser, currentPlayer.getUsername())) {
                        setText("★");
                        setStyle("-fx-alignment: CENTER;");
                    } else {
                        setText(null);
                    }
                }
            }
        });

        // Set the data for the leaderboardTable
        leaderboardTable.setItems(observableList);

        // Set scene
        Scene scene = new Scene(root, 512, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Sort leaderboard by wins when the "Sort by Wins" button is clicked
    @FXML
    public void handleSortByWinsButton() {
        leaderBoard.sortLeaderBoardWins();
        observableList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observableList);
    }
    
    // Sort leaderboard by win percentage when the "Sort by Win Percentage" button is clicked
    @FXML
    public void handleSortByWinPercentageButton() {
        leaderBoard.sortLeaderBoardWinPercent();
        observableList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observableList);
    }

    // Update the leaderboard with new player data
    public void updateLeaderboardTable() {
        leaderboardTable.getItems().clear();
        leaderboardTable.getItems().addAll(leaderBoard.getLeaderBoard());
    }

    private void showPlayerRecord(Player player) {
        PlayerRecord playerRecord = new PlayerRecord(); 

        // Update the record with the clicked player's information
        playerRecord.updatePlayerRecord(player, player.getWins(), player.getLosses()); 
    
        PlayerRecordGUI playerRecordGUI = new PlayerRecordGUI(playerRecord, sampleUser);
        Stage playerRecordStage = new Stage();
        try {
            playerRecordGUI.start(playerRecordStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
