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

    /**
     * Creates a new instance of the PlayerRecord class with the specified utility function.
     *
     * @param ultilityFunction the utility function to use for setting player statistics
     */
    public PlayerRecord(UltilityFunction ultilityFunction) {
        this.ultilityFunction = ultilityFunction;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player as a String
     */
    public String getPlayerName() {
        return playerName.get();
    }

    /**
     * Sets the name of the player.
     *
     * @param playerName the name of the player as a String
     */
    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    /**
     * Returns the number of wins for the player.
     *
     * @return the number of wins for the player as an int
     */
    public int getWins() {
        return wins.get();
    }

    /**
     * Sets the number of wins for the player.
     *
     * @param wins the number of wins for the player as an int
     */
    public void setWins(int wins) {
        this.wins.set(wins);
    }

    /**
     * Returns the number of losses for the player.
     *
     * @return the number of losses for the player as an int
     */
    public int getLosses() {
        return losses.get();
    }

    /**
     * Sets the number of losses for the player.
     *
     * @param losses the number of losses for the player as an int
     */
    public void setLosses(int losses) {
        this.losses.set(losses);
    }

    /**
     * Updates the player record with the specified player and their win/loss statistics.
     * If the player already exists in the record, their statistics are updated. Otherwise,
     * a new player is added to the record.
     *
     * @param player the player to update or add to the record
     * @param wins the number of wins for the player
     * @param losses the number of losses for the player
     */
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

    /**
     * Searches for a player in the record with the same username as the specified player.
     *
     * @param player the player to search for in the record
     * @return the player if found in the record, otherwise null
     */
    public Player findThePlayer(Player player) {
        for (int i = 0; i < playerRecord.size(); i++) {
            if (playerRecord.get(i).getUsername().equals(player.getUsername())) {
                return playerRecord.get(i);
            }
        }
        return null;
    }

    /**
     * Adds a new player to the player record.
     *
     * @param newPlayer the player to add to the player record
     */
    public void addNewPlayerToPlayerRecord(Player newPlayer) {
        playerRecord.add(newPlayer);
    }

    /**
     * Adds all players in the database to the player record.
     * If an SQLException occurs, it is printed to the standard error stream.
     */
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

    /**
     * Returns an ObservableList of all the players in the player record.
     *
     * @return an ObservableList of all the players in the player record
     */
    public ObservableList<Player> getPlayers() {
        return FXCollections.observableArrayList(playerRecord);
    }

    /**
     * Returns an ArrayList of all the players in the player record.
     *
     * @return an ArrayList of all the players in the player record
     */
    public ArrayList<Player> getPlayerRecord() {
        return playerRecord;
    }
}
