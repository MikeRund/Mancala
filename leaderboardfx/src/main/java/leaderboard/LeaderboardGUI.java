package leaderboard;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The LeaderboardGUI class displays and manages the leaderboard. 
 * It allows users to view the leaderboard, sort it by wins or win percentage,
 * and see their favourite players. 
 */
public class LeaderboardGUI extends Application {

    @FXML
    private Button updateButton;

    @FXML
    private Button sortByWinsButton;

    @FXML
    private Button sortByWinPercentageButton;

    @FXML
    private TableView<Player> leaderboardTable;

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
        primaryStage.setTitle("Leaderboard");

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));
        loader.setController(this);
        VBox root = loader.load();
        String backgroundImageUrl = "file:src/main/resources/leaderboard/WoodenPattern.jpg";
        root.setStyle("-fx-background-image: url(" + backgroundImageUrl + "); -fx-background-size: cover;");
        
        // Set up the columns in the leaderboardTable
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        winPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("winPercentage"));
        favColumn.setCellValueFactory(cellData -> isPlayerFavoriteProperty(cellData.getValue()));

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
            return row;
        });
        
        // Set the cell factory for each column to apply the background color to every cell
        rankColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
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
                        setText("favourite");
                    } else {
                        setText(null);
                    }
                }
            }
        });

        // Set the data for the leaderboardTable
        leaderboardTable.setItems(observableList);
                
        // Set the data for the leaderboardTable
        leaderboardTable.setItems(observableList);

        Scene scene = new Scene(root, 512, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void handleUpdateButton() {
        // Update the leaderboard with new data 
        Player player = new Player("NewPlayer");
        int wins = 5;
        int losses = 3;
        
        updateLeaderboardData(player, wins, losses);
    }
    
    @FXML
    public void handleSortByWinsButton() {
        leaderBoard.sortLeaderBoardWins();
        observableList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observableList);
    }
    
    @FXML
    public void handleSortByWinPercentageButton() {
        leaderBoard.sortLeaderBoardWinPercent();
        observableList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observableList);
    }
    
    private BooleanProperty isPlayerFavoriteProperty(Player player) {
        return new SimpleBooleanProperty(leaderBoard.isPlayerFavourite(sampleUser, player.getUsername()));
    }    

    public void updateLeaderboardData(Player player, int wins, int losses) {
        leaderBoard.updateLeaderBoard(player, wins, losses);
        observableList.setAll(leaderBoard.getLeaderBoard());
        leaderboardTable.setItems(observableList);
    }    
}
