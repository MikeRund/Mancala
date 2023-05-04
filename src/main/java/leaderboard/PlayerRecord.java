package leaderboard;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The PlayerRecord class responsible for managing the player record of players.
 * It provide a functionality to update the player record.
 */
public class PlayerRecord {

    private ArrayList<Player> playerRecord = new ArrayList<>();

    private SimpleStringProperty playerName;
    private SimpleIntegerProperty wins;
    private SimpleIntegerProperty losses;

    private UltilityFunction ultilityFunction;

    public PlayerRecord(UltilityFunction ultilityFunction) {
        this.ultilityFunction = ultilityFunction;
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public int getWins() {
        return wins.get();
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getLosses() {
        return losses.get();
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
    }

    public void updatePlayerRecord(Player player, int wins, int losses) {
        Player foundThePlayer = findThePlayer(player);
    
        if (foundThePlayer != null) {
            ultilityFunction.setPlayerStatistic(wins, losses, foundThePlayer);
        } else {
            Player newPlayer = new Player(player);
            ultilityFunction.setPlayerStatistic(wins, losses, newPlayer);
            addNewPlayerToPlayerRecord(newPlayer);
        }
    }

    public Player findThePlayer(Player player) {
        for (int i = 0; i < playerRecord.size(); i++) {
            if (playerRecord.get(i).getUsername().equals(player.getUsername())) {
                return playerRecord.get(i);
            }
        }
        return null;
    }

    public void addNewPlayerToPlayerRecord(Player newPlayer) {
        playerRecord.add(newPlayer);
    }

    public void addALlPlayerRecords() {
        ArrayList<Player> players = null;
        try {
            players = Player.loadAllPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < players.size(); i++) {
            addNewPlayerToPlayerRecord(players.get(i));
        }
    }

    public ObservableList<Player> getPlayers() {
        return FXCollections.observableArrayList(playerRecord);
    }

    public ArrayList<Player> getPlayerRecord() {
        return playerRecord;
    }
}
