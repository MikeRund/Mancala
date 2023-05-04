package leaderboard;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.stage.Stage;

/**
* The PlayerRecordGUI class manages the functions to setup the player record. 
*/
public class PlayerRecordController {

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

    @FXML
    private Button markUnmarkFavouriteButton;
    
    private PlayerRecord playerRecord;
    private User sampleUser;
    private UltilityFunction ultilityFunction;

    private ObservableList<Player> observablePlayerList;

    public PlayerRecordController(PlayerRecord playerRecord, User sampleUser, UltilityFunction ultilityFunction) {
        this.playerRecord = playerRecord;
        this.sampleUser = sampleUser;
        this.ultilityFunction = ultilityFunction;
    }

    public void setUpPlayerRecordTable() {
        // Load the players into the table
        observablePlayerList = FXCollections.observableArrayList(playerRecord.getPlayers());
        playerRecordTable.setItems(observablePlayerList);

        // Automatically select the first row in the table
        playerRecordTable.getSelectionModel().selectFirst();

        setUpTableStyle();

        playerRecordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setUpTableStyle() {
        playerRecordTable.setStyle(
            "-fx-base: #E9CDA2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" 
        );

        setUpAlternateRowColor();
    }

    private void setUpAlternateRowColor() {
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

    private void setUpLossesColumnCellValue() {
        lossesColumn.setCellValueFactory(cellData -> {
            Player player = cellData.getValue();
            return new SimpleIntegerProperty(player.getLosses()).asObject();
        });
    }

    private void setUpLossesColumnCell() {
        lossesColumn.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                ultilityFunction.updateIntegerCell(item, empty, this);
            }
        });
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

    public void setUpUsernameColumn() {
        setUpUsernameColumnCell();
        setUpUsernameColumnCellValue();
    }

    public void setUpWinsColumn() {
        setUpWinsColumnCell();
        setUpWinsColumnCellValue();
    }

    public void setUpLossesColumn() {
        setUpLossesColumnCell();
        setUpLossesColumnCellValue();
    }

    public void setUpFavouriteColumn() {
        setUpFavouriteColumnCell();
        setUpFavouriteColumnCellValue();
    }

    public void updatePlayerRecordData() {
        playerRecordTable.getItems().clear();
        playerRecordTable.getItems().addAll(playerRecord.getPlayerRecord());
    }
    
    @FXML
    private void handleMarkUnmarkFavourite() {
        Player selectedPlayer = playerRecordTable.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            boolean isPlayerFavourite = ultilityFunction.isPlayerFavourite(sampleUser, selectedPlayer.getUsername());
            if (isPlayerFavourite) {
                User user = new User(selectedPlayer.getUsername());
                ultilityFunction.unmarkFavouriteUser(sampleUser, user);
            } else {
                User user = new User(selectedPlayer.getUsername());
                ultilityFunction.markFavouriteUser(sampleUser, user);
            }
            playerRecordTable.refresh();
        }
    }
    
    public void show(Stage stage) throws IOException {
        stage.show();
    }   
}
