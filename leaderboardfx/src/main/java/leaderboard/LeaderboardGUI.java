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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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

        // Set up the columns in the leaderboardTable
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        winPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("winPercentage"));
        favColumn.setCellValueFactory(cellData -> isPlayerFavoriteProperty(cellData.getValue()));

        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
               
        // Set the data for the leaderboardTable
        leaderboardTable.setItems(observableList);

        // Set the scene and show the stage
        primaryStage.setScene(new Scene(root));
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
